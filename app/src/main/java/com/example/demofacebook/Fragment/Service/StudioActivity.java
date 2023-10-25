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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.demofacebook.Adapter.StudioDetail.FeedbackAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.PhotoAdapter;
import com.example.demofacebook.Adapter.StudioDetail.RecommendStudioAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.PickTimeActivity;
import com.example.demofacebook.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudioActivity extends AppCompatActivity {
    private Studio studio;
    private ViewPager viewPager;

    private List<String> photoList;
    private Timer timer;
    //feedback
    private RecyclerView recyclerViewFeedback;
    private FeedbackAdapter feedbackAdapter;
    private List<Feedback> mFeedbackList;
    //service
    private RecyclerView recyclerViewService;
    private RecommendStudioAdapter recommendStudioAdapter;
    private List<Studio> mStudioListRecommend;
    private Context context;
    private Button addToCardbtn;
    private Button buttonFeedback;
    private Button buttonService;
    private Button buttonBooking;

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
        //onClickAddToChat
        addToCardbtn = findViewById(R.id.AddToCartBtn2);
        addToCardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoChat(studio);
            }
        });


        buttonFeedback = findViewById(R.id.ViewMoreFeedbackBtn);
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickViewMoreFeedback();
            }
        });
        //load Feedback list
        loadFeedback();
        //load recommend service list
        callApiGetRecommendServicePack();
        //View more feedback btn

        buttonBooking = findViewById(R.id.btn_Booking);
        buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PickTimeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("studio", studio);
                intent.putExtras(bundle);
                startActivity(intent);
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

    private void onClickGotoChat(Studio studio) {
        Intent intent = new Intent(this, HomeActivity.class);
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
        ApiService.apiService.getAllStudio().enqueue(new Callback<List<Studio>>() {
            @Override
            public void onResponse(Call<List<Studio>> call, Response<List<Studio>> response) {
                if (response.isSuccessful()) {
                    mStudioListRecommend = response.body();
                    if (mStudioListRecommend.size() == 0) {
                        buttonService.setEnabled(false);
                        buttonService.setVisibility(View.INVISIBLE);
                    } else {
                        List<Studio> sort = mStudioListRecommend.stream().skip(0).limit(5).collect(Collectors.toList());
                        recyclerViewService = findViewById(R.id.RecommendServiceRecyclerView);
                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerViewService.setLayoutManager(linearLayoutManager2);
                        recommendStudioAdapter = new RecommendStudioAdapter(sort, new IClickItemServiceListener() {
                            @Override
                            public void onClickItemService(Studio studio) {
                                goDetailService(studio);
                            }
                        });
                        recyclerViewService.setAdapter(recommendStudioAdapter);
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Studio>> call, Throwable t) {
            }
        });
    }

    private void goDetailService(Studio studio) {
        Intent intent = new Intent(this, StudioActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadFeedback() {
        ApiService.apiService.getServiceFeedbackStudioId(studio.getStudioId()).enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful()) {
                    List<Feedback> feedbackList = response.body();
                    if (feedbackList.isEmpty()) {
                        buttonFeedback.setEnabled(false);
                        buttonFeedback.setVisibility(View.INVISIBLE);
                    } else {
                        mFeedbackList = feedbackList.stream().skip(0).limit(3).collect(Collectors.toList());
                        loadFeedbackData(mFeedbackList);
                    }
                } else {
                    Toast.makeText(context, "Load Feedback Fail", Toast.LENGTH_SHORT).show();
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
        feedbackAdapter = new FeedbackAdapter(data);
        recyclerViewFeedback.setAdapter(feedbackAdapter);
    }


    private void loadData() {
        if (getIntent().getExtras() == null) {
            Toast.makeText(context, "Load Content Error Null Object", Toast.LENGTH_SHORT).show();
            return;
        }
        studio = (Studio) getIntent().getExtras().get("studio");
        if (studio == null) {
            Toast.makeText(context, "Load Content Error Null Object", Toast.LENGTH_SHORT).show();
            return;
        } else {
            TextView serviceName = findViewById(R.id.ServiceNameDetail);
            serviceName.setText(studio.getName());
//            TextView servicePrice = findViewById(R.id.ServicePriceDetail);
//            servicePrice.setText("Price: " + formatMoney(studio.getBalance()) + " VND");
            TextView serviceDescription = findViewById(R.id.ServiceDescription);
            serviceDescription.setText(Html.fromHtml(studio.getDescription()));
            TextView locationStudio = findViewById(R.id.location_Studio);
            locationStudio.setText(studio.getAddress());
        }
    }


    private String formatMoney(int Money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Money);
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
        if (studio.getCoverImage() == null) {
            myList.add("https://i.imgur.com/DvpvklR.png");
            myList.add("https://i.imgur.com/DvpvklR.png");
            myList.add("https://i.imgur.com/DvpvklR.png");
            myList.add("https://i.imgur.com/DvpvklR.png");
        } else {
            myList.add(studio.getCoverImage());
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