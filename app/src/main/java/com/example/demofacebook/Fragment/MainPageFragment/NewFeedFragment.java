package com.example.demofacebook.Fragment.MainPageFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.NewFeed.MediaAdapter;
import com.example.demofacebook.Model.MediaItem;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;


public class NewFeedFragment extends Fragment {
    private RecyclerView recyclerView;
    private MediaAdapter mediaAdapter;
    private List<MediaItem> mediaItems;
    private List<Service> mServiceList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaItems = getNewFeedData();
        recyclerView = view.findViewById(R.id.NewFeedRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Create and set the adapter


        mediaAdapter = new MediaAdapter(mediaItems, getActivity());
        recyclerView.setAdapter(mediaAdapter);


    }

    private List<MediaItem> getNewFeedData() {
        Studio studio = new Studio(1, "https://i.imgur.com/DvpvklR.png", "Studio 1 test", 500, 5,
                "Description\nDescription\nDescription", null);


        List<MediaItem> myList = new ArrayList<>();

        myList.add(new MediaItem(MediaItem.TYPE_IMAGE_SLIDE, studio, null, getPhotoList(), null));
        myList.add(new MediaItem(MediaItem.TYPE_IMAGE, studio, "https://scontent.fsgn8-4.fna.fbcdn.net/v/t39.30" +
                "808-6/355655582_6488770904512336_2377269232681884105_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=5cd70e&_nc_ohc=QBQFxbg9" +
                "jtYAX_-SrWc&_nc_ht=scontent.fsgn8-4.fna&oh=00_AfCidnOXnjizoc1VUQyt3n5TKV8gs28PbknupshPqNZgiA&oe=649C4C13", null, null));
        myList.add(new MediaItem(MediaItem.TYPE_VIDEO, studio, null, null, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));


        //back up list do not delete
        myList.add(new MediaItem(MediaItem.TYPE_IMAGE_SLIDE, studio, null, getPhotoList(), null));
        myList.add(new MediaItem(MediaItem.TYPE_IMAGE_SLIDE, studio, null, getPhotoList(), null));
        myList.add(new MediaItem(MediaItem.TYPE_IMAGE_SLIDE, studio, null, getPhotoList(), null));
        return myList;
    }


    private List<String> getPhotoList() {
        List<String> myList = new ArrayList<>();
        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add("https://scontent.fsgn8-4.fna.fbcdn.net/v/t39.30808-6/354438956_285849777299856_6037642141648862241_n.jpg?_nc_cat=1&ccb=1-7&_nc_sid=730e14&_nc_ohc=AHnVVgluf54AX8PFbV1&_nc_ht=scontent.fsgn8-4.fna&oh=00_AfAsO85hKxcSpWpdlpf8Lp4ZX_56fE9NZWSEnKVthKUqqQ&oe=649C1618");
        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add("https://i.imgur.com/DvpvklR.png");
        return myList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feed, container, false);
        return view;
    }
}