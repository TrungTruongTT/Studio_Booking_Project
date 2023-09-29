package com.example.demofacebook.Fragment.Service;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.FeedbackAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {
    private Studio studio;
    private RecyclerView recyclerViewFeedback;
    private FeedbackAdapter feedbackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        loadData();
        initToolBar();
        loadFeedbackStudio();
    }


    private void loadFeedbackStudio() {
        ApiService.apiService.getServiceFeedbackStudioId(studio.getStudioId()).enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful()) {
                    List<Feedback> mFeedbackList  = response.body();
                    loadFeedback(mFeedbackList);
                } else {

                }
            }
            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {

            }
        });
    }


    private void loadFeedback(List<Feedback> responseValue) {
        recyclerViewFeedback = findViewById(R.id.ListFeedbackServiceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFeedback.setLayoutManager(linearLayoutManager);
        feedbackAdapter = new FeedbackAdapter(this, responseValue, new IClickItemFeedbackListener() {
            @Override
            public void onClickItemFeedback(Feedback feedback) {
                Toast.makeText(getApplicationContext(), feedback.getFeedbackUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewFeedback.setAdapter(feedbackAdapter);
    }


    private void loadData() {
        if (getIntent().getExtras() != null) {
            studio = (Studio) getIntent().getExtras().get("studio");
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarFeedbackService);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
            getSupportActionBar().setTitle(studio.getTitle());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}