package com.example.demofacebook.Search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.HomePage.StudioHomeAdapter;
import com.example.demofacebook.HomePage.StudioPageActivity;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.MyInterface.IClickItemStudioListener;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    SearchView searchView;

    private RecyclerView recyclerViewStudio;
    private StudioHomeAdapter studioHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //ToolBar
        toolbar = findViewById(R.id.myToolBarSearch);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search View");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Search_ToolBar)));
        //studioList
        recyclerViewStudio = findViewById(R.id.RecyclerViewStudioSearch);
        studioHomeAdapter = new StudioHomeAdapter(getStudioData(), new IClickItemStudioListener() {
            @Override
            public void onClickItemStudio(Studio studio) {
                onClickItemGoDetail(studio);
            }
        });
        LinearLayoutManager linearLayoutManagerStudio = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewStudio.setLayoutManager(linearLayoutManagerStudio);
        recyclerViewStudio.setAdapter(studioHomeAdapter);
        recyclerViewStudio.setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                studioHomeAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewStudio.setVisibility(View.VISIBLE);
                studioHomeAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private List<Studio> getStudioData() {
        int[] image = {R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download};
        String[] studioName = {"Studio Name 1", "Studio Name 2", "Studio Name 3", "Studio Name 4", "Studio Name 5", "Studio Name 6"};
        String[] studioDescription = {"Studio Description 1: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sed semper elit. Donec at luctus felis, id faucibus nibh. Aliquam.", "Studio Description 2", "Studio Description 3", "Studio Description 4", "Studio Description 5", "Studio Description 6"};
        Integer[] price = {500, 400, 300, 500, 100, 500};
        Integer[] rating = {2, 1, 2, 4, 5, 6};
        List<Studio> myList = new ArrayList<>();

        for (int i = 0; i < studioName.length; i++) {
            myList.add(new Studio(image[i], studioName[i], studioDescription[i], price[i], rating[i]));
            myList.add(new Studio(image[i], studioName[i], studioDescription[i], price[i], rating[i]));
        }

        return myList;
    }

    private void onClickItemGoDetail(Studio studio) {
        Intent intent = new Intent(this, StudioPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}