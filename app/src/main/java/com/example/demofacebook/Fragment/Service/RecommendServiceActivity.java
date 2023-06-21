package com.example.demofacebook.Fragment.Service;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;

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
        recyclerViewService = findViewById(R.id.ListRecommendServiceRecyclerView);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewService.setLayoutManager(linearLayoutManager2);
        recommendService = getServiceData();
        serviceAdapter = new ServiceAdapter(recommendService, new IClickItemServiceListener() {
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
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(2, R.drawable.download, 4, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(3, R.drawable.download, 4, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 4, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 4, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(2, R.drawable.download, 4, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(3, R.drawable.download, 4, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 4, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 4, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(2, R.drawable.download, 4, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(3, R.drawable.download, 4, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 4, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 4, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
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
        Studio studio = new Studio(1, R.drawable.download, "Studio 1", 40, 5);
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