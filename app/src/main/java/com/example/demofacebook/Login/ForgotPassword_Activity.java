package com.example.demofacebook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofacebook.R;

public class ForgotPassword_Activity extends AppCompatActivity {

    //chuyển về main
    public void viewLoginClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(ForgotPassword_Activity.this, MainActivity.class);
        startActivity(intent);
    }
    //chuyển sang đăng ký
    public void viewRegisterClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(ForgotPassword_Activity.this, Register_Activity.class);
        startActivity(intent);
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_forgot_pass);

        }
}

