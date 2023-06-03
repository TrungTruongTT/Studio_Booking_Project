package com.example.demofacebook.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

//chuyển register
    public void viewRegisterClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(MainActivity.this, Register_Activity.class);
        startActivity(intent);
    }
//chuyển forgotPass
    public void viewForgotPassword(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(MainActivity.this, ForgotPassword_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gắn kết các thành phần UI
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.btnLogin);


        // Thêm xử lý sự kiện cho nút đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra tên đăng nhập và mật khẩu
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (isValidCredentials(email, password)) {
                    // Nếu thông tin đăng nhập hợp lệ, chuyển đến màn hình chính
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Nếu thông tin đăng nhập không hợp lệ, hiển thị thông báo lỗi
                    // (có thể thay bằng cách sử dụng Toast hoặc AlertDialog)
                    editTextEmail.setError("Invalid credentials");
                }
            }
        });


    }

    private boolean isValidCredentials(String email, String password) {
        // Thực hiện kiểm tra thông tin đăng nhập
        // (thông thường, bạn sẽ kiểm tra với cơ sở dữ liệu hoặc API)
        return true; //email.equals("admin@gmail.com") && password.equals("admin123");
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