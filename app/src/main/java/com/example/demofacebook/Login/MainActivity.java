package com.example.demofacebook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Set<String> test = addSetStringUserNameTEST();
        /*if(DataLocalManager.getFirstInstalled()){
            Toast.makeText(this, "First installed app", Toast.LENGTH_SHORT).show();
            DataLocalManager.setFirstInstalled(true);
        }*/

        DataLocalManager.setNameUserInstalled(test);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    private Set<String> addSetStringUserNameTEST(){
        Set<String> nameUser = new HashSet<>();
        nameUser.add("test1");
        nameUser.add("test2");
        nameUser.add("test3");
        nameUser.add("test4");
        nameUser.add("test5");
        return nameUser;
    }
}