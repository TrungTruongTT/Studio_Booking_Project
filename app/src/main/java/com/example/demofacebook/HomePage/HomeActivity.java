package com.example.demofacebook.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demofacebook.Fragment.BookingFragment;
import com.example.demofacebook.Fragment.ChatFragment;
import com.example.demofacebook.Fragment.HomeFragment;
import com.example.demofacebook.Fragment.NewFeedFragment;
import com.example.demofacebook.Fragment.UserFragment;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class  HomeActivity extends AppCompatActivity {
    private Fragment selectedFragment = null;
    private Toolbar toolbar;
    SearchView searchView;
    StudioHomeAdapter studioHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.myToolBar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId() == R.id.action_home) {
                    selectedFragment = new HomeFragment();
                    toolbar.setTitle("Studio Booking Service");
                }
                if(item.getItemId() == R.id.action_chat) {
                    toolbar.setTitle("Chat");
                    selectedFragment = new ChatFragment();
                }
                if(item.getItemId() == R.id.action_feed) {
                    toolbar.setTitle("New Feed");
                    selectedFragment = new NewFeedFragment();
                }
                if(item.getItemId() == R.id.action_booking) {
                    toolbar.setTitle("Booking");
                    selectedFragment = new BookingFragment();
                }
                if(item.getItemId() == R.id.action_user) {
                    toolbar.setTitle("User");
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


}