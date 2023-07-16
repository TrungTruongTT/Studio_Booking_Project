package com.example.demofacebook.Adapter.Chat.Booking;

import android.graphics.Color;
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
import com.example.demofacebook.Model.OrderInformation;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private final List<OrderInformation> mList;
    private final IClickItemOrderListener iClickItemOrderListenerListener;
    private final IClickItemChatOrderListener iClickItemChatOrderListener;


    public OrderAdapter(List<OrderInformation> mListOrder, IClickItemOrderListener iClickItemOrderListenerListener, IClickItemChatOrderListener iClickItemChatOrderListener) {
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
        OrderInformation orderDetail = mList.get(position);

        if (orderDetail == null) {
            return;
        }

        Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(holder.urlImageService);

        holder.orderId.setText("Order: " + orderDetail.getOrderId());

        String status = orderDetail.getStatus();
        switch (status) {
            case "pending":
                holder.status.setText("PENDING");
                holder.status.setTextColor(Color.parseColor("#DAA520"));
                break;
            case "deposited":
                holder.status.setText("DEPOSITED");
                holder.status.setTextColor(Color.parseColor("#9370DB"));
                break;
            case "worked":
                holder.status.setText("WORKED");
                holder.status.setTextColor(Color.parseColor("#0000CD"));
                break;
            case "completed":
                holder.status.setText("COMPLETED");
                holder.status.setTextColor(Color.parseColor("#228B22"));
                break;
            case "canceled":
                holder.status.setText("CANCELED");
                holder.status.setTextColor(Color.parseColor("#C71585"));
                break;

        }

        int totalPrice = 300;
//        totalPrice = totalPrice + orderDetail.getPrice();
        holder.totalPrice.setText("Total Price: " + String.valueOf(totalPrice) + " VND");


        holder.totalOrderDetail.setText("Service: 2");
        holder.serviceName.setText("FrameMates App Service Package");

        holder.orderDate.setText(orderDetail.getOrderDate().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderListenerListener.onClickItemOrder(orderDetail);
            }
        });

        holder.chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemChatOrderListener.onClickItemChatOrder(orderDetail);
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

        private TextView orderId;
        public TextView orderDate;
        public TextView status;
        public TextView totalPrice;
        public TextView totalOrderDetail;
        public ImageView urlImageService;
        public Button chatButton;
        public TextView serviceName;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.OrderId);
            status = itemView.findViewById(R.id.orderStatus);
            totalPrice = itemView.findViewById(R.id.orderTotalPrice);
            totalOrderDetail = itemView.findViewById(R.id.orderTotalOrderDetail);
            serviceName = itemView.findViewById(R.id.orderServiceName);
            orderDate = itemView.findViewById(R.id.orderDate);
            chatButton = itemView.findViewById(R.id.OrderChatBtn);
            urlImageService = itemView.findViewById(R.id.urlImageService);

        }
    }
}
