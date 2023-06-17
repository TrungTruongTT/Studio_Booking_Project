package com.example.demofacebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.demofacebook.Adapter.Favorite.StudioAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
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
        listSevice.add(new Service(1, 1, 3, "String serviceName", 100000, 3));
        listSevice.add(new Service(2, 1, 3, "String serviceName", 100000, 3));
        listSevice.add(new Service(3, 1, 3, "String serviceName", 100000, 3));
        listSevice.add(new Service(4, 1, 3, "String serviceName", 100000, 3));
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Studio(1, 1, "String title", 3, listSevice));
        myList.add(new Studio(2, 1, "String title", 3, listSevice));
        myList.add(new Studio(3, 1, "String title", 3, listSevice));
        myList.add(new Studio(4, 1, "String title", 3, listSevice));
        myList.add(new Studio(5, 1, "String title", 3, listSevice));
        for (Studio s: myList) {
            Log.d("studio", String.valueOf(s.getServiceList().size()));
        }
        return myList;
    }
}