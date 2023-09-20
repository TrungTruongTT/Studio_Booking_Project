package com.example.demofacebook.Adapter.NewFeed;

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
import androidx.viewpager.widget.PagerAdapter;

import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlideImageAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mListPhoto;

    public SlideImageAdapter(Context mContext, List<String> mListPhoto) {
        this.mContext = mContext;
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_image_slide_item, container, false);

        ImageView imageView = view.findViewById(R.id.NewFeedImageSlide);
        String photo = mListPhoto.get(position);
        if (photo != null) {
            Picasso.get()
                    .load(photo)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewImageGalleryItemDialog(Gravity.CENTER, photo);
            }
        });

        container.addView(view);
        return view;
    }

    private void openViewImageGalleryItemDialog(int gravity, String photo) {
        final Dialog dialog = new Dialog(mContext);
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

        Picasso.get()
                .load(photo)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(feedbackImage);

        closeBtn.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public int getCount() {
        if (mListPhoto != null) {
            return mListPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
