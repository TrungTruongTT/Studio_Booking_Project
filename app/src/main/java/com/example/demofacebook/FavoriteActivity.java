package com.example.demofacebook;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Favorite.StudioAdapter;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudioAdapter studioAdapter;
    private List<Studio> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        initToolBar();
        recyclerView = (RecyclerView) findViewById(R.id.favoriteRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mList = getListStudioData();
        studioAdapter = new StudioAdapter(mList, this);
        recyclerView.setAdapter(studioAdapter);
    }
    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.favoriteToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_navbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Favorite");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Studio> getListStudioData() {
        List<Studio> myList = new ArrayList<>();
        List<Service> listSevice = new ArrayList<>();
        listSevice.add(new Service(1, R.drawable.download, 4, "Service 1", "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        listSevice.add(new Service(1, R.drawable.download, 4, "Service 1", "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        listSevice.add(new Service(1, R.drawable.download, 4, "Service 1", "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        listSevice.add(new Service(1, R.drawable.download, 4, "Service 1", "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        listSevice.add(new Service(1, R.drawable.download, 4, "Service 1", "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);

        myList.add(new Studio(1, R.drawable.download, "Studio 1 test", 40, 5, "22", listSevice));
        myList.add(new Studio(2, R.drawable.download, "Studio 2 test", 40, 5, "22", listSevice));
        myList.add(new Studio(3, R.drawable.download, "Studio 3 test", 40, 5, "22", listSevice));
        myList.add(new Studio(4, R.drawable.download, "Studio 4 test", 40, 5, "22", listSevice));
        myList.add(new Studio(5, R.drawable.download, "Studio 5 test", 40, 5, "22", listSevice));

        for (Studio s : myList) {
            Log.d("studio", String.valueOf(s.getServiceList().size()));
        }
        return myList;
    }
}