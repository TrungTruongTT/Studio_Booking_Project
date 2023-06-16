package com.example.demofacebook.Fragment.MainPageFragment;

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

import com.example.demofacebook.Adapter.Notification.NotificationAdapter;
import com.example.demofacebook.Model.Notification;
import com.example.demofacebook.MyInterface.IClickItemNotificationListener;
import com.example.demofacebook.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {
    private RecyclerView recyclerViewNofication;
    private NotificationAdapter notificationAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewNofication = getActivity().findViewById(R.id.RecyclerNotification);
        LinearLayoutManager linearLayoutManagerOption = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewNofication.setLayoutManager(linearLayoutManagerOption);

        notificationAdapter = new NotificationAdapter(new IClickItemNotificationListener() {
            @Override
            public void onClickItemNotification(Notification notification) {
                Toast.makeText(getActivity(), notification.getNotificationTitle(), Toast.LENGTH_SHORT).show();
            }
        }, getNotificationData());
        recyclerViewNofication.setAdapter(notificationAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        return view;
    }

    private List<Notification> getNotificationData() {
        List<Notification> myList = new ArrayList<>();
        int[] id = {1, 2, 3, 4, 5, 6};
        int[] image = {R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download};
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
