package com.example.demofacebook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;

public class Register_Activity extends AppCompatActivity {
    private TextView textUserName, textEmail, textPassword, textPhoneNumner;
    private Button btnRegister;
    //chuyển về main
    public void viewLoginClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(Register_Activity.this, MainActivity.class);
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
        setContentView(R.layout.layout_register);
        textUserName = findViewById(R.id.textUserName);
        textEmail = findViewById(R.id.textEmail);
        textPhoneNumner = findViewById(R.id.textPhoneNum);
        textPassword = findViewById(R.id.textPass);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String userName = textUserName.getText().toString();
                String email = textEmail.getText().toString();
                String phoneNumber = textPhoneNumner.getText().toString();
                String password = textPassword.getText().toString();*/
            }
        });


    }

    private void createCustomer(User customer){

    }
}
