//package com.example.demofacebook.Fragment.Service;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
//import com.example.demofacebook.Adapter.StudioDetail.StudioAdapter;
//import com.example.demofacebook.Api.ApiService;
//import com.example.demofacebook.Model.Studio;
//import com.example.demofacebook.R;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class RecommendServiceActivity extends AppCompatActivity {
//    private Studio studio;
//    List<Studio> recommendStudio;
//    private RecyclerView recyclerViewService;
//    private StudioAdapter studioAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recommend_service);
//        loadData();
//        initToolBar();
//        loadRecommendService();
//    }
//
//    private void loadRecommendService() {
//        ApiService.apiService.getAllStudio().enqueue(new Callback<List<Studio>>() {
//            @Override
//            public void onResponse(Call<List<Studio>> call, Response<List<Studio>> response) {
//                if (response.isSuccessful()) {
//                    List<Studio> responseValue = response.body();
//                    recommendStudio = responseValue;
//                    recyclerViewService = findViewById(R.id.ListRecommendServiceRecyclerView);
//                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//                    recyclerViewService.setLayoutManager(gridLayoutManager);
//                    studioAdapter = new StudioAdapter(recommendStudio, new IClickItemServiceListener() {
//                        @Override
//                        public void onClickItemService(Studio studio) {
//                            goDetailService(studio);
//
//                        }
//                    });
//                    recyclerViewService.setAdapter(studioAdapter);
//                } else {
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Studio>> call, Throwable t) {
//            }
//        });
//    }
//
//    private void goDetailService(Studio studio) {
//        Intent intent = new Intent(this, StudioActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("studio", studio);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//
//    private void loadData() {
//        if (getIntent().getExtras() != null) {
//            studio = (Studio) getIntent().getExtras().get("studio");
//        }
//    }
//
//    private void initToolBar() {
//        Toolbar toolbar;
//        toolbar = findViewById(R.id.ToolBarRecommendService);
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
//            getSupportActionBar().setTitle("Recommend Studio");
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//}