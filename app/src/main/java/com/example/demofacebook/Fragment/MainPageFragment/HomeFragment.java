package com.example.demofacebook.Fragment.MainPageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    //Studio
//    private RecyclerView recyclerViewStudio;
//    private StudioHomeAdapter studioHomeAdapter;

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

    }

    private void serviceItemData(@NonNull View view) {
        recyclerViewService = view.findViewById(R.id.RecyclerViewService);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewService.setLayoutManager(linearLayoutManager2);
        //hàm set đổ API lên RCVIEW

        DividerItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewService.addItemDecoration(itemDecoration);

        callApiGetServicePack(); // gọi API List Services
        //mServiceList = getServiceData();
        serviceAdapter = new ServiceAdapter(mServiceList, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Service service) {
                goDetailService(service);
                Toast.makeText(getActivity(), String.valueOf(service.getServiceId()), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewService.setAdapter(serviceAdapter);
    }

    private void sortItemData(@NonNull View view) {
        recyclerViewSort = view.findViewById(R.id.RecyclerSort);
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewSort.setLayoutManager(linearLayoutManagerSort);
        sortHomeAdapter = new SortHomeAdapter(getSortData(), new IClickItemSortListener() {
            @Override
            public void onClickItemSort(String sortBy) {
                Toast.makeText(getActivity(), sortBy, Toast.LENGTH_SHORT).show();
                serviceAdapter.getFilter().filter("@!" + sortBy);
            }
        });
        recyclerViewSort.setAdapter(sortHomeAdapter);
    }

    private void goDetailService(Service service) {
        Intent intent = new Intent(getActivity(), ServicePage.class); // qua trang servicePage
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);

        Toast.makeText(getContext(), Integer.valueOf(service.getStudio().getStudioId()).toString(), Toast.LENGTH_SHORT).show();
        Studio studio = new Studio(service.getStudio().getStudioId(), service.getStudio().getImage(), service.getStudio().getTitle()
                , service.getStudio().getRating(), service.getStudio().getDescription(), null);
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
                            Toast.makeText(getContext(), "StudioId" + service.getStudio().getStudioId(), Toast.LENGTH_SHORT).show();
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