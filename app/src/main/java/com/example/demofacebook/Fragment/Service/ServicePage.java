package com.example.demofacebook.Fragment.Service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.demofacebook.Adapter.StudioDetail.FeedbackAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.PhotoAdapter;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.HomePage.StudioPageActivity;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ServicePage extends AppCompatActivity {
    private Studio studio;
    private Service service;
    private ViewPager viewPager;
    private List<String> photoList;
    private Timer timer;
    //feedback
    private RecyclerView recyclerViewFeedback;
    private FeedbackAdapter feedbackAdapter;
    private List<Feedback> mFeedbackList;
    //service
    private RecyclerView recyclerViewService;
    private ServiceAdapter serviceAdapter;
    private List<Service> mServiceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);
        //load Service Page Data (Studio + Service)
        loadData();
        //LoadAppBar
        initToolBar();
        //Slide Image
        slideImage();
        //Auto SlideImages
        autoSlideImages();
        //onClickAddToCart
        Button button = findViewById(R.id.AddToCartBtn);
        button.setOnClickListener(view -> {
            Toast.makeText(ServicePage.this, String.valueOf(service.getServiceId()), Toast.LENGTH_SHORT).show();
            button.setBackgroundResource(R.drawable.love_heart_svg);
        });
        //Click on studio
        LinearLayout linearLayout = findViewById(R.id.userLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemGoStudioDetail(studio);
            }
        });
        //load Feedback list
        loadFeedback();
        //load recommend service list
        loadRecommendService();
        //View more feedback btn
        Button buttonFeedback = findViewById(R.id.ViewMoreFeedbackBtn);
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickViewMoreFeedback();
            }
        });
        //view more recommend service btn
        Button buttonService = findViewById(R.id.ViewMoreRecommendServiceBtn);
        buttonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickViewMoreService();
            }
        });
    }

    private void slideImage() {
        viewPager = findViewById(R.id.ViewPagerService);
        CircleIndicator circleIndicator = findViewById(R.id.Circle_Indicator_Service);
        photoList = getPhotoList();
        PhotoAdapter photoAdapter = new PhotoAdapter(this, photoList);
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private void onClickItemGoStudioDetail(Studio studio) {
        Intent intent = new Intent(this, StudioPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void onClickViewMoreService() {
        Intent intent = new Intent(this, RecommendServiceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, R.drawable.download, "Studio 1 test", 500, 5,
                "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n");
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void onClickViewMoreFeedback() {
        Intent intent = new Intent(this, FeedbackActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, R.drawable.download, "Studio 1 test", 500, 5, "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n");
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadRecommendService() {
        recyclerViewService = findViewById(R.id.RecommendServiceRecyclerView);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewService.setLayoutManager(linearLayoutManager2);
        mServiceList = getServiceData();
        serviceAdapter = new ServiceAdapter(mServiceList, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Service service) {
                goDetailService(service);
                Toast.makeText(getApplicationContext(), String.valueOf(service.getServiceId()), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewService.setAdapter(serviceAdapter);
    }

    private List<Service> getServiceData() {
        List<Service> myList = new ArrayList<>();
        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "Service Description 1", 350, 500));
        myList.add(new Service(2, R.drawable.download, 4, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(3, R.drawable.download, 4, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 4, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 4, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        return myList;
    }

    private void goDetailService(Service service) {
        Intent intent = new Intent(this, ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, R.drawable.download, "Studio 1 test", 500, 5, "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n");
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadFeedback() {
        recyclerViewFeedback = findViewById(R.id.FeedbackServiceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFeedback.setLayoutManager(linearLayoutManager);
        mFeedbackList = getFeedbackData();
        feedbackAdapter = new FeedbackAdapter(this ,mFeedbackList, new IClickItemFeedbackListener() {
            @Override
            public void onClickItemFeedback(Feedback feedback) {
                Toast.makeText(getApplicationContext(), feedback.getFeedbackUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewFeedback.setAdapter(feedbackAdapter);
    }

    private List<Feedback> getFeedbackData() {
        List<Feedback> myList = new ArrayList<>();
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Feedback(1, R.drawable.download, studio.getTitle(), 5, getString(R.string.feedbackString), R.drawable.download, dateChange));
        myList.add(new Feedback(2, R.drawable.download, studio.getTitle(), 5, getString(R.string.feedbackString), R.drawable.download, dateChange));
        return myList;
    }

    private void loadData() {
        if (getIntent().getExtras() == null) {
            return;
        }
        if (getIntent().getExtras() != null) {
            studio = (Studio) getIntent().getExtras().get("studio");
            if (studio == null) {
                return;
            } else {
                TextView studioName = findViewById(R.id.StudioName);
                studioName.setText(studio.getTitle());
                TextView studioRating = findViewById(R.id.StudioRating);
                String rating = "⭐: " + String.valueOf(studio.getRating());
                studioRating.setText(rating);
            }
            service = (Service) getIntent().getExtras().get("service");
            if (service == null) {
                return;
            } else {
                TextView serviceName = findViewById(R.id.ServiceNameDetail);
                serviceName.setText(service.getServiceName());
                TextView servicePrice = findViewById(R.id.ServicePriceDetail);
                servicePrice.setText("Price: " + String.valueOf(service.getPriceService()) + "$");
                TextView serviceDiscount = findViewById(R.id.ServicePriceDiscountDetail);
                serviceDiscount.setText("Discount: " + String.valueOf(service.getPriceService() + "$"));
                TextView serviceDescription = findViewById(R.id.ServiceDescription);
                serviceDescription.setText(Html.fromHtml(service.getServiceDescription()));

            }
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

    private List<String> getPhotoList() {
        List<String> myList = new ArrayList<>();
        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add("https://i.imgur.com/DvpvklR.png");
        return myList;
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