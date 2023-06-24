package com.example.demofacebook.Fragment.StudioDetailFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.GalleryAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemGalleryListener;
import com.example.demofacebook.Model.Gallery;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private Studio studio;
    private RecyclerView recyclerViewGallery;
    private GalleryAdapter galleryAdapter;
    private List<Gallery> mGalleryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        loadData();
        initToolBar();
        loadGallery();
    }

    private void loadGallery() {
        recyclerViewGallery = findViewById(R.id.ListGalleryServiceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewGallery.setLayoutManager(linearLayoutManager);
        mGalleryList = getGalleryData();
        galleryAdapter = new GalleryAdapter(mGalleryList, new IClickItemGalleryListener() {
            @Override
            public void onClickItemGallery(Gallery gallery) {
                onClickGoGalleryDetail(gallery);
            }
        });
        recyclerViewGallery.setAdapter(galleryAdapter);

    }

    private void onClickGoGalleryDetail(Gallery gallery) {
        Intent intent = new Intent(this, GalleryItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("gallery", gallery);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<Gallery> getGalleryData() {
        List<Gallery> myList = new ArrayList<>();
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Gallery(1, R.drawable.download, "Gallery Item " + studio.getTitle(), dateChange, 120));
        myList.add(new Gallery(2, R.drawable.download, "Gallery Item " + studio.getTitle(), dateChange, 120));
        myList.add(new Gallery(3, R.drawable.download, "Gallery Item " + studio.getTitle(), dateChange, 120));
        myList.add(new Gallery(4, R.drawable.download, "Gallery Item " + studio.getTitle(), dateChange, 120));
        myList.add(new Gallery(5, R.drawable.download, "Gallery Item " + studio.getTitle(), dateChange, 120));
        return myList;
    }

    private void loadData() {
        if (getIntent().getExtras() != null) {
            studio = (Studio) getIntent().getExtras().get("studio");
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarGalleryService);
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