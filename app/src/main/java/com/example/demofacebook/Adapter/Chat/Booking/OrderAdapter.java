package com.example.demofacebook.Adapter.Chat.Booking;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.Interface.IClickItemChatOrderListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.OrderInformation;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        OrderInformation orderInformation = mList.get(position);

        if (orderInformation == null) {
            return;
        }

        ApiService.apiService.getDetailByOrderId(orderInformation.getOrderId()).enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                if (response.isSuccessful()) {
                    List<OrderDetail> mOrderDetail;
                    mOrderDetail = response.body();

                    int totalPrice = 0;
                    for (int i = 0; i < mOrderDetail.size(); i++) {
                        totalPrice = totalPrice + mOrderDetail.get(i).getServicePack().getPriceService();
                    }
                    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                    holder.totalPrice.setText("Total Price: " + numberFormat.format(totalPrice) + " VND");
                    Picasso.get().load(mOrderDetail.get(0).getServicePack().getMediaServicePackList().get(0).getFilePath()).into(holder.urlImageService);
                    holder.orderServicePrice.setText(numberFormat.format(mOrderDetail.get(0).getServicePack().getPriceService()) + " VND");
                    holder.totalOrderDetail.setText("Service: " + mOrderDetail.size());
                    holder.serviceName.setText(mOrderDetail.get(0).getServicePack().getServiceName());
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
            }
        });


        holder.orderId.setText("Order: " + orderInformation.getOrderId());

        String status = orderInformation.getStatus();
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
            case "cancel":
                holder.status.setText("CANCELED");
                holder.status.setTextColor(Color.parseColor("#C71585"));
                break;

        }


        String dateTimeString = orderInformation.getOrderDate();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            java.util.Date utilDate = sdf.parse(dateTimeString);
            SimpleDateFormat outputSdf = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = outputSdf.format(utilDate);
            holder.orderDate.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderListenerListener.onClickItemOrder(orderInformation);
            }
        });

//        holder.chatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iClickItemChatOrderListener.onClickItemChatOrder(orderInformation);
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


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView orderId;
        public TextView orderDate;
        public TextView status;
        public TextView totalPrice;
        public TextView totalOrderDetail;
        public ImageView urlImageService;
        //        public Button chatButton;
        public TextView serviceName;
        public TextView orderServicePrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.OrderId);
            status = itemView.findViewById(R.id.orderStatus);
            totalPrice = itemView.findViewById(R.id.orderTotalPrice);
            totalOrderDetail = itemView.findViewById(R.id.orderTotalOrderDetail);
            serviceName = itemView.findViewById(R.id.orderServiceName);
            orderDate = itemView.findViewById(R.id.orderDate);
//            chatButton = itemView.findViewById(R.id.OrderChatBtn);
            urlImageService = itemView.findViewById(R.id.urlImageService);
            orderServicePrice = itemView.findViewById(R.id.orderServicePrice);
        }
    }
}
