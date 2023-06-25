package com.example.demofacebook.Adapter.Booking;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.Interface.IClickItemChatOrderListener;
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
        holder.status.setText("Status: " + String.valueOf(order.getStatus()));
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
