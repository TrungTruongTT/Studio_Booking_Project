package com.example.demofacebook.Fragment.MainPageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.HomePage.SortHomeAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.Model.Service;
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
    private ServiceAdapter serviceAdapter;
    private List<Service> mServiceList;
    //sort
    private RecyclerView recyclerViewSort;
    private SortHomeAdapter sortHomeAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Sort list home page
        sortItemData(view);
        serviceItemData(view);
        LinearLayout btn_SearchHomeFragment = view.findViewById(R.id.btn_SearchHomeFragment);
        btn_SearchHomeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void serviceItemData(@NonNull View view) {
        recyclerViewService = view.findViewById(R.id.RecyclerViewService);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewService.setLayoutManager(gridLayoutManager);
        //hàm set đổ API lên RCVIEW
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
//        recyclerViewService.addItemDecoration(itemDecoration);
        callApiGetServicePack(); // gọi API List Services
    }

    private void sortItemData(@NonNull View view) {
        recyclerViewSort = view.findViewById(R.id.RecyclerSort);
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewSort.setLayoutManager(linearLayoutManagerSort);
        sortHomeAdapter = new SortHomeAdapter(getSortData(), new IClickItemSortListener() {
            @Override
            public void onClickItemSort(String sortBy) {
                serviceAdapter.getFilter().filter("@!" + sortBy);
            }
        });
        recyclerViewSort.setAdapter(sortHomeAdapter);
    }

    private void goDetailService(Service service) {
        Intent intent = new Intent(getActivity(), ServicePage.class); // qua trang servicePage
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = service.getStudio();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    //lấy API services
    private void callApiGetServicePack() {
        ApiService.apiService.serviceCall().enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if(response.isSuccessful()){
                    mServiceList = response.body();
                    serviceAdapter = new ServiceAdapter(mServiceList, new IClickItemServiceListener() {
                        @Override
                        public void onClickItemService(Service service) {
                            goDetailService(service);
                        }
                    });
                    recyclerViewService.setAdapter(serviceAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getSortData() {
        String[] sortList = {"Top Rating", "Low Price", "High Price", "Top View", "Low View"};
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