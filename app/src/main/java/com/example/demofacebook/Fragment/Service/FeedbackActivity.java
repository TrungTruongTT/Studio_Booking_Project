package com.example.demofacebook.Fragment.Service;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.FeedbackAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackListener;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {
    private Studio studio;
    private RecyclerView recyclerViewFeedback;
    private FeedbackAdapter feedbackAdapter;
    private List<Feedback> mFeedbackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        loadData();
        initToolBar();
        loadFeedback();
    }


    private void loadFeedback() {
        recyclerViewFeedback = findViewById(R.id.ListFeedbackServiceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFeedback.setLayoutManager(linearLayoutManager);
        mFeedbackList = getFeedbackData();
        feedbackAdapter = new FeedbackAdapter(this, mFeedbackList, new IClickItemFeedbackListener() {
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
        myList.add(new Feedback(3, R.drawable.download, studio.getTitle(), 5, getString(R.string.feedbackString), R.drawable.download, dateChange));
        myList.add(new Feedback(4, R.drawable.download, studio.getTitle(), 5, getString(R.string.feedbackString), R.drawable.download, dateChange));
        myList.add(new Feedback(5, R.drawable.download, studio.getTitle(), 5, getString(R.string.feedbackString), R.drawable.download, dateChange));
        myList.add(new Feedback(6, R.drawable.download, studio.getTitle(), 5, getString(R.string.feedbackString), R.drawable.download, dateChange));

        return myList;
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
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_navbar));
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