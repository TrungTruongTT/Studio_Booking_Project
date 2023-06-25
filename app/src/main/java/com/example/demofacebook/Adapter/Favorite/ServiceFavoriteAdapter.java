package com.example.demofacebook.Adapter.Favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceFavoriteAdapter extends RecyclerView.Adapter<ServiceFavoriteAdapter.MyArrayAdapterHolder> {
    private final List<Service> mListService;
    private final IClickItemServiceListener iClickItemServiceListener;
    private final IClickItemServiceDeleteListener iClickItemServiceDeleteListener;


    public ServiceFavoriteAdapter(List<Service> mListService, IClickItemServiceListener iClickItemServiceListener, IClickItemServiceDeleteListener iClickItemServiceDeleteListener) {
        this.mListService = mListService;
        this.iClickItemServiceListener = iClickItemServiceListener;
        this.iClickItemServiceDeleteListener = iClickItemServiceDeleteListener;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_service_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        Service service = mListService.get(position);
        int indexDelelte = position;
        if (service == null) {
            return;
        }
        /*Picasso.get()
                .load(service.getImageService())
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(holder.imageService);*/

        holder.serviceName.setText(service.getServiceName());
        holder.ratingService.setText("⭐: " + service.getServiceRating());
        holder.views.setText("View: " + service.getView());
        String price = String.valueOf(service.getPriceService());
        holder.servicePrice.setText("Form: US$" + price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemServiceListener.onClickItemService(service);
            }
        });

        holder.buttonDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemServiceDeleteListener.onItemServiceDelete(service, indexDelelte);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (mListService != null) {
            return mListService.size();
        }
        return 0;
    }

    public void removeItem(int index) {
        mListService.remove(index);
        notifyItemRemoved(index);
    }

    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView imageService;
        TextView serviceName;
        TextView ratingService;
        TextView views;
        TextView servicePrice;
        TextView buttonDeleteService;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageService = itemView.findViewById(R.id.ServiceImage);
            serviceName = itemView.findViewById(R.id.NameService);
            ratingService = itemView.findViewById(R.id.ServiceRating);
            views = itemView.findViewById(R.id.ServiceView);
            servicePrice = itemView.findViewById(R.id.ServicePrice);
            buttonDeleteService = itemView.findViewById(R.id.DeleteItem);
        }

    }
}
