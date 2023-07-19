package com.example.demofacebook.Fragment.StudioDetailFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.FeedbackAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.Service.FeedbackActivity;
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
import retrofit2.http.Query;

public class StudioFeedbackFragment extends Fragment {
    final private Studio studio;
    private RecyclerView recyclerViewFeedback;
    private FeedbackAdapter feedbackAdapter;
    private List<Feedback> mFeedbackList;

    public StudioFeedbackFragment(Studio studio) {
        this.studio = studio;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewFeedback = view.findViewById(R.id.FeedbackRecyclerView);

        loadFeedbackStudio();

        Button button = view.findViewById(R.id.ViewMoreFeedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickViewMoreFeedback();
            }
        });
    }

    private void loadFeedbackStudio() {
        ApiService.apiService.getServiceFeedbackStudioId(studio.getStudioId()).enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful()) {
                    List<Feedback> responseValue  = response.body();
                    mFeedbackList = responseValue.stream().skip(0).limit(4).collect(Collectors.toList());
                    loadFeedbackData(mFeedbackList);
                } else {
                    Toast.makeText(getContext(), "Load Data Fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
                Toast.makeText(getContext(), "Lost Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFeedbackData(List<Feedback> data){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFeedback.setLayoutManager(linearLayoutManager);
        mFeedbackList = data;
        feedbackAdapter = new FeedbackAdapter(getActivity() ,mFeedbackList, new IClickItemFeedbackListener() {
            @Override
            public void onClickItemFeedback(Feedback feedback) {
                Toast.makeText(getActivity(), feedback.getFeedbackUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewFeedback.setAdapter(feedbackAdapter);
    }



    private void onClickViewMoreFeedback() {
        Intent intent = new Intent(getActivity(), FeedbackActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studio_feedback, container, false);
        return view;
    }
}