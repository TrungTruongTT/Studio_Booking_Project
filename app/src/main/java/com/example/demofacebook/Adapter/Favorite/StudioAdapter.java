package com.example.demofacebook.Adapter.Favorite;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.List;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.StudioViewHolder> {
    private final List<Studio> mList;
    private final Context mContext;
//    private final IClickItemOrderListener iClickItemOrderListenerListener;


    public StudioAdapter(List<Studio> studioList, Context context
//            , IClickItemOrderListener iClickItemOrderListenerListener
    ) {
        this.mContext = context;
        this.mList = studioList;
//        this.iClickItemOrderListenerListener = iClickItemOrderListenerListener;
    }

    @NonNull
    @Override
    public StudioAdapter.StudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_studio_item, parent, false);
        return new StudioAdapter.StudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudioAdapter.StudioViewHolder holder, int position) {
        Studio studio = mList.get(position);
        Log.d("a", String.valueOf(studio.getServiceList().size()));
        if (studio == null) {
            return;
        }
//        holder.imageGallery.setImageResource(gallery.getImageGallery());
//        Log.d("a", order.toString());
//        holder.status.setText(String.valueOf(order.getStatus()));
//        holder.totalPrice.setText(String.valueOf(order.getTotalPrice()));
//        holder.totalOrderDetail.setText(String.valueOf(order.getTotalOrderDetail()));
//        holder.serviceName.setText(order.getServiceName());
        holder.mList = studio.getServiceList();
        Log.d("a", String.valueOf(studio.getServiceList().size()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.serviceAdapter = new ServiceAdapter(studio.getServiceList());
        holder.recyclerView.setAdapter(holder.serviceAdapter);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iClickItemOrderListenerListener.onClickItemOrder(order);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public class StudioViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageOrder;

        private int orderId;
        public TextView orderDate;
        public TextView status;
        public TextView totalPrice;
        public TextView totalOrderDetail;
        public ImageView urlImageService;
        public TextView serviceName;
        List<Service> mList;
        RecyclerView recyclerView;
        ServiceAdapter serviceAdapter;
        Context context;

        public StudioViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
//            status = itemView.findViewById(R.id.orderStatus);
//            totalPrice = itemView.findViewById(R.id.orderTotalPrice);
//            totalOrderDetail = itemView.findViewById(R.id.orderTotalOrderDetail);
//            serviceName = itemView.findViewById(R.id.orderServiceName);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.serviceFavoriteRecyclerView);

        }
    }
}
