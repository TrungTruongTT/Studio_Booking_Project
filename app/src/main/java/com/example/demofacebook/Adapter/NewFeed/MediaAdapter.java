package com.example.demofacebook.Adapter.NewFeed;

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
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.demofacebook.HomePage.StudioPageActivity;
import com.example.demofacebook.Model.MediaItem;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MediaItem> mediaItems;
    private Context mContext;

    public MediaAdapter(List<MediaItem> mediaItems, Context mContext) {
        this.mediaItems = mediaItems;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MediaItem.TYPE_IMAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newfeed_image_item, parent, false);
            return new ImageViewHolder(view);
        } else if (viewType == MediaItem.TYPE_VIDEO) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newfeed_video_item, parent, false);
            return new VideoViewHolder(view);
        } else if (viewType == MediaItem.TYPE_IMAGE_SLIDE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newfeed_image_slide_item, parent, false);
            return new ImageSlideViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MediaItem mediaItem = mediaItems.get(position);
        if (mediaItem == null) {
            return;
        }
        if (holder instanceof ImageViewHolder) {
            imageViewData((ImageViewHolder) holder, mediaItem);
        } else if (holder instanceof VideoViewHolder) {
            // Bind video item
            videoViewData((VideoViewHolder) holder, mediaItem);
        } else if (holder instanceof ImageSlideViewHolder) {
            imageSlideViewData((ImageSlideViewHolder) holder, mediaItem);
        }
    }

    private void imageSlideViewData(ImageSlideViewHolder holder, MediaItem mediaItem) {
        holder.studioNewFeedSlideImageBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoStudioPage(mediaItem);
            }
        });
        Picasso.get()
                .load(mediaItem.getStudio().getImage())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.studioAvatarNewFeedImageSlide);

        holder.nameStudioNewFeedImageSlide.setText(mediaItem.getStudio().getTitle());
        holder.studioRatingNewFeedImageSlide.setText("⭐ " + mediaItem.getStudio().getRating());


        holder.likeBtnNewFeedImageSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.likeBtnNewFeedImageSlide.setBackgroundResource(R.drawable.love_heart_svg);
            }
        });
        ViewPager viewPager = holder.viewPager;
        CircleIndicator circleIndicator = holder.circleIndicator;

        SlideImageAdapter slideImageAdapter = new SlideImageAdapter(mContext, mediaItem.getImageResourceUrlList());
        viewPager.setAdapter(slideImageAdapter);

        circleIndicator.setViewPager(viewPager);
        slideImageAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

    }

    private void videoViewData(VideoViewHolder holder, MediaItem mediaItem) {
        holder.studioNewFeedVideoBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoStudioPage(mediaItem);
            }
        });
        Picasso.get()
                .load(mediaItem.getStudio().getImage())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.studioAvatarNewFeedVideo);

        holder.nameStudioNewFeedVideo.setText(mediaItem.getStudio().getTitle());
        holder.studioRatingNewFeedVideo.setText("⭐ " + mediaItem.getStudio().getRating());


        holder.likeBtnNewFeedVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.likeBtnNewFeedVideo.setBackgroundResource(R.drawable.love_heart_svg);
            }
        });

        VideoViewHolder videoViewHolder = holder;
        videoViewHolder.videoNewFeedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the video popup
                VideoPopup videoPopup = new VideoPopup(mContext, mediaItem.getVideoUrl());
                videoPopup.showPopup(videoViewHolder.videoNewFeedView);
            }
        });
    }

    private void imageViewData(ImageViewHolder holder, MediaItem mediaItem) {
        holder.studioNewFeedImageBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoStudioPage(mediaItem);
            }
        });
        Picasso.get()
                .load(mediaItem.getStudio().getImage())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.studioAvatarNewFeedImage);

        holder.nameStudioNewFeedImage.setText(mediaItem.getStudio().getTitle());
        holder.studioRatingNewFeedImage.setText("⭐ " + mediaItem.getStudio().getRating());

        Picasso.get()
                .load(mediaItem.getImageResourceUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.newFeedImage);


        holder.likeBtnNewFeedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.likeBtnNewFeedImage.setBackgroundResource(R.drawable.love_heart_svg);
            }
        });

        holder.newFeedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewImageGalleryItemDialog(Gravity.CENTER, mediaItem);
            }
        });
    }

    private void onClickGoStudioPage(MediaItem mediaItem) {
        Intent intent = new Intent(mContext, StudioPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", mediaItem.getStudio());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        if (mediaItems == null) {
            return 0;
        }
        return mediaItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mediaItems.get(position).getType();
    }

    private void openViewImageGalleryItemDialog(int gravity, MediaItem mediaItem) {
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
                .load(mediaItem.getImageResourceUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(feedbackImage);

        closeBtn.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

    // ViewHolder for image items
    private static class ImageViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout studioNewFeedImageBanner;
        private ImageView studioAvatarNewFeedImage;
        private TextView nameStudioNewFeedImage;
        private TextView studioRatingNewFeedImage;
        private ImageView newFeedImage;
        private Button likeBtnNewFeedImage;

        private ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            studioNewFeedImageBanner = itemView.findViewById(R.id.StudioNewFeedImageBanner);
            studioAvatarNewFeedImage = itemView.findViewById(R.id.StudioAvatarNewFeedImage);
            nameStudioNewFeedImage = itemView.findViewById(R.id.NameStudioNewFeedImage);
            studioRatingNewFeedImage = itemView.findViewById(R.id.StudioRatingNewFeedImage);
            newFeedImage = itemView.findViewById(R.id.NewFeedImage);
            likeBtnNewFeedImage = itemView.findViewById(R.id.LikeBtnNewFeedImage);
        }
    }

    // ViewHolder for video items
    private static class VideoViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout studioNewFeedVideoBanner;
        private ImageView studioAvatarNewFeedVideo;
        private TextView nameStudioNewFeedVideo;
        private TextView studioRatingNewFeedVideo;
        private VideoView videoNewFeedView;
        private Button likeBtnNewFeedVideo;


        private VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            studioNewFeedVideoBanner = itemView.findViewById(R.id.StudioNewFeedVideoBanner);
            studioAvatarNewFeedVideo = itemView.findViewById(R.id.StudioAvatarNewFeedVideo);
            nameStudioNewFeedVideo = itemView.findViewById(R.id.NameStudioNewFeedVideo);
            studioRatingNewFeedVideo = itemView.findViewById(R.id.StudioRatingNewFeedVideo);
            videoNewFeedView = itemView.findViewById(R.id.NewFeedVideo);
            likeBtnNewFeedVideo = itemView.findViewById(R.id.LikeBtnNewFeedVideo);
        }
    }

    // ViewHolder for slide image items
    private static class ImageSlideViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout studioNewFeedSlideImageBanner;
        private ImageView studioAvatarNewFeedImageSlide;
        private TextView nameStudioNewFeedImageSlide;
        private TextView studioRatingNewFeedImageSlide;
        private ViewPager viewPager;
        private CircleIndicator circleIndicator;
        private Button likeBtnNewFeedImageSlide;


        private ImageSlideViewHolder(@NonNull View itemView) {
            super(itemView);
            studioNewFeedSlideImageBanner = itemView.findViewById(R.id.studioNewFeedSlideImageBanner);
            studioAvatarNewFeedImageSlide = itemView.findViewById(R.id.StudioAvatarNewFeedImageSlide);
            nameStudioNewFeedImageSlide = itemView.findViewById(R.id.NameStudioNewFeedImageSlide);
            studioRatingNewFeedImageSlide = itemView.findViewById(R.id.StudioRatingNewFeedImageSlide);

            viewPager = itemView.findViewById(R.id.ViewPagerNewFeedImageSlide);
            circleIndicator = itemView.findViewById(R.id.Circle_IndicatorNewFeedImageSlide);

            likeBtnNewFeedImageSlide = itemView.findViewById(R.id.LikeBtnNewFeedImageSlide);

        }
    }

}


//            Picasso.get()
//                    .load(mediaItem.getImageResourceUrl())
//                    .placeholder(R.drawable.download)
//                    .error(R.drawable.download)
//                    .into(imageView);