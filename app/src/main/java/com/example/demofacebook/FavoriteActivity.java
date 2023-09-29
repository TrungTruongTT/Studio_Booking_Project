package com.example.demofacebook;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Favorite.FavoriteAdapter;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
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
        favoriteAdapter = new FavoriteAdapter(mList, this);
        recyclerView.setAdapter(favoriteAdapter);

    }
    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.favoriteToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
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
        listSevice.add(new Service(1, R.drawable.placeholder_image, 4, "Service 1", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        listSevice.add(new Service(1, R.drawable.placeholder_image, 4, "Service 2", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        listSevice.add(new Service(1, R.drawable.placeholder_image, 4, "Service 3", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        listSevice.add(new Service(1, R.drawable.placeholder_image, 4, "Service 4", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        listSevice.add(new Service(1, R.drawable.placeholder_image, 4, "Service 5", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);

        List<Service> listSevice2 = new ArrayList<>();
        listSevice2.add(new Service(1, R.drawable.placeholder_image, 4, "Service 1", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        listSevice2.add(new Service(1, R.drawable.placeholder_image, 4, "Service 2", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        listSevice2.add(new Service(1, R.drawable.placeholder_image, 4, "Service 3", "Service Description 1\nService Description 2\nService Description 3", 350, 500, 0));
        myList.add(new Studio(1, "https://i.imgur.com/DvpvklR.png", "Studio 1 test", 40, 5, "22", null, listSevice));
        myList.add(new Studio(2, "https://i.imgur.com/DvpvklR.png", "Studio 2 test", 40, 5, "22", null, listSevice2));


        for (Studio s : myList) {
            Log.d("studio", String.valueOf(s.getServiceList().size()));
        }
        return myList;
    }
}