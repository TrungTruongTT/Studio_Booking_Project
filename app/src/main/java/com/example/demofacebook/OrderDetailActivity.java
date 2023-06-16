package com.example.demofacebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.demofacebook.Adapter.Booking.OrderAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Model.Order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        recyclerView = (RecyclerView) findViewById(R.id.orderDetailRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mList = getOrderData();
        orderAdapter = new OrderAdapter(mList, new IClickItemOrderListener() {
            @Override
            public void onClickItemOrder(Order order) {
                Toast.makeText(OrderDetailActivity.this, String.valueOf(order.getOrderId()) , Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(orderAdapter);
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
}