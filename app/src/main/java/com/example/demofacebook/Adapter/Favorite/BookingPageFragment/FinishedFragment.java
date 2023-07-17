package com.example.demofacebook.Adapter.Favorite.BookingPageFragment;

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

import com.example.demofacebook.Adapter.Chat.Booking.OrderAdapter;
import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.Interface.IClickItemChatOrderListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.Order;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.OrderInformation;
import com.example.demofacebook.OrderDetailActivity;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FinishedFragment extends Fragment {

    private RecyclerView recyclerViewOrder;
    private OrderAdapter orderAdapter;
    private List<OrderInformation> orderList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiService.apiService.geOrderIdByUser().enqueue(new Callback<List<OrderInformation>>() {
            @Override
            public void onResponse(Call<List<OrderInformation>> call, Response<List<OrderInformation>> response) {
                if (response.isSuccessful()) {
                    List<OrderInformation> value = response.body();
                    orderList = value.stream().filter(p->p.getStatus().equals("worked")).collect(Collectors.toList());
                    loadBookingData(view, orderList);
                } else {
                    Toast.makeText(getContext(), "k dc", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<OrderInformation>> call, Throwable t) {
                Toast.makeText(getContext(), "sai link", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadBookingData(@NonNull View view, List<OrderInformation> value) {
        recyclerViewOrder = view.findViewById(R.id.orderFinishRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(value, new IClickItemOrderListener() {
            @Override
            public void onClickItemOrder(OrderInformation orderInformation) {
                Intent it = new Intent(getContext(), OrderDetailActivity.class);
                it.putExtra("orderId", orderInformation.getOrderId());
                it.putExtra("orderStatus", orderInformation.getStatus());
                view.getContext().startActivity(it);
            }
        }, new IClickItemChatOrderListener() {
            @Override
            public void onClickItemChatOrder(OrderInformation orderInformation) {

            }
        });
        recyclerViewOrder.setAdapter(orderAdapter);
    }

//    private List<Order> getOrderData() {
//        List<Order> myList = new ArrayList<>();
//        String str = "2015-03-31";
//        Date dateChange = Date.valueOf(str);
//        myList.add(new Order(1, "Studio Name", dateChange, "WORKED", 100000, 4, "", "Ten ne", detailList));
//        myList.add(new Order(2, "Studio Name", dateChange, "WORKED", 100000, 4, "", "Ten ne", detailList));
//        myList.add(new Order(3, "Studio Name", dateChange, "WORKED", 100000, 4, "", "Ten ne", detailList));
//        myList.add(new Order(4, "Studio Name", dateChange, "WORKED", 100000, 4, "", "Ten ne", detailList));
//        myList.add(new Order(5, "Studio Name", dateChange, "WORKED", 100000, 4, "", "Ten ne", detailList));
//        return myList;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished, container, false);
        return view;
    }
}