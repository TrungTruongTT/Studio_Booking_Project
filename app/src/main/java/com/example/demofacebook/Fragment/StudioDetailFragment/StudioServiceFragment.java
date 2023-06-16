package com.example.demofacebook.Fragment.StudioDetailFragment;

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

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.HomePage.StudioPageActivity;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;

public class StudioServiceFragment extends Fragment {
    final private Studio studio;
    private RecyclerView recyclerViewService;
    private ServiceAdapter serviceAdapter;
    private List<Service> mServiceList;

    public StudioServiceFragment(Studio studio) {
        this.studio = studio;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewService = view.findViewById(R.id.ServiceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewService.setLayoutManager(linearLayoutManager);
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
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private List<Service> getServiceData() {
        List<Service> myList = new ArrayList<>();
        myList.add(new Service(1, R.drawable.download, 4,"Service " + studio.getTitle(),
                "", 350, 500));
        myList.add(new Service(2,R.drawable.download, 4, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(3,R.drawable.download, 4, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4,R.drawable.download, 4, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5,R.drawable.download, 4, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        return myList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studio_service, container, false);
        return view;
    }
}