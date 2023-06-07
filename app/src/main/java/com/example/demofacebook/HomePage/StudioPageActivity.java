package com.example.demofacebook.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.demofacebook.Adapter.PhotoAdapter;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.Model.StudioToolBarPhoto;
import com.example.demofacebook.R;

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
            studio = (Studio) getIntent().getExtras().get("Studio");
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