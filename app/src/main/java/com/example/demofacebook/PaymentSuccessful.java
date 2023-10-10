package com.example.demofacebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demofacebook.Fragment.MainPageFragment.BookingFragment;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;

public class PaymentSuccessful extends AppCompatActivity {
    Button btn_ViewOrderList;
    Button btn_DiscoverMoreStudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        initToolBar();
        btn_ViewOrderList = findViewById(R.id.btn_ViewOrderList);
        btn_ViewOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGotoHome();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                Fragment bookingFragment = new BookingFragment();
//                fragmentTransaction.replace(R.id.frame_container,bookingFragment).commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, bookingFragment).commit();
            }
        });
        btn_DiscoverMoreStudio = findViewById(R.id.btn_DiscoverMoreStudio);
        btn_DiscoverMoreStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    private void onClickGotoHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarPickTimeActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
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