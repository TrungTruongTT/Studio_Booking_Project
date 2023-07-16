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

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.Service.FeedbackActivity;
import com.example.demofacebook.Fragment.Service.RecommendServiceActivity;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.HomePage.StudioPageActivity;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ApiService.apiService.getServiceByStudioId(studio.getStudioId()).enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful()) {
                    mServiceList = response.body();
                    List<Service> sort = mServiceList.stream().skip(0).limit(6).collect(Collectors.toList());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerViewService.setLayoutManager(linearLayoutManager);
                    serviceAdapter = new ServiceAdapter(sort, new IClickItemServiceListener() {
                        @Override
                        public void onClickItemService(Service service) {
                            goDetailService(service);

                        }
                    });
                    recyclerViewService.setAdapter(serviceAdapter);
                    Button button = view.findViewById(R.id.ViewMoreStudioService);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickViewMoreService();
                        }
                    });

                } else {

                }
            }
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
            }
        });

    }

    private void goDetailService(Service service) {
        Intent intent = new Intent(getActivity(), ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void onClickViewMoreService() {
        Intent intent = new Intent(getActivity(), RecommendServiceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studio_service, container, false);
        return view;
    }
}