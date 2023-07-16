package com.example.demofacebook.Adapter.Chat.Booking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackOrderDetailListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderDetailListener;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderViewHolder> {
    private final List<OrderDetail> mList;
    private final IClickItemOrderDetailListener iClickItemOrderDetailListener;
    private final IClickItemFeedbackOrderDetailListener iClickItemFeedbackOrderDetailListener;
    private final String orderStatus;


    public OrderDetailAdapter(List<OrderDetail> mList, IClickItemOrderDetailListener iClickItemOrderDetailListener, IClickItemFeedbackOrderDetailListener iClickItemFeedbackOrderDetailListener, String orderStatus) {
        this.mList = mList;
        this.iClickItemOrderDetailListener = iClickItemOrderDetailListener;
        this.iClickItemFeedbackOrderDetailListener = iClickItemFeedbackOrderDetailListener;
        this.orderStatus = orderStatus;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_detail_item, parent, false);
        return new OrderDetailAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.OrderViewHolder holder, int position) {
        OrderDetail orderDetail = mList.get(position);
        if (orderDetail == null) {
            return;
        }
        holder.serviceName.setText(orderDetail.getServicePack().getServiceName());
        holder.servicePrice.setText(orderDetail.getPrice() + " VND");

        if (orderDetail.getServicePack() != null) {
            if (orderDetail.getServicePack() == null) {
                Picasso.get().load(orderDetail.getServicePack().getMediaServicePackList().get(0).getFilePath())
                        .placeholder(R.drawable.download)
                        .error(R.drawable.download).into(holder.urlImageService);
            } else {
                Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                        .placeholder(R.drawable.download)
                        .error(R.drawable.download).into(holder.urlImageService);
            }
        } else {
            Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                    .placeholder(R.drawable.download).error(R.drawable.download)
                    .into(holder.urlImageService);
        }

        if (orderStatus.equals("pending")
                || orderStatus.equals("deposited")
                || orderStatus.equals("worked")
                || orderStatus.equals("cancel")
                || orderDetail.getContent() != null
                || orderDetail.getPostDate() != null
        ) {
            holder.feedbackButton.setEnabled(false);
            holder.feedbackButton.setVisibility(View.INVISIBLE);
        } else {
            holder.feedbackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickItemFeedbackOrderDetailListener.onClickItemFeedbackOrderDetail(orderDetail, holder.feedbackButton);
                    notifyDataSetChanged();
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderDetailListener.onClickItemOrderDetail(orderDetail);
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
        public TextView orderDetailId;
        public ImageView urlImageService;

        public TextView serviceName;
        public TextView servicePrice;
        public Button feedbackButton;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDetailId = itemView.findViewById(R.id.orderTotalOrderDetail);
            urlImageService = itemView.findViewById(R.id.urlImageOrderDetailService);
            serviceName = itemView.findViewById(R.id.orderDetailServiceName);
            servicePrice = itemView.findViewById(R.id.orderDetailServicePrice);
            feedbackButton = itemView.findViewById(R.id.feedbackBtn);

        }
    }
}
