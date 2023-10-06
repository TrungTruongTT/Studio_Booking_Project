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
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.Login_Request;
import com.example.demofacebook.Model.TokenResponse;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;

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

        initToolBar();
        // Gắn kết các thành phần UI
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.btnLogin);

        // Thêm xử lý sự kiện cho nút đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra tên đăng nhập và mật khẩu
                String credential = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
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
        ApiService.apiServiceGuest.login(loginAccount).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    TokenResponse tokenResponse = response.body();
                    if (tokenResponse != null) {
                        getCustomerByEmailOrPhone(credential);
                        DataLocalManager.setTokenResponse(tokenResponse);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    } else {
                        // Nếu thông tin đăng nhập không hợp lệ, hiển thị thông báo lỗi
                        Toast.makeText(LoginActivity.this, "Invalid Load Token Fail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Nếu thông tin đăng nhập không hợp lệ, hiển thị thông báo lỗi
                    Toast.makeText(LoginActivity.this, "Invalid Account", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lost Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getCustomerByEmailOrPhone(String credential){
        ApiService.apiServiceGuest.getCustomerByEmailorPhone(credential).enqueue(new Callback<List<CustomerAccount>>() {
            @Override
            public void onResponse(Call<List<CustomerAccount>> call, Response<List<CustomerAccount>> response) {
                if(response.isSuccessful()){
                    //API trả về list
                    List<CustomerAccount> mAccount = response.body();
                    //lấy account duy nhất
                    CustomerAccount account = mAccount.get(0);
                    if(account!=null){
                        Log.d("CustomerAccount", "User ID: " + account.getUser().getUserId());
                        Log.d("CustomerAccount", "Full Name: " + account.getUser().getFullName());
                        Log.d("CustomerAccount", "Email: " + account.getUser().getEmail());
                        Log.d("CustomerAccount", "Image URL: " + account.getUser().getImage());
                        DataLocalManager.setCustomerAccount(account);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Load Token Fail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Fail Status", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<CustomerAccount>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lost Connection", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarLoginActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Log in");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}