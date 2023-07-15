package com.example.demofacebook.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {

    private TextView textFullName, textUserName, textEmail, textPassword, textPhoneNumner;
    private Button btnRegister;

    public void viewLoginClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(Register_Activity.this, LoginActivity.class);
        startActivity(intent);
    }

    // quên mật khẩu
    public void viewForgotPassword(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(Register_Activity.this, ForgotPassword_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textFullName = findViewById(R.id.textFullName);
        textUserName = findViewById(R.id.textUserName);
        textEmail = findViewById(R.id.textEmail);
        textPhoneNumner = findViewById(R.id.textPhoneNum);
        textPassword = findViewById(R.id.textPass);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = textFullName.getText().toString();
                String userName = textUserName.getText().toString();
                String email = textEmail.getText().toString();
                String phoneNumber = textPhoneNumner.getText().toString();
                String password = textPassword.getText().toString();
                User account = new User("null",fullName,userName,phoneNumber,email,password);
                CustomerAccount customer = new CustomerAccount(account);
                createCustomer(customer);
            }
        });

    }

    private void createCustomer(CustomerAccount customer){
        ApiService.apiService.createCustomer(customer).enqueue(new Callback<CustomerAccount>() {
            @Override
            public void onResponse(Call<CustomerAccount> call, Response<CustomerAccount> response) {
                if(response.isSuccessful()){
                    CustomerAccount customerAccount = response.body();
                        if(customerAccount !=null ){
                            Toast.makeText(Register_Activity.this, "RegisterSuccess", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register_Activity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Register_Activity.this, "Create UnSuccess", Toast.LENGTH_SHORT).show();
                        }
                }
            }

            @Override
            public void onFailure(Call<CustomerAccount> call, Throwable t) {
                Toast.makeText(Register_Activity.this, "UnSuccess", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
