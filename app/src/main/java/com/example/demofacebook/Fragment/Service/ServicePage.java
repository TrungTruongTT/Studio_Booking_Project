package com.example.demofacebook.Fragment.Service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.MainPageFragment.ChatFragment;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.HomePage.StudioPageActivity;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Context context;

    //chatBy Button
    private Button addToCardbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);
        context = getApplicationContext();
        //load Service Page Data (Studio + Service)
        loadData();
        //LoadAppBar
        initToolBar();
        //Slide Image
        slideImage();
        //Auto SlideImages
        autoSlideImages();
        //onClickAddToCart
        addToCardbtn = findViewById(R.id.AddToCartBtn2);
        addToCardbtn.setOnClickListener(view -> {
            addToCardbtn.setBackgroundResource(R.drawable.love_heart_svg);
            //xử lý qua trang chat và lưu trên talkjs ở đây .....
            Intent intent = new Intent(ServicePage.this, HomeActivity.class);
            Bundle bundle = new Bundle();
            ChatFragment fragment = new ChatFragment();
            bundle.putSerializable("studio", service.getStudio());
            intent.putExtras(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            //startActivity(intent);
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
        callApiGetRecommendServicePack();
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
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void onClickViewMoreFeedback() {
        Intent intent = new Intent(this, FeedbackActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void callApiGetRecommendServicePack() {
        ApiService.apiService.serviceCall().enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful()) {
                    mServiceList = response.body();
                    List<Service> sort = mServiceList.stream().skip(0).limit(5).collect(Collectors.toList());
                    recyclerViewService = findViewById(R.id.RecommendServiceRecyclerView);
                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewService.setLayoutManager(linearLayoutManager2);
                    serviceAdapter = new ServiceAdapter(sort, new IClickItemServiceListener() {
                        @Override
                        public void onClickItemService(Service service) {
                            goDetailService(service);

                        }
                    });
                    recyclerViewService.setAdapter(serviceAdapter);


                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
            }
        });
    }

    private void goDetailService(Service service) {
        Intent intent = new Intent(this, ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, "https://i.imgur.com/DvpvklR.png", "Studio 1 test", 500, 5, "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n", null);
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadFeedback() {
        ApiService.apiService.getServiceFeedbackServiceId(service.getServiceId()).enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful()) {
                    List<Feedback> responseValue  = response.body();
                    mFeedbackList = responseValue.stream().skip(0).limit(3).collect(Collectors.toList());
                    loadFeedbackData(mFeedbackList);

                } else {

                }
            }
            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
            }
        });
    }

    private void loadFeedbackData(List<Feedback> data){
        recyclerViewFeedback = findViewById(R.id.FeedbackServiceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFeedback.setLayoutManager(linearLayoutManager);
        feedbackAdapter = new FeedbackAdapter(this, data, new IClickItemFeedbackListener() {
            @Override
            public void onClickItemFeedback(Feedback feedback) {

            }
        });
        recyclerViewFeedback.setAdapter(feedbackAdapter);
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
                ImageView studioImage = findViewById(R.id.StudioAvatarImageService);
                if(studio.getImage() != null){
                    Picasso.get().load(studio.getImage())
                            .error(R.drawable.download)
                            .into(studioImage);
                }else {
                    Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                            .error(R.drawable.download)
                            .into(studioImage);
                }

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
        if (service.getMediaServicePackList() != null) {
            if (service.getMediaServicePackList().size() != 0) {
                for (int i = 0; i < service.getMediaServicePackList().size(); i++) {
                    myList.add(service.getMediaServicePackList().get(i).getFilePath());
                }
                return myList;
            }
            else {
                myList.add("https://i.imgur.com/DvpvklR.png");
                myList.add("https://i.imgur.com/DvpvklR.png");
                myList.add("https://i.imgur.com/DvpvklR.png");
                myList.add("https://i.imgur.com/DvpvklR.png");
            }
        }
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