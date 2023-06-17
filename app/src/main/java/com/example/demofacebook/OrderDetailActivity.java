package com.example.demofacebook;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        initToolBar();
        recyclerView = (RecyclerView) findViewById(R.id.orderDetailRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mList = getOrderData();
        orderAdapter = new OrderAdapter(mList, new IClickItemOrderListener() {
            @Override
            public void onClickItemOrder(Order order) {
                Toast.makeText(OrderDetailActivity.this, String.valueOf(order.getOrderId()), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(orderAdapter);
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.OrderDetailToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_navbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Order Detail");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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