package com.example.demofacebook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.OptSms;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.Regex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {


    private EditText textFullName, textUserName, textEmail, textPassword, textPhoneNumner;
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
    public static String convertToInternationalFormat(String phoneNumber) {
        // Remove any leading zeros from the phone number
        String trimmedNumber = phoneNumber.replaceFirst("^0+", "");

        // Add the country code "+84" to the trimmed number
        return "+84" + trimmedNumber;
    }
    private boolean validateEmail(String email){
        if(email.isEmpty()){
            textEmail.setError("Email is required");
            return false;
        } else if(!Regex.EMAIL_ADDRESS.matcher(email).matches()){
            textEmail.setError("Pleased enter a valid email");
            return false;
        }else {
            textEmail.setError(null);
            return true;
        }
    }
    private boolean validateUsername(String username){
        if(username.isEmpty()){
            textUserName.setError("Password is required0");
            return false;
        }else {
            textUserName.setError(null);
            return true;
        }
    }
    private boolean validatePhone(String phone){
        if(phone.isEmpty()){
            textPhoneNumner.setError("Phone is required0");
            return false;
        }else if (!Regex.PHONE_NUMBER.matcher(phone).matches()){
            textPhoneNumner.setError("Please enter valid phone");
            return false;
        }else {
            textPhoneNumner.setError(null);
            return true;
        }
    }
    private boolean validateFullName(String fullName){
        if(fullName.isEmpty()){
            textFullName.setError("Name is required");
            return false;
        }else {
            textFullName.setError(null);
            return true;
        }
    }
    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarSignInActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Sign up");
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initToolBar();

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
                if (validateFullName(fullName) && validateUsername(userName) && validateEmail(email)
                        && validatePhone(phoneNumber) && validatePassword(password)) {
                    User account = new User("null",fullName,userName,phoneNumber,email,password);
                    CustomerAccount customer = new CustomerAccount(account);
                    sendRequestToServer(customer);
                } else {
                    // Hiển thị thông báo lỗi hoặc thực hiện các hành động khác nếu dữ liệu không hợp lệ
                    Toast.makeText(getApplicationContext(), "Please try again!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validatePassword(String pass){
        if (pass.isEmpty()) {
            textPassword.setError("Password is required");
            return false;
        } else if (!Regex.PASSWORD.matcher(pass).matches()) {
            textPassword.setError("Password required 8-20 character and least 1 special character");
            return false;
        } else {
            textPassword.setError(null);
            return true;
        }
    }

    private void sendRequestToServer(CustomerAccount customer) {
        String phone = convertToInternationalFormat(customer.getUser().getPhone());
        OptSms phoneOpt = new OptSms(phone);
        Log.w("opt", phoneOpt.getPhoneNumber());

        Call<OptSms> call = ApiService.apiServiceGuest.getOtp(phoneOpt);
        call.enqueue(new Callback<OptSms>() {
            @Override
            public void onResponse(Call<OptSms> call, Response<OptSms> response) {
                if (response.isSuccessful()) {
                    OptSms responseValue = response.body();
                    Intent intent = new Intent(Register_Activity.this, OtpConfirmActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("customer", customer);
                    bundle.putSerializable("idOtp", responseValue.getOtpId());
                    bundle.putSerializable("phoneNumberFormatted", responseValue.getPhoneNumber());
                    intent.putExtras(bundle);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Send SMS Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Send SMS Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OptSms> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
