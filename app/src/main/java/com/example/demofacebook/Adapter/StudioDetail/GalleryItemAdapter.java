package com.example.demofacebook.Adapter.StudioDetail;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.GalleryItem;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryItemAdapter extends RecyclerView.Adapter<GalleryItemAdapter.MyArrayAdapterHolder> {
    private final Context context;
    private final List<GalleryItem> mListItemGallery;

    public GalleryItemAdapter(Context context, List<GalleryItem> mListItemGallery) {
        this.context = context;
        this.mListItemGallery = mListItemGallery;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_gallery_image_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        GalleryItem galleryItem = mListItemGallery.get(position);
        if (galleryItem == null) {
            return;
        }
//        holder.imageItemGallery.setImageResource(galleryItem.getImageItemGallery());
        if (galleryItem.getImageItemGallery() != null) {
            Picasso.get().load(galleryItem.getImageItemGallery())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.imageItemGallery);
        } else {
            Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.imageItemGallery);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewImageGalleryItemDialog(Gravity.CENTER, galleryItem);
            }
        });
    }

    private void openViewImageGalleryItemDialog(int gravity, GalleryItem galleryItem) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_feedback_image);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        ImageView feedbackImage = dialog.findViewById(R.id.FeedbackImage_View);
        Button closeBtn = dialog.findViewById(R.id.CloseBtn);

//        feedbackImage.setImageResource(galleryItem.getImageItemGallery());
        if (galleryItem.getImageItemGallery() != null) {
            Picasso.get().load(galleryItem.getImageItemGallery())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(feedbackImage);
        } else {
            Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(feedbackImage);
        }

        closeBtn.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public int getItemCount() {
        if (mListItemGallery != null) {
            return mListItemGallery.size();
        }
        return 0;
    }


    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView imageItemGallery;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageItemGallery = itemView.findViewById(R.id.GalleryItemImage);
        }
    }


}
