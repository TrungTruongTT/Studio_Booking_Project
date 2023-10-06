package com.example.demofacebook.HomePage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.demofacebook.FavoriteActivity;
import com.example.demofacebook.Fragment.MainPageFragment.BookingFragment;
import com.example.demofacebook.Fragment.MainPageFragment.ChatFragment;
import com.example.demofacebook.Fragment.MainPageFragment.HomeFragment;
import com.example.demofacebook.Fragment.MainPageFragment.NewFeedFragment;
import com.example.demofacebook.Fragment.MainPageFragment.NotificationActivity;
import com.example.demofacebook.Fragment.MainPageFragment.UserFragment;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;
import com.example.demofacebook.Search.SearchActivity;

public class HomeActivity extends AppCompatActivity {
    private Fragment selectedFragment = null;
    private Toolbar toolbar;

    private Studio studio;
    private Service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
         studio = loadStudio();
         service = loadService();
        loadBottomNavigationView();

    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.search) {
//            OpenSearchScreen();
//        }
//        if (item.getItemId() == R.id.ringtone) {
//            OpenNotificationScreen();
//        }
//        if (item.getItemId() == R.id.favorite) {
//            OpenFavoriteScreen();
//        }
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void OpenFavoriteScreen() {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }
    private void OpenSearchScreen() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    private void OpenNotificationScreen() {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }
    private void loadBottomNavigationView() {
        AHBottomNavigation bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //Define Items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.action_home, R.drawable.home_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.action_chat, R.drawable.chat_white_48dp, R.color.ToolBar);
//        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.action_feed, R.drawable.feed_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.action_booking, R.drawable.shopping_cart_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.action_user, R.drawable.account_circle_white_48dp, R.color.ToolBar);
        // Add items
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.addItem(item3);
        bottomNavigationView.addItem(item4);
//        bottomNavigationView.addItem(item5);

        bottomNavigationView.setCurrentItem(0);
        //Style
        bottomNavigationView.setColored(true);
        // Change colors
        bottomNavigationView.setAccentColor(Color.parseColor("#235a8f"));
        bottomNavigationView.setInactiveColor(Color.parseColor("#cfcfcf"));

        //OnClickItem
        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (position == 0) {
                    selectedFragment = new HomeFragment();
                    getSupportActionBar().setTitle("Framemates");
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
                    getSupportActionBar();
                }
                if (position == 1) {
                    getSupportActionBar().setTitle("Chat");
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
                    selectedFragment = new ChatFragment();
                }
//                if (position == 2) {
//                    getSupportActionBar().setTitle("New Feed");
//                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
//                    selectedFragment = new NewFeedFragment();
//                }
                if (position == 2) {
                    getSupportActionBar().setTitle("Order Management");
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
                    selectedFragment = new BookingFragment();
                }
                if (position == 3) {
                    getSupportActionBar().setTitle("Profile");
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
                    selectedFragment = new UserFragment();

                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
                }
                return true;
            }
        });

            if (studio != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("studio", studio);
                bundle.putSerializable("service",service);
                selectedFragment = new ChatFragment();
                selectedFragment.setArguments(bundle);
                // Thay thế fragment hiện tại trong "HomeActivity" bằng fragment mới
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            }else {
                // Set the initial fragment as HomeFragment
                selectedFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            }
        //Notification Icon
//        AHNotification notification = new AHNotification.Builder().setText("10").setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.colorAccent)).setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Home_ToolBar)).build();
//        bottomNavigationView.setNotification(notification, 1);
    }
    private Studio loadStudio() {
        if(getIntent().getExtras() != null) {
            Studio studio= (Studio) getIntent().getExtras().get("studio");
            if (studio != null) {
                return studio;
            }
        }
        return null;
    }
    private Service loadService(){
        if(getIntent().getExtras() != null) {
            Service service= (Service) getIntent().getExtras().get("service");
            if (service != null) {
                return service;
            }
        }
        return null;
    }
}