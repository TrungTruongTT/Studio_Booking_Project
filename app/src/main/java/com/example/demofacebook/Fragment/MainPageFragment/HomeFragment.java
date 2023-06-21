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
        sortItemData(view);

        serviceItemData(view);

    }

    private void serviceItemData(@NonNull View view) {
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

    private void sortItemData(@NonNull View view) {
        recyclerViewSort = view.findViewById(R.id.RecyclerSort);
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
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
        Intent intent = new Intent(getActivity(), ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, R.drawable.download, "Studio 1 test", 500, 5, "Title Description", "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n");
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<Service> getServiceData() {
        List<Service> myList = new ArrayList<>();

        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "<p>Please contact me first before starting order.</p><p><br></p><p>Hello, I'm Lana, I will be happy to shoot <strong>professional photo</strong> with your product and my adorable models)</p><p>I specialize in creating professional, high quality and selling product images.&nbsp;I have the&nbsp;models for your product (baby, mom and dad, toddler, and dog).</p><p>I live in Florida, so can use a beautiful nature to create an amazing lifestyle photos. Also I will be happy to create studio high quality photos for your brand.</p><p><br></p><p><strong>What you'll get:</strong></p><ul><li>High quality JPEG image</li><li>Images taken with professional high end SLRs and equipment (inc. Canon 6D mark ii)&nbsp;</li><li>Free image enhancement and editing to polish off the final product.</li></ul><p><br></p><p><strong>If you're an e-commerce seller on platforms such as eBay, Amazon, Shopify or Etsy, then&nbsp;this is the gig for you!</strong></p><p><br></p><p><strong>Why me?&nbsp;</strong>I'm hardworking and always aim for a quality results. You'll get the cheapest deal from me right now whilst I'm building my Fiverr reviews! If you are interested, write to me and we will discuss the details of the order.&nbsp;</p><p><strong>I</strong>&nbsp;<strong>would be</strong>&nbsp;<strong>really</strong>&nbsp;<strong>glad to work with you!&nbsp;</strong></p>", 350, 500));
        myList.add(new Service(2, R.drawable.download, 3, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(3, R.drawable.download, 2, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 5, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 1, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        return myList;
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