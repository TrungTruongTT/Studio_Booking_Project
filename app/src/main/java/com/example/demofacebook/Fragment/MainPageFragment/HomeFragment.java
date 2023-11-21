package com.example.demofacebook.Fragment.MainPageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.HomePage.SortHomeAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.StudioAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.Service.StudioActivity;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.MyInterface.IClickItemSortListener;
import com.example.demofacebook.R;
import com.example.demofacebook.Search.SearchActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewService;
    private StudioAdapter studioAdapter;
    private List<Studio> mStudioList;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        sortItemData(view);
        studioItemData(view);

        LinearLayout btn_SearchHomeFragment = view.findViewById(R.id.btn_SearchHomeFragment);
        btn_SearchHomeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void studioItemData(@NonNull View view) {
        recyclerViewService = view.findViewById(R.id.RecyclerViewService);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewService.setLayoutManager(gridLayoutManager);
        callApiGetStudio(); // gọi API List Services
    }

//    private void sortItemData(@NonNull View view) {
//        RecyclerView recyclerViewSort = view.findViewById(R.id.RecyclerSort);
//        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
//        recyclerViewSort.setLayoutManager(linearLayoutManagerSort);
//        SortHomeAdapter sortHomeAdapter = new SortHomeAdapter(getSortData(), new IClickItemSortListener() {
//            @Override
//            public void onClickItemSort(String sortBy) {
//                studioAdapter.getFilter().filter("@!" + sortBy);
//            }
//        });
//        recyclerViewSort.setAdapter(sortHomeAdapter);
//    }

    private void goDetailService(Studio studio) {
        Log.d("toString", studio.toString());
        Intent intent = new Intent(getActivity(), StudioActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //lấy API services
    private void callApiGetStudio() {
        ApiService.apiService.getAllStudio().enqueue(new Callback<List<Studio>>() {
            @Override
            public void onResponse(Call<List<Studio>> call, Response<List<Studio>> response) {
                if (response.isSuccessful()) {
                    mStudioList = response.body();
                    studioAdapter = new StudioAdapter(mStudioList, new IClickItemServiceListener() {
                        @Override
                        public void onClickItemService(Studio studio) {
                            goDetailService(studio);
                        }
                    });
                    recyclerViewService.setAdapter(studioAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Studio>> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getSortData() {
        String[] sortList = {"Top Rating"};
        List<String> myList = new ArrayList<>();
        Collections.addAll(myList, sortList);
        return myList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        return view;
    }
}