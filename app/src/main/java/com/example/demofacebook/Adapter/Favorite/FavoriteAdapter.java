package com.example.demofacebook.Adapter.Favorite;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.StudioViewHolder> {
    private final List<Studio> mList;
    private final Context mContext;


    public FavoriteAdapter(List<Studio> studioList, Context context) {
        this.mContext = context;
        this.mList = studioList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.StudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_studio_item, parent, false);
        return new FavoriteAdapter.StudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.StudioViewHolder holder, int position) {
        Studio studio = mList.get(position);
        if (studio == null) {
            return;
        }
        int studioIndex = position;
        //Service List
        holder.mList = studio.getServiceList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.serviceFavoriteAdapter = new ServiceFavoriteAdapter(studio.getServiceList(), new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Service Service) {
                Intent intent = new Intent(mContext, ServicePage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service", Service);
                Studio studio = new Studio(1, "https://i.imgur.com/DvpvklR.png", "Studio 1 test", 500, 5,
                        "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n", null);
                bundle.putSerializable("studio", studio);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }

        }, new IClickItemServiceDeleteListener() {
            @Override
            public void onItemServiceDelete(Service service, int indexDelete) {
                openConfirmAndDeleteDialog(Gravity.CENTER, holder.serviceFavoriteAdapter, service, indexDelete, studioIndex);
            }
        });
        holder.recyclerView.setAdapter(holder.serviceFavoriteAdapter);


        //Studio Banner
        holder.studioName.setText(studio.getTitle());
//        holder.studioAvatar.setImageResource(studio.getImage());
        Picasso.get().load(studio.getImage()).into(holder.studioAvatar);
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

        //Booking Service Item (Booking btn)
        holder.bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(studioIndex);
          }
        });

    }

    private void openConfirmAndDeleteDialog(int gravity, ServiceFavoriteAdapter serviceFavoriteAdapter, Service service, int indexDelete, int studioIndex) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_confirm);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        Button noBtn = dialog.findViewById(R.id.noButton);
        noBtn.setOnClickListener(view -> {

            dialog.dismiss();
        });

        Button yesBtn = dialog.findViewById(R.id.yesButton);
        yesBtn.setOnClickListener(view -> {
            serviceFavoriteAdapter.removeItem(indexDelete);
            serviceFavoriteAdapter.notifyDataSetChanged();
            if (serviceFavoriteAdapter.getItemCount() == 0) {
                removeItem(studioIndex);
            }

            dialog.dismiss();
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public void removeItem(int index) {
        mList.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
    }

    public class StudioViewHolder extends RecyclerView.ViewHolder {
        List<Service> mList;
        RecyclerView recyclerView;
        ServiceFavoriteAdapter serviceFavoriteAdapter;
        TextView studioName;
        ImageView studioAvatar;
        TextView rating;
        LinearLayout studioBanner;
        Button bookingBtn;
        Context context;

        public StudioViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            recyclerView = itemView.findViewById(R.id.serviceFavoriteRecyclerView);
            studioName = itemView.findViewById(R.id.txtNameOrderStudio);
            studioAvatar = itemView.findViewById(R.id.StudioAvatarOrderImage);
            rating = itemView.findViewById(R.id.txtOrderStudioRating);
            studioBanner = itemView.findViewById(R.id.StudioBanner);
            bookingBtn = itemView.findViewById(R.id.BookingServiceBtn);
        }
    }
}
