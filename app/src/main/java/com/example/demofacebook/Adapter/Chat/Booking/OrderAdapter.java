package com.example.demofacebook.Adapter.Chat.Booking;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.Interface.IClickItemChatOrderListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.OrderInformation;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private final List<OrderInformation> mList;
    private final Context context;
    private final IClickItemOrderListener iClickItemOrderListenerListener;
    private final IClickItemChatOrderListener iClickItemChatOrderListener;


    public OrderAdapter(List<OrderInformation> mListOrder, Context context, IClickItemOrderListener iClickItemOrderListenerListener, IClickItemChatOrderListener iClickItemChatOrderListener) {
        this.mList = mListOrder;
        this.context = context;
        this.iClickItemOrderListenerListener = iClickItemOrderListenerListener;
        this.iClickItemChatOrderListener = iClickItemChatOrderListener;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_scheduled_item, parent, false);
        return new OrderViewHolder(view);
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
                    if (mOrderDetail != null) {
                        int totalPrice = 0;
                        for (int i = 0; i < mOrderDetail.size(); i++) {
                            totalPrice = totalPrice + mOrderDetail.get(i).getServicePack().getPriceService();
                        }
//                        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
//                        holder.totalPrice.setText("Total Price: " + numberFormat.format(totalPrice) + " VND");
                        Picasso.get()
                                .load(mOrderDetail.get(0).getServicePack().getMediaServicePackList().get(0).getFilePath())
                                .placeholder(R.drawable.placeholder_image)
                                .error(R.drawable.placeholder_image)
                                .into(holder.urlImageService);
//                        holder.orderServicePrice.setText(numberFormat.format(mOrderDetail.get(0).getServicePack().getPriceService()) + " VND");
//                        holder.totalOrderDetail.setText("Service: " + mOrderDetail.size());
                        holder.serviceName.setText(mOrderDetail.get(0).getServicePack().getServiceName());
                    }else{
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
            }
        });

        String dateTimeString = orderInformation.getOrderDate();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            java.util.Date utilDate = sdf.parse(dateTimeString);
            SimpleDateFormat outputSdf = new SimpleDateFormat("EEE, MMM d, yyyy");
            String formattedDate = outputSdf.format(utilDate);
            holder.orderDate.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (orderInformation.getStatus()) {
            case "pending":
                holder.btn_CanceledOrder.setClickable(true);
                holder.btn_CanceledOrder.setVisibility(View.VISIBLE);
                break;
            case "completed":
            case "cancel":
                holder.btn_CanceledOrder.setClickable(false);
                holder.btn_CanceledOrder.setVisibility(View.INVISIBLE);
                break;
        }

        holder.btn_ViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderListenerListener.onClickItemOrder(orderInformation);
            }
        });

        holder.btn_CanceledOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog(orderInformation.getOrderId(), position);
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


    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView orderDate;
        public ImageView urlImageService;
        public TextView serviceName;
        public Button btn_ViewOrder;
        public Button btn_CanceledOrder;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.orderServiceName);
            orderDate = itemView.findViewById(R.id.orderDate);
            urlImageService = itemView.findViewById(R.id.urlImageService);
            btn_ViewOrder = itemView.findViewById(R.id.btn_ViewOrder);
            btn_CanceledOrder = itemView.findViewById(R.id.btn_CanceledOrder);
        }
    }

    void confirmDialog(int orderId, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cancel order " + orderId + " ?");
        builder.setMessage("Are you sure you want to cancel " + orderId + " ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelOrderAction(orderId, index);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private void cancelOrderAction(int orderId, int i) {
        ApiService.apiService.updateCancelStatus(orderId, "cancel").enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Cancel Successful", Toast.LENGTH_SHORT).show();
                    mList.remove(i);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Lost Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
