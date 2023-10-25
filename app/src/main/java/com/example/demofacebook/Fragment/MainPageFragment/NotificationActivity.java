package com.example.demofacebook.Fragment.MainPageFragment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Notification.NotificationAdapter;
import com.example.demofacebook.Model.Notification;
import com.example.demofacebook.MyInterface.IClickItemNotificationListener;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNofication;
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initToolBar();

        recyclerViewNofication = findViewById(R.id.RecyclerNotification);
        LinearLayoutManager linearLayoutManagerOption = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewNofication.setLayoutManager(linearLayoutManagerOption);

        notificationAdapter = new NotificationAdapter(new IClickItemNotificationListener() {
            @Override
            public void onClickItemNotification(Notification notification) {

            }
        }, getNotificationData());
        recyclerViewNofication.setAdapter(notificationAdapter);
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.NotificationToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Favorite");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Notification> getNotificationData() {
        List<Notification> myList = new ArrayList<>();
        int[] id = {1, 2, 3, 4, 5, 6};
        int[] image = {R.drawable.placeholder_image, R.drawable.placeholder_image, R.drawable.placeholder_image, R.drawable.placeholder_image, R.drawable.placeholder_image, R.drawable.placeholder_image};
        String[] notificationTile = {"Notification 1", "Notification 2", "Notification 3", "Notification 4", "Notification 5", "Notification 6"};
        String[] notification = {"Sale description\nSale description", "Sale description\nSale description", "Sale description\nSale description\nSale description\nSale description", "Sale description\nSale description", "Sale description\nSale description", "Sale description\nSale description"};
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        Date[] date = {dateChange, dateChange, dateChange, dateChange, dateChange, dateChange};
        for (int i = 0; i < notificationTile.length; i++) {
            myList.add(new Notification(id[1], image[i], notificationTile[i], notification[i], date[i]));
            myList.add(new Notification(id[2], image[i], notificationTile[i], notification[i], date[i]));
        }
        return myList;
    }
}