package com.example.demofacebook.HomePage;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.demofacebook.Fragment.MainPageFragment.BookingFragment;
import com.example.demofacebook.Fragment.MainPageFragment.ChatFragment;
import com.example.demofacebook.Fragment.MainPageFragment.HomeFragment;
import com.example.demofacebook.Fragment.MainPageFragment.NewFeedFragment;
import com.example.demofacebook.Fragment.MainPageFragment.NotificationFragment;
import com.example.demofacebook.Fragment.MainPageFragment.UserFragment;
import com.example.demofacebook.R;
import com.example.demofacebook.Search.SearchActivity;

public class HomeActivity extends AppCompatActivity {
    private Fragment selectedFragment = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        loadBottomNavigationView();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            OpenSearchScreen();
        }
        if (item.getItemId() == R.id.ringtone) {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            OpenNotificationScreen();
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
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

    private void OpenNotificationScreen() {
//        Intent intent = new Intent(this, SearchActivity.class);
//        startActivity(intent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notification");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Home_ToolBar)));
        selectedFragment = new NotificationFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
    }

    private void loadBottomNavigationView() {
        AHBottomNavigation bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //Define Items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.action_home, R.drawable.home_white_48dp, R.color.Home_ToolBar);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.action_chat, R.drawable.chat_white_48dp, R.color.Chat_ToolBar);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.action_feed, R.drawable.feed_white_48dp, R.color.NewFeed_ToolBar);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.action_booking, R.drawable.shopping_cart_white_48dp, R.color.Booking_ToolBar);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.action_user, R.drawable.account_circle_white_48dp, R.color.User_ToolBar);
        // Add items
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.addItem(item3);
        bottomNavigationView.addItem(item4);
        bottomNavigationView.addItem(item5);
        //Style
        bottomNavigationView.setColored(true);

        //OnClickItem
        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (position == 0) {
                    selectedFragment = new HomeFragment();
                    getSupportActionBar().setTitle("Studio Booking Service");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Home_ToolBar)));
                }
                if (position == 1) {
                    getSupportActionBar().setTitle("Chat");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Chat_ToolBar)));
                    selectedFragment = new ChatFragment();
                }
                if (position == 2) {
                    getSupportActionBar().setTitle("New Feed");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.NewFeed_ToolBar)));
                    selectedFragment = new NewFeedFragment();
                }
                if (position == 3) {
                    getSupportActionBar().setTitle("Booking");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Booking_ToolBar)));
                    selectedFragment = new BookingFragment();
                }
                if (position == 4) {
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

        //Notification Icon
        AHNotification notification = new AHNotification.Builder().setText("10").setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.colorAccent)).setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Home_ToolBar)).build();
        bottomNavigationView.setNotification(notification, 1);
    }
}