package com.example.demofacebook.Fragment.StudioDetailFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudioGalleryFragment extends Fragment {
    private final Studio studio;
    private RecyclerView recyclerViewGallery;
    private GalleryAdapter galleryAdapter;
    private List<Gallery> mGalleryList;

    public StudioGalleryFragment(Studio studio) {
        this.studio = studio;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ApiService.apiService.getGalleryByStudioId(studio.getStudioId()).enqueue(new Callback<List<Gallery>>() {
            @Override
            public void onResponse(Call<List<Gallery>> call, Response<List<Gallery>> response) {
                if (response.isSuccessful()) {
                    List<Gallery> responseValue = response.body();
                    mGalleryList = responseValue.stream().skip(0).limit(5).collect(Collectors.toList());
                    loadGalleryData(view, mGalleryList);

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Gallery>> call, Throwable t) {

            }
        });

        Button button = view.findViewById(R.id.ViewMoreGallery);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickViewMoreGallery();
            }
        });
    }

    private void loadGalleryData(@NonNull View view, List<Gallery> value) {
        recyclerViewGallery = view.findViewById(R.id.GalleryRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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
        Intent intent = new Intent(getActivity(), GalleryItemActivity.class);
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
        myList.add(new Gallery(1,"Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(2,"Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(3, "Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(4, "Gallery Item " + studio.getTitle(), dateChange, a));
        myList.add(new Gallery(5, "Gallery Item " + studio.getTitle(), dateChange, a));
        return myList;
    }

    private void onClickViewMoreGallery() {
        Intent intent = new Intent(getActivity(), GalleryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studio_gallery, container, false);
        return view;
    }
}