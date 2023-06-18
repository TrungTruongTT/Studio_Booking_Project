package com.example.demofacebook.HomePage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.demofacebook.Adapter.StudioDetail.PhotoAdapter;
import com.example.demofacebook.Adapter.StudioDetail.StudioViewPagerAdapter;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.Model.StudioToolBarPhoto;
import com.example.demofacebook.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class StudioPageActivity extends AppCompatActivity {
    private Studio studio;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<StudioToolBarPhoto> photoList;
    private Timer timer;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private StudioViewPagerAdapter mStudioViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_page);
        //LoadDetail Page
        loadStudioInfo();
        //LoadAppBar
        initToolBar();
        //Slide Image
        viewPager = findViewById(R.id.ViewPager);
        circleIndicator = findViewById(R.id.Circle_Indicator);
        photoList = getPhotoList();
        photoAdapter = new PhotoAdapter(this, photoList);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        //Auto SlideImages
        autoSlideImages();
        //Fragment
        mTabLayout = findViewById(R.id.TabLayout_Studio);
        mViewPager = findViewById(R.id.ViewPager2_Studio);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mViewPager.addItemDecoration(dividerItemDecoration);
        mStudioViewPagerAdapter = new StudioViewPagerAdapter(this, studio);
        mViewPager.setAdapter(mStudioViewPagerAdapter);

        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Gallery");
                    break;
                case 1:
                    tab.setText("Service");
                    break;
                case 2:
                    tab.setText("Feedback");
                    break;
            }
        }).attach();
    }

    private List<StudioToolBarPhoto> getPhotoList() {
        List<StudioToolBarPhoto> myList = new ArrayList<>();
        myList.add(new StudioToolBarPhoto(R.drawable.download));
        myList.add(new StudioToolBarPhoto(R.drawable.download));
        myList.add(new StudioToolBarPhoto(R.drawable.download));
        myList.add(new StudioToolBarPhoto(R.drawable.download));
        myList.add(new StudioToolBarPhoto(R.drawable.download));
        return myList;
    }

    private void loadStudioInfo() {
        if (getIntent().getExtras() != null) {
            studio = (Studio) getIntent().getExtras().get("studio");
            if(studio == null){
                Toast.makeText(this, "LoadStudio Fail", Toast.LENGTH_SHORT).show();
                return;
            }
            TextView studioName = findViewById(R.id.StudioName);
            studioName.setText(studio.getTitle());

            TextView studioRating = findViewById(R.id.StudioRating);
            String rating = "⭐: " + String.valueOf(studio.getRating());
            studioRating.setText(rating);
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
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

    private void autoSlideImages() {
        if (photoList == null || photoList.isEmpty() || viewPager == null) {
            return;
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler((Looper.getMainLooper())).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = photoList.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 10000, 10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}