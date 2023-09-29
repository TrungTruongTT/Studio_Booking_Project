package com.example.demofacebook.Fragment.StudioDetailFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.GalleryAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemGalleryListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.Gallery;
import com.example.demofacebook.Model.GalleryItem;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ApiService.apiService.getGalleryByStudioId(studio.getStudioId()).enqueue(new Callback<List<Gallery>>() {
            @Override
            public void onResponse(Call<List<Gallery>> call, Response<List<Gallery>> response) {
                if (response.isSuccessful()) {
                    mGalleryList = response.body();
                    loadGalleryData(mGalleryList);

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Gallery>> call, Throwable t) {

            }
        });
    }

    private void loadGalleryData(List<Gallery> value) {
        recyclerViewGallery = findViewById(R.id.ListGalleryServiceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewGallery.setLayoutManager(linearLayoutManager);
        galleryAdapter = new GalleryAdapter(value, new IClickItemGalleryListener() {
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
        List<GalleryItem> a = new ArrayList<>();
        a.add(new GalleryItem(1, "https://firebasestorage.googleapis.com/v0/b/framemates-363d4.appspot.com/o/album%2Ff5fcd6c1-22b0-4ffb-aedb-873599093062.jpeg?alt=media&token=731c6d42-8008-409d-8caa-7de439d78909"));

        myList.add(new Gallery(1, "Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(2, "Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(3, "Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(4, "Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(5, "Gallery Item " + studio.getTitle(), dateChange, a));
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
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
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