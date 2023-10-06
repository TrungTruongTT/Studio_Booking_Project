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
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecommendStudioAdapter extends RecyclerView.Adapter<RecommendStudioAdapter.MyArrayAdapterHolder> {
    private List<Service> mListService;
    private IClickItemServiceListener iClickItemServiceListener;


    public RecommendStudioAdapter(List<Service> mListService, IClickItemServiceListener iClickItemServiceListener) {
        this.mListService = mListService;
        this.iClickItemServiceListener = iClickItemServiceListener;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_studio_recommend_item, parent, false);
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
        if (!service.getMediaServicePackList().isEmpty()) {
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

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageService = itemView.findViewById(R.id.ServiceImage);
            serviceName = itemView.findViewById(R.id.NameService);
        }
    }
}
