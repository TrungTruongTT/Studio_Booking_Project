package com.example.demofacebook.HomePage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.demofacebook.Fragment.MainPageFragment.BookingFragment;
import com.example.demofacebook.Fragment.MainPageFragment.ChatFragment;
import com.example.demofacebook.Fragment.MainPageFragment.HomeFragment;
import com.example.demofacebook.Fragment.MainPageFragment.UserFragment;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

public class HomeActivity extends AppCompatActivity {
    private Fragment selectedFragment = null;
    private Toolbar toolbar;
    private Studio studio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        studio = loadStudio();
        loadBottomNavigationView();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void loadBottomNavigationView() {
        AHBottomNavigation bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //Define Items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.action_home, R.drawable.home_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.action_chat, R.drawable.chat_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.action_booking, R.drawable.shopping_cart_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.action_user, R.drawable.account_circle_white_48dp, R.color.ToolBar);
        // Add items
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.addItem(item3);
        bottomNavigationView.addItem(item4);

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
                selectedFragment = new ChatFragment();
                selectedFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            }else {
                selectedFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            }
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

}