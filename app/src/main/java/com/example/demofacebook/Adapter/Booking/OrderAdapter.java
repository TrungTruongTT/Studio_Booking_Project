package com.example.demofacebook.Adapter.Booking;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Model.Order;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.R;

import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private final List<Order> mList;
    private final IClickItemOrderListener iClickItemOrderListenerListener;


    public OrderAdapter(List<Order> mListOrder, IClickItemOrderListener iClickItemOrderListenerListener) {
        this.mList = mListOrder;
        this.iClickItemOrderListenerListener = iClickItemOrderListenerListener;
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
        holder.status.setText(String.valueOf(order.getStatus()));
        holder.totalPrice.setText(String.valueOf(order.getTotalPrice()));
        holder.totalOrderDetail.setText(String.valueOf(order.getTotalOrderDetail()));
        holder.serviceName.setText(order.getServiceName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderListenerListener.onClickItemOrder(order);
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
        public TextView status;
        public TextView totalPrice;
        public TextView totalOrderDetail;
        public ImageView urlImageService;
        public TextView serviceName;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.orderStatus);
            totalPrice = itemView.findViewById(R.id.orderTotalPrice);
            totalOrderDetail = itemView.findViewById(R.id.orderTotalOrderDetail);
            serviceName = itemView.findViewById(R.id.orderServiceName);

        }
    }
}
