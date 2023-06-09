package com.example.demofacebook.Fragment.StudioDetailFragment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.GalleryItemAdapter;
import com.example.demofacebook.Model.Gallery;
import com.example.demofacebook.Model.GalleryItem;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryItemActivity extends AppCompatActivity {
    private Gallery gallery;
    private RecyclerView recyclerViewGallery;
    private GalleryItemAdapter galleryAdapter;
    private List<GalleryItem> mGalleryItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_item);
        loadData();
        initToolBar();
        loadGalleyItem();

    }

    private void loadGalleyItem() {
        recyclerViewGallery = findViewById(R.id.ListGalleryItemRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewGallery.setLayoutManager(gridLayoutManager);
        mGalleryItemList = getGalleryItemData();
        galleryAdapter = new GalleryItemAdapter(this, mGalleryItemList);
        recyclerViewGallery.setAdapter(galleryAdapter);
    }

    private List<GalleryItem> getGalleryItemData() {
        List<GalleryItem> myList = new ArrayList<>();
        myList.add(new GalleryItem(1, R.drawable.download));
        myList.add(new GalleryItem(2, R.drawable.download));
        myList.add(new GalleryItem(3, R.drawable.download));
        myList.add(new GalleryItem(4, R.drawable.download));
        myList.add(new GalleryItem(5, R.drawable.download));
        myList.add(new GalleryItem(6, R.drawable.download));
        myList.add(new GalleryItem(7, R.drawable.download));


        return myList;
    }

    private void loadData() {
        if (getIntent().getExtras() != null) {
            gallery = (Gallery) getIntent().getExtras().get("gallery");
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarGalleryItemService);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_navbar));
            getSupportActionBar().setTitle(gallery.getGalleryName());
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