package com.example.demofacebook.Fragment.StudioDetailFragment;

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
        mServiceList = getGalleryData();
        serviceAdapter = new ServiceAdapter(mServiceList, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Service Service) {
                Toast.makeText(getActivity(), Service.getServiceName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewService.setAdapter(serviceAdapter);

    }

    private List<Service> getGalleryData() {
        List<Service> myList = new ArrayList<>();
        myList.add(new Service(R.drawable.download, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350));
        myList.add(new Service(R.drawable.download, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350));
        myList.add(new Service(R.drawable.download, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350));
        myList.add(new Service(R.drawable.download, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350));
        myList.add(new Service(R.drawable.download, "Service " + studio.getTitle(),
                "Service Description 1\nService Description 2\nService Description 3", 350));
        return myList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studio_service, container, false);
        return view;
    }
}