package com.example.demofacebook.Adapter.Notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.Notification;
import com.example.demofacebook.MyInterface.IClickItemNotificationListener;
import com.example.demofacebook.R;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyArrayAdapterHolder> {

    public NotificationAdapter(IClickItemNotificationListener iClickItemNotificationListener, List<Notification> mNotification) {
        this.iClickItemNotificationListener = iClickItemNotificationListener;
        this.mNotification = mNotification;
    }

    private final IClickItemNotificationListener iClickItemNotificationListener;
    private final List<Notification> mNotification;

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notification_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {


        Notification notification = mNotification.get(position);
        if (notification == null) {
            return;
        }
        holder.image.setImageResource(notification.getImage());
        holder.notificationTile.setText(notification.getNotificationTitle());
        holder.notification.setText(notification.getNotification());
        holder.date.setText(notification.getDate().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemNotificationListener.onClickItemNotification(notification);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mNotification != null) {
            return mNotification.size();
        }
        return 0;
    }


    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView notificationTile;
        TextView notification;
        TextView date;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgNotification);
            notificationTile = itemView.findViewById(R.id.NotificationTitle);
            notification = itemView.findViewById(R.id.Notification);
            date = itemView.findViewById(R.id.NotificationDate);

        }
    }
}
