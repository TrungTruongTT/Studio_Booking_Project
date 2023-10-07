package com.example.demofacebook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofacebook.BookingStudioActivity;
import com.example.demofacebook.HomePage.ActivitySplashScreen;
import com.example.demofacebook.PickTimeActivity;
import com.example.demofacebook.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);


        Button signUpButtonLobby = findViewById(R.id.SignUpButtonLobby);
        Button loginButtonLobby = findViewById(R.id.LoginButtonLobby);

        loginButtonLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookingStudioActivity.class);
                startActivity(intent);
            }
        });

        signUpButtonLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register_Activity.class);
                startActivity(intent);
            }
        });






    }
}