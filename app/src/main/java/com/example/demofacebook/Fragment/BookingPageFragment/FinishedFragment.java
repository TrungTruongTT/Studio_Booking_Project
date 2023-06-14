package com.example.demofacebook.Fragment.BookingPageFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.GalleryAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemGalleryListener;
import com.example.demofacebook.Model.Gallery;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class FinishedFragment extends Fragment {

    private RecyclerView recyclerViewGallery;
    private GalleryAdapter galleryAdapter;
    private List<Gallery> mGalleryList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        recyclerViewGallery = view.findViewById(R.id.GalleryRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewGallery.setLayoutManager(linearLayoutManager);
        mGalleryList = getGalleryData();
        galleryAdapter = new GalleryAdapter(mGalleryList, new IClickItemGalleryListener() {
            @Override
            public void onClickItemGallery(Gallery gallery) {
                Toast.makeText(getActivity(), gallery.getGalleryName(), Toast.LENGTH_SHORT).show();
            }
        });
        //.setAdapter(galleryAdapter);


    }

    private List<Gallery> getGalleryData() {
        List<Gallery> myList = new ArrayList<>();
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Gallery(1, R.drawable.download, "Gallery Item ", dateChange, 120));
        myList.add(new Gallery(2, R.drawable.download, "Gallery Item ", dateChange, 120));
        myList.add(new Gallery(3, R.drawable.download, "Gallery Item ", dateChange, 120));
        myList.add(new Gallery(4, R.drawable.download, "Gallery Item ", dateChange, 120));
        myList.add(new Gallery(5, R.drawable.download, "Gallery Item ", dateChange, 120));
        return myList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished, container, false);
        return view;
    }
}