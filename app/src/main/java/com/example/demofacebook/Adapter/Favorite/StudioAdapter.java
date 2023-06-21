package com.example.demofacebook.Adapter.Favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.HomePage.StudioPageActivity;
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
        holder.mList = studio.getServiceList();
        Log.d("a", String.valueOf(studio.getServiceList().size()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        holder.serviceAdapter = new ServiceAdapter(studio.getServiceList(), new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Service Service) {
                Intent intent = new Intent(mContext, ServicePage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service", Service);
                Studio studio = new Studio(1, R.drawable.download, "Studio 1 test", 500,5 ,"Title Description", "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n");
                bundle.putSerializable("studio", studio);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        holder.recyclerView.setAdapter(holder.serviceAdapter);

        holder.studioName.setText(studio.getTitle());
        holder.studioAvatar.setImageResource(studio.getImage());
        holder.rating.setText("⭐: " + studio.getRating());

        holder.studioBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StudioPageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("studio", studio);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
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


    public class StudioViewHolder extends RecyclerView.ViewHolder {
        List<Service> mList;
        RecyclerView recyclerView;
        ServiceAdapter serviceAdapter;
        TextView studioName;
        ImageView studioAvatar;
        TextView rating;
        LinearLayout studioBanner;
        Context context;

        public StudioViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            recyclerView = (RecyclerView) itemView.findViewById(R.id.serviceFavoriteRecyclerView);
            studioName = itemView.findViewById(R.id.txtNameOrderStudio);
            studioAvatar = itemView.findViewById(R.id.StudioAvatarOrderImage);
            rating = itemView.findViewById(R.id.txtOrderStudioRating);
            studioBanner = itemView.findViewById(R.id.StudioBanner);

        }
    }
}
