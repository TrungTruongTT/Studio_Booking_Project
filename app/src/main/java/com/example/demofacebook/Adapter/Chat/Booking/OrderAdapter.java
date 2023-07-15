package com.example.demofacebook.Adapter.Chat.Booking;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.Interface.IClickItemChatOrderListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Model.Order;
import com.example.demofacebook.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private final List<Order> mList;
    private final IClickItemOrderListener iClickItemOrderListenerListener;
    private final IClickItemChatOrderListener iClickItemChatOrderListener;


    public OrderAdapter(List<Order> mListOrder, IClickItemOrderListener iClickItemOrderListenerListener, IClickItemChatOrderListener iClickItemChatOrderListener) {
        this.mList = mListOrder;
        this.iClickItemOrderListenerListener = iClickItemOrderListenerListener;
        this.iClickItemChatOrderListener = iClickItemChatOrderListener;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_scheduled_item, parent, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order = mList.get(position);
        if (order == null) {
            return;
        }
//        holder.imageGallery.setImageResource(gallery.getImageGallery());
        Log.d("a", order.toString());
        holder.studioName.setText(order.getStudioName());
        String status = order.getStatus();
        switch (status) {
            case "PENDING":
                holder.status.setText("PENDING");
                holder.status.setTextColor(Color.parseColor("#DAA520"));
                break;
            case "DEPOSITED":
                holder.status.setText("DEPOSITED");
                holder.status.setTextColor(Color.parseColor("#9370DB"));
                break;
            case "WORKED":
                holder.status.setText("WORKED");
                holder.status.setTextColor(Color.parseColor("#0000CD"));
                break;
            case "COMPLETED":
                holder.status.setText("COMPLETED");
                holder.status.setTextColor(Color.parseColor("#228B22"));
                break;
            case "CANCELED":
                holder.status.setText("CANCELED");
                holder.status.setTextColor(Color.parseColor("#C71585"));
                break;

        }

        holder.totalPrice.setText("Total Price: $" + String.valueOf(order.getTotalPrice()));
        holder.totalOrderDetail.setText("Service: " + String.valueOf(order.getTotalOrderDetail()));
        holder.serviceName.setText(order.getServiceName());
        holder.orderDate.setText(order.getOrderDate().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderListenerListener.onClickItemOrder(order);
            }
        });

        holder.chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemChatOrderListener.onClickItemChatOrder(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageOrder;

        private int orderId;
        public TextView orderDate;
        public TextView studioName;
        public TextView status;
        public TextView totalPrice;
        public TextView totalOrderDetail;
        public ImageView urlImageService;
        public Button chatButton;
        public TextView serviceName;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            studioName = itemView.findViewById(R.id.OrderStudio);
            status = itemView.findViewById(R.id.orderStatus);
            totalPrice = itemView.findViewById(R.id.orderTotalPrice);
            totalOrderDetail = itemView.findViewById(R.id.orderTotalOrderDetail);
            serviceName = itemView.findViewById(R.id.orderServiceName);
            orderDate = itemView.findViewById(R.id.orderDate);
            chatButton = itemView.findViewById(R.id.OrderChatBtn);

        }
    }
}
