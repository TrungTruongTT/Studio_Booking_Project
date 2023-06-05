package com.example.demofacebook.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.Toast;

import com.example.demofacebook.Fragment.BookingFragment;
import com.example.demofacebook.Fragment.ChatFragment;
import com.example.demofacebook.Fragment.HomeFragment;
import com.example.demofacebook.Fragment.NewFeedFragment;
import com.example.demofacebook.Fragment.UserFragment;

import com.example.demofacebook.R;
import com.example.demofacebook.Search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private Fragment selectedFragment = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.myToolBar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    selectedFragment = new HomeFragment();
                    getSupportActionBar().setTitle("Studio Booking Service");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Home_ToolBar)));
                }
                if (item.getItemId() == R.id.action_chat) {
                    getSupportActionBar().setTitle("Chat");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Chat_ToolBar)));
                    selectedFragment = new ChatFragment();
                }
                if (item.getItemId() == R.id.action_feed) {
                    getSupportActionBar().setTitle("New Feed");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.NewFeed_ToolBar)));
                    selectedFragment = new NewFeedFragment();
                }
                if (item.getItemId() == R.id.action_booking) {
                    getSupportActionBar().setTitle("Booking");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Booking_ToolBar)));
                    selectedFragment = new BookingFragment();
                }
                if (item.getItemId() == R.id.action_user) {
                    getSupportActionBar().setTitle("User");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.User_ToolBar)));
                    selectedFragment = new UserFragment();
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
                }
                return true;
            }
        });

        // Set the initial fragment as HomeFragment
        selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            OpenSearchScreen();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void OpenSearchScreen() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}