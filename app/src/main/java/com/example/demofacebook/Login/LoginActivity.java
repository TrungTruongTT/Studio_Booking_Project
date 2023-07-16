package com.example.demofacebook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.Login_Request;
import com.example.demofacebook.Model.TokenResponse;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.Regex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    //chuyển register
    public void viewRegisterClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(LoginActivity.this, Register_Activity.class);
        startActivity(intent);
    }

    //chuyển forgotPass
    public void viewForgotPassword(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(LoginActivity.this, ForgotPassword_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Gắn kết các thành phần UI
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.btnLogin);

        // Thêm xử lý sự kiện cho nút đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra tên đăng nhập và mật khẩu
                String credential = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if (validateEmail(credential) && validatePassword(password)) {
                    isValidCredentials(credential, password);
                } else {
                    // Hiển thị thông báo lỗi hoặc thực hiện các hành động khác nếu dữ liệu không hợp lệ
                    Toast.makeText(getApplicationContext(), "Please try again!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void isValidCredentials(String credential, String password) {
        Login_Request loginAccount = new Login_Request(credential,password);
        ApiService.apiService.login(loginAccount).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()){
                    TokenResponse tokenResponse= response.body();
                    if(tokenResponse !=null){
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", new User(1, "", "phi", null, "", "", ""));
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "LoginSuccess", Toast.LENGTH_SHORT).show();
                        //getCustomerAccountByPhoneorEmail(credential);
                    }else {
                        // Nếu thông tin đăng nhập không hợp lệ, hiển thị thông báo lỗi
                        // (có thể thay bằng cách sử dụng Toast hoặc AlertDialog)
                        editTextEmail.setError("Invalid credentials");
                    }
                }
            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "LOGIN API Unsuccess", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateEmail(String email){
        if(email.isEmpty()){
            editTextEmail.setError("Email/Phone is required");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }
    private boolean validatePassword(String pass){
        if(pass.isEmpty()){
            editTextPassword.setError("Password is required");
            return false;
        }else {
            editTextPassword.setError(null);
            return true;
        }
    }
    private void getCustomerAccountByPhoneorEmail(String credential){
        ApiService.apiService.getCustomerByEmailorPhone(credential).enqueue(new Callback<CustomerAccount>() {
            @Override
            public void onResponse(Call<CustomerAccount> call, Response<CustomerAccount> response) {
                if(response.isSuccessful()){
                    CustomerAccount customerAccount = response.body();
                    if(customerAccount!=null){
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        //bundle.putSerializable("acccount", customerAccount);
                        bundle.putSerializable("user", new User(1, "", "phi", null, "", "", ""));
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, customerAccount.getCustomerId(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerAccount> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "getCustomerFail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}