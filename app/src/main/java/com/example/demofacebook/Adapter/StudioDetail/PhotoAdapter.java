package com.example.demofacebook.Adapter.StudioDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demofacebook.Model.StudioToolBarPhoto;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    private Context mContext;
    private List<StudioToolBarPhoto> mListPhoto;

    public PhotoAdapter(Context mContext, List<StudioToolBarPhoto> mListPhoto) {
        this.mContext = mContext;
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_studio_photo_item, container, false);

        ImageView imageView = view.findViewById(R.id.ImagePhoto);
        StudioToolBarPhoto photo = mListPhoto.get(position);
        if (photo != null) {
            Picasso.get()
                    .load("https://i.imgur.com/DvpvklR.png")
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download)
                    .into(imageView);
        }

        container.addView(view);
        return view;
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
