package com.example.demofacebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Adapter.Chat.Booking.ExpandableListAdapter;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.BookingGroupItem;
import com.example.demofacebook.Model.SlotBookingItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingStudioActivity extends AppCompatActivity {
    private Button btn_Continue;
    private Button btn_Cancel;
    Toolbar toolbar;
    ExpandableListView expandableListView;
    List<BookingGroupItem> mGroupList;
    Map<BookingGroupItem, List<SlotBookingItem>> mListItems;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_studio);
        initToolBar();

        btn_Continue = findViewById(R.id.btn_ContinuePayment);
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), PaymentBookingActivity.class);
                startActivity(intent);
            }
        });

        btn_Cancel = findViewById(R.id.btn_CancelBooking);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HomeActivity.class);
                startActivity(intent);
            }
        });

        expandableListView = findViewById(R.id.ExpandableListBooking);
        mListItems = getListItems();
        mGroupList = new ArrayList<>(mListItems.keySet());

        expandableListAdapter = new ExpandableListAdapter(mGroupList, mListItems, getApplicationContext());
        expandableListView.setAdapter(expandableListAdapter);


    }

    private Map<BookingGroupItem, List<SlotBookingItem>> getListItems() {
        Map<BookingGroupItem, List<SlotBookingItem>> listMap = new HashMap<>();

        BookingGroupItem groupItem = new BookingGroupItem(1, "https://static.wikia.nocookie.net/avatar/images/9/91/Avatar_Studios.png/revision/latest?cb=20210225004145" +
                "s.io%2Fwp-content%2Fuploads%2Fsites%2F6%2F2021%2F02%2F23%2FAvatar_Studios_logo_large_02_22_21.jpg", "Avatar", "Avatar Studio", "10-10-2023");

        List<SlotBookingItem> items = new ArrayList<>();
        items.add(new SlotBookingItem(1, " 8:00 - 10:00"));
        items.add(new SlotBookingItem(2, "10:00 - 12:00"));
        items.add(new SlotBookingItem(3, "12:00 - 14:00"));

        listMap.put(groupItem, items);

        return listMap;
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.ToolBarBookingStudioActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
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
}