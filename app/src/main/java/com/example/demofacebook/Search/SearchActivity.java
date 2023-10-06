package com.example.demofacebook.Search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    SearchView searchView;
    private RecyclerView recyclerViewStudio;
    private ServiceAdapter serviceAdapter;
    private List<Service> mListService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = findViewById(R.id.myToolBarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search Studio");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));

        //studioList
        recyclerViewStudio = findViewById(R.id.RecyclerViewStudioSearch);
        ApiService.apiService.serviceCall().enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful()) {
                    mListService = response.body();
                    serviceAdapter = new ServiceAdapter(mListService, new IClickItemServiceListener() {
                        @Override
                        public void onClickItemService(Service Service) {
                            onClickItemGoDetail(Service);
                        }
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                    recyclerViewStudio.setLayoutManager(gridLayoutManager);
                    recyclerViewStudio.setAdapter(serviceAdapter);
                    recyclerViewStudio.setVisibility(View.GONE);

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        EditText txtSearch  = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setTextColor(Color.BLACK);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                serviceAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewStudio.setVisibility(View.VISIBLE);
                serviceAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    private void onClickItemGoDetail(Service service) {
        Intent intent = new Intent(this, ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = service.getStudio();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}