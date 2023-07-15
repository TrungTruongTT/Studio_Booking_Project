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
import com.example.demofacebook.Model.Order;
import com.example.demofacebook.OrderDetailActivity;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class DepositedFragment extends Fragment {
    private RecyclerView recyclerViewOrder;
    private OrderAdapter orderAdapter;
    private List<Order> mList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewOrder = view.findViewById(R.id.orderDepositedRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);
        mList = getOrderData();
        orderAdapter = new OrderAdapter(mList, new IClickItemOrderListener() {
            @Override
            public void onClickItemOrder(Order order) {
                Toast.makeText(getActivity(), String.valueOf(order.getOrderId()), Toast.LENGTH_SHORT).show();
                Intent it = new Intent(view.getContext(), OrderDetailActivity.class);
                it.putExtra("orderId", order.getOrderId());
                it.putExtra("orderStatus", order.getStatus());
                view.getContext().startActivity(it);
            }
        }, new IClickItemChatOrderListener() {
            @Override
            public void onClickItemChatOrder(Order order) {
                Toast.makeText(view.getContext(), "Chat with order id: " + order.getOrderId(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewOrder.setAdapter(orderAdapter);


    }

    private List<Order> getOrderData() {
        List<Order> myList = new ArrayList<>();
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Order(1, "Studio Name", dateChange, "DEPOSITED", 100000, 4, "", "Ten service dau tien"));
        myList.add(new Order(2, "Studio Name", dateChange, "DEPOSITED", 100000, 4, "", "Ten service dau tien"));
        myList.add(new Order(3, "Studio Name", dateChange, "DEPOSITED", 100000, 4, "", "Ten service dau tien"));
        myList.add(new Order(4, "Studio Name", dateChange, "DEPOSITED", 100000, 4, "", "Ten service dau tien"));
        myList.add(new Order(5, "Studio Name", dateChange, "DEPOSITED", 100000, 4, "", "Ten service dau tien"));
        return myList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deposited, container, false);
        return view;
    }
}