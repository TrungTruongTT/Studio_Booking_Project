package com.example.demofacebook.Adapter.Favorite.BookingPageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.Interface.Booking.OrderAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.OrderInformation;
import com.example.demofacebook.OrderDetailActivity;
import com.example.demofacebook.R;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduledFragment extends Fragment {
    private RecyclerView recyclerViewOrder;
    private OrderAdapter orderAdapter;
    private List<OrderInformation> orderList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiService.apiService.getOrderByUser().enqueue(new Callback<List<OrderInformation>>() {
            @Override
            public void onResponse(Call<List<OrderInformation>> call, Response<List<OrderInformation>> response) {
                if (response.isSuccessful()) {
                    List<OrderInformation> value = response.body();
                    orderList = value.stream().filter(p->p.getStatus().equals("pending")).collect(Collectors.toList());
                    loadBookingData(view, orderList);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<OrderInformation>> call, Throwable t) {

            }
        });
    }

    private void loadBookingData(@NonNull View view, List<OrderInformation> value) {
        recyclerViewOrder = view.findViewById(R.id.OrderScheduleRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(value, getContext(), new IClickItemOrderListener() {
            @Override
            public void onClickItemOrder(OrderInformation orderInformation) {
                Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("orderInformation", orderInformation);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerViewOrder.setAdapter(orderAdapter);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scheduled, container, false);
        return view;
    }

}