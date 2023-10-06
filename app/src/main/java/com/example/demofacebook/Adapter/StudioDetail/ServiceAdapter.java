package com.example.demofacebook.Adapter.StudioDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyArrayAdapterHolder> implements Filterable {
    private List<Service> mListService;
    private List<Service> mListServiceOld;
    private IClickItemServiceListener iClickItemServiceListener;


    public ServiceAdapter(List<Service> mListService){
        this.mListService = mListService;
        this.mListServiceOld = mListService;
    }
    public ServiceAdapter(List<Service> mListService, IClickItemServiceListener iClickItemServiceListener) {
        this.mListService = mListService;
        this.mListServiceOld = mListService;
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

        /*Picasso.get()
                .load(mediaItem.getStudio().getImage())
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(holder.studioAvatarNewFeedImage);*/
        /*Picasso.get()
                .load(service.getMediaServicePackList())
                .into(holder.imageService);*/
        //holder.imageService.setImageResource(service.getMediaServicePack().getFilePath());


        //Picasso.get().load(service.getMediaServicePackList().get(1).getFilePath()).into(holder.imageService);
        if(!service.getMediaServicePackList().isEmpty()){
            Picasso.get().load(service.getMediaServicePackList().get(0).getFilePath())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.imageService);
        }
        else{
            Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.imageService);
        }


        if (service.getMediaServicePackList() != null) {
            if (service.getMediaServicePackList().size() != 0) {
                Picasso.get().load(service.getMediaServicePackList().get(0).getFilePath())
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .into(holder.imageService);
            } else {
                Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .into(holder.imageService);
            }
        }

        holder.serviceName.setText(service.getServiceName());
        holder.ratingService.setText("⭐: " + service.getServiceRating());
//        holder.views.setText("View: " + service.getView());

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        holder.servicePrice.setText( numberFormat.format(service.getPriceService()) + " VND");

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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                List<Service> list = new ArrayList<>();
                if (strSearch.isEmpty()) {
                    mListService = mListServiceOld;
                }
                if (!strSearch.isEmpty()) {
                    //sortBy Category - sortByItem
                    if ("@!All".equals(strSearch)) {
                        for (Service service : mListServiceOld) {
                            if (true) {
                                list.add(service);
                            }
                        }
                    }
                    if ("@!Top Rating".equals(strSearch)) {
                        list = mListServiceOld;
                        list.sort((service, t1) -> {
                            return (int) (t1.getServiceRating() - service.getServiceRating());
                        });
                    }
                    if ("@!Low Price".equals(strSearch)) {
                        list = mListServiceOld;
                        list.sort((service, t1) -> {
                            return (int) (service.getPriceService() - t1.getPriceService());
                        });
                    }
                    if ("@!High Price".equals(strSearch)) {
                        list = mListServiceOld;
                        list.sort((service, t1) -> {
                            return (int) (t1.getPriceService() - service.getPriceService());
                        });
                    }
                    if ("@!Top View".equals(strSearch)) {
                        list = mListServiceOld;
                        list.sort((service, t1) -> {
                            return (int) (t1.getView() - service.getView());
                        });
                    }
                    if ("@!Low View".equals(strSearch)) {
                        list = mListServiceOld;
                        list.sort((service, t1) -> {
                            return (int) (service.getView() - t1.getView());
                        });
                    }
                    //Search bar
                    else {
                        for (Service service : mListServiceOld) {
                            if (service.getServiceName().toLowerCase().trim().contains(strSearch.toLowerCase().trim())) {
                                list.add(service);
                            }
                        }
                    }
                }
                mListService = list;
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListService;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mListService = (List<Service>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView imageService;
        TextView serviceName;
        TextView ratingService;
//        TextView views;
        TextView servicePrice;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageService = itemView.findViewById(R.id.ServiceImage);
            serviceName = itemView.findViewById(R.id.NameService);
            ratingService = itemView.findViewById(R.id.ServiceRating);
//            views = itemView.findViewById(R.id.ServiceView);
            servicePrice = itemView.findViewById(R.id.ServicePrice);
        }
    }
}
