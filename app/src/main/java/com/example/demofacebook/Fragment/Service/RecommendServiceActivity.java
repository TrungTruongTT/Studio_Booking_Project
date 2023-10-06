package com.example.demofacebook.Fragment.Service;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendServiceActivity extends AppCompatActivity {
    private Studio studio;
    List<Service> recommendService;
    private RecyclerView recyclerViewService;
    private ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_service);
        loadData();
        initToolBar();
        loadRecommendService();
    }

    private void loadRecommendService() {
        ApiService.apiService.getServiceByStudioId(studio.getStudioId()).enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful()) {
                    List<Service> responseValue  = response.body();
                    recommendService = responseValue;
                    recyclerViewService = findViewById(R.id.ListRecommendServiceRecyclerView);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                    recyclerViewService.setLayoutManager(gridLayoutManager);
                    serviceAdapter = new ServiceAdapter(recommendService, new IClickItemServiceListener() {
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
//        Studio studio = new Studio(1, "https://i.imgur.com/DvpvklR.png", "Studio 1 test", 500, 5, "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n", null);
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void loadData() {
        if (getIntent().getExtras() != null) {
            studio = (Studio) getIntent().getExtras().get("studio");
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarRecommendService);
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