package com.example.demofacebook.Fragment.MainPageFragment;

import android.content.Intent;
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

import com.example.demofacebook.Adapter.HomePage.SortHomeAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.MyInterface.IClickItemSortListener;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
        recyclerViewSort = view.findViewById(R.id.RecyclerSort);
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewSort.setLayoutManager(linearLayoutManagerSort);
        sortHomeAdapter = new SortHomeAdapter(getSortData(), new IClickItemSortListener() {
            @Override
            public void onClickItemSort(String sortBy) {
//                Toast.makeText(getActivity(), sortBy, Toast.LENGTH_SHORT).show();
//                studioHomeAdapter.getFilter().filter("@!" + sortBy);

            }
        });
        recyclerViewSort.setAdapter(sortHomeAdapter);

        recyclerViewService = view.findViewById(R.id.RecyclerViewService);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewService.setLayoutManager(linearLayoutManager2);
        mServiceList = getServiceData();
        serviceAdapter = new ServiceAdapter(mServiceList, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Service service) {
                goDetailService(service);
                Toast.makeText(getActivity(), String.valueOf(service.getServiceId()), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewService.setAdapter(serviceAdapter);

    }

    private void goDetailService(Service service) {
        Intent intent = new Intent(getActivity(), ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, R.drawable.download, "Studio 1 test", 40, 5);
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

//    private List<Studio> getStudioData() {
//        List<Studio> myList = new ArrayList<>();
//        int[] id = {1, 2, 3, 4, 5, 6};
//        int[] image = {R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download};
//        String[] studioName = {"Studio Name 1", "Studio Name 2", "Studio Name 3", "Studio Name 4", "Studio Name 5", "Studio Name 6"};
//        Integer[] totalAlbum = {2, 1, 2, 4, 5, 6};
//        Integer[] rating = {2, 1, 2, 4, 5, 6};
//        for (int i = 0; i < studioName.length; i++) {
//            myList.add(new Studio(id[i], image[i], studioName[i], totalAlbum[i], rating[i]));
//            myList.add(new Studio(id[i], image[i], studioName[i], totalAlbum[i], rating[i]));
//        }
//        return myList;
//    }

    private List<Service> getServiceData() {
        List<Service> myList = new ArrayList<>();
        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(2, R.drawable.download, 4, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(3, R.drawable.download, 4, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 4, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 4, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        return myList;
    }

    private List<String> getSortData() {
        String[] sortList = {"All", "Rating", "Price", "Newest", "Famous", "Sort 6"};
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