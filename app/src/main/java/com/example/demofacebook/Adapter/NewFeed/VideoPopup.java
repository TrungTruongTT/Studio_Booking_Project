package com.example.demofacebook.Adapter.NewFeed;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.demofacebook.R;

public class VideoPopup {

    private Context context;
    private String videoUrl;
    private android.widget.PopupWindow popupWindow;
    private VideoView videoView;

    public VideoPopup(Context context, String videoUrl) {
        this.context = context;
        this.videoUrl = videoUrl;
    }

    public void showPopup(View anchorView) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }

        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_video, null);
        videoView = popupView.findViewById(R.id.videoViewPopup);
        videoView.setVideoURI(Uri.parse(videoUrl));

        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        ImageButton closeButton = popupView.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopup();
            }
        });

        popupWindow = new android.widget.PopupWindow(popupView, androidx.appcompat.widget.LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, true);

        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAtLocation(anchorView, android.view.Gravity.CENTER, 0, 0);
    }

    public void dismissPopup() {
        if (popupWindow != null && popupWindow.isShowing()) {
            videoView.stopPlayback();
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

}
