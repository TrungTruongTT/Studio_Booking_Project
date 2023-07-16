package com.example.demofacebook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(DataLocalManager.getFirstInstalled()){
            Toast.makeText(this, "First installed app", Toast.LENGTH_SHORT).show();
            DataLocalManager.setFirstInstalled(true);
        }

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

}