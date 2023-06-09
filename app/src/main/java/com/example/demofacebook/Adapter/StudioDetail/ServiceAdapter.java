package com.example.demofacebook.Adapter.StudioDetail;

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

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyArrayAdapterHolder> {
    private final List<Service> mListService;
    private final IClickItemServiceListener iClickItemServiceListener;


    public ServiceAdapter(List<Service> mListService, IClickItemServiceListener iClickItemServiceListener) {

        this.mListService = mListService;
        this.iClickItemServiceListener = iClickItemServiceListener;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_service_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        Service service = mListService.get(position);
        if (service == null) {
            return;
        }
        holder.imageService.setImageResource(service.getImageService());
        holder.serviceName.setText(service.getServiceName());
        holder.serviceDescription.setText(service.getServiceDescription());
        String price = String.valueOf(service.getPriceService());
        holder.servicePrice.setText("Form: US$" + price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemServiceListener.onClickItemService(service);
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


    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView imageService;
        TextView serviceName;
        TextView serviceDescription;
        TextView servicePrice;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageService = itemView.findViewById(R.id.ServiceImage);
            serviceName = itemView.findViewById(R.id.NameService);
            serviceDescription = itemView.findViewById(R.id.ServiceDescription);
            servicePrice = itemView.findViewById(R.id.ServicePrice);

        }
    }
}
