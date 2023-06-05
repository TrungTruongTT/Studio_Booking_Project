package com.example.demofacebook.Search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.demofacebook.HomePage.StudioHomeAdapter;
import com.example.demofacebook.Model.Studio;
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
        toolbar.setTitle("Search View");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //studioList
        recyclerViewStudio = findViewById(R.id.RecyclerViewStudioSearch);
        studioHomeAdapter = new StudioHomeAdapter(getStudioData());
        LinearLayoutManager linearLayoutManagerStudio = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewStudio.setLayoutManager(linearLayoutManagerStudio);
        recyclerViewStudio.setAdapter(studioHomeAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
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
        String[] price = {"Price: 500$", "Price: 500$", "Price: 500$", "Price: 500$", "Price: 500$", "Price: 500$"};
        String[] rating = {"⭐: 5.0", "⭐: 5.0", "⭐: 5.0", "⭐: 5.0", "⭐: 5.0", "⭐: 5.0"};
        List<Studio> myList = new ArrayList<>();

        for (int i = 0; i < studioName.length; i++) {
            myList.add(new Studio(image[i], studioName[i], studioDescription[i], price[i], rating[i]));
            myList.add(new Studio(image[i], studioName[i], studioDescription[i], price[i], rating[i]));
        }

        return myList;
    }
}