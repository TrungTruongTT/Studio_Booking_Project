package com.example.demofacebook.Fragment.BookingPageFragment;

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

import com.example.demofacebook.Adapter.Booking.OrderAdapter;
import com.example.demofacebook.Adapter.StudioDetail.GalleryAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemGalleryListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Model.Gallery;
import com.example.demofacebook.Model.Order;
import com.example.demofacebook.OrderDetailActivity;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ScheduledFragment extends Fragment {
    private RecyclerView recyclerViewOrder;
    private OrderAdapter orderAdapter;
    private List<Order> mList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewOrder = view.findViewById(R.id.OrderRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);
        mList = getOrderData();
        orderAdapter = new OrderAdapter(mList, new IClickItemOrderListener() {
            @Override
            public void onClickItemOrder(Order order) {
                Toast.makeText(getActivity(),String.valueOf(order.getOrderId()) , Toast.LENGTH_SHORT).show();
                Intent it = new Intent(view.getContext(), OrderDetailActivity.class);
                it.putExtra("orderId", order.getOrderId());
                view.getContext().startActivity(it);
            }
        });
        recyclerViewOrder.setAdapter(orderAdapter);


    }

    private List<Order> getOrderData() {
        List<Order> myList = new ArrayList<>();
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Order(1, dateChange, 1, 100000, 4, "", "Ten ne"));
        myList.add(new Order(2, dateChange, 1, 100000, 4, "", "Ten ne"));
        myList.add(new Order(3, dateChange, 1, 100000, 4, "", "Ten ne"));
        myList.add(new Order(4, dateChange, 1, 100000, 4, "", "Ten ne"));
        myList.add(new Order(5, dateChange, 1, 100000, 4, "", "Ten ne"));
        return myList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scheduled, container, false);
        return view;
    }

}