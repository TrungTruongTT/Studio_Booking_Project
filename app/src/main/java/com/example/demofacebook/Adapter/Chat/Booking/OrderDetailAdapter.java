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
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.R;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderViewHolder> {
    private final List<Service> mList;
    private final IClickItemOrderDetailListener iClickItemOrderDetailListener;
    private final IClickItemFeedbackOrderDetailListener iClickItemFeedbackOrderDetailListener;
    private final String orderStatus;


    public OrderDetailAdapter(List<Service> mList, IClickItemOrderDetailListener iClickItemOrderDetailListener, IClickItemFeedbackOrderDetailListener iClickItemFeedbackOrderDetailListener, String orderStatus) {
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
        Service service = mList.get(position);
        if (service == null) {
            return;
        }
        holder.serviceName.setText(service.getServiceName());
        holder.servicePrice.setText("Price: US$" + service.getPriceService());
//        holder.urlImageService.setImageResource(orderDetail.getServiceName());

        if (orderStatus.equals("PENDING") || orderStatus.equals("DEPOSITED") || orderStatus.equals("WORKED") || orderStatus.equals("CANCELED")) {
            holder.feedbackButton.setEnabled(false);
            holder.feedbackButton.setVisibility(View.INVISIBLE);
        } else {
            holder.feedbackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickItemFeedbackOrderDetailListener.onClickItemFeedbackOrderDetail(service, holder.feedbackButton);
                    notifyDataSetChanged();
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderDetailListener.onClickItemOrderDetail(service);
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
