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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackListener;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyArrayAdapterHolder> {
    private final Context context;
    private final List<Feedback> mListFeedback;
    private final IClickItemFeedbackListener iClickItemFeedbackListener;


    public FeedbackAdapter(Context context, List<Feedback> mListFeedback, IClickItemFeedbackListener iClickItemFeedbackListener) {
        this.context = context;
        this.mListFeedback = mListFeedback;
        this.iClickItemFeedbackListener = iClickItemFeedbackListener;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_feedback_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        Feedback feedback = mListFeedback.get(position);
        if (feedback == null) {
            return;
        }
        holder.userAvatar.setImageResource(feedback.getAvatarUser());
        holder.feedbackUserName.setText(feedback.getFeedbackUserName());
        String rating = "⭐: " + feedback.getRating();
        holder.rating.setText(rating);
        holder.feedbackDescription.setText(feedback.getFeedbackDescription());
        holder.feedbackImage.setImageResource(feedback.getFeedbackImage());
        holder.feedbackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateUserDialog(Gravity.TOP, feedback);
            }
        });
        holder.feedbackDate.setText("Create at " + feedback.getFeedbackDate().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemFeedbackListener.onClickItemFeedback(feedback);
            }
        });
    }

    private void openUpdateUserDialog(int gravity, Feedback feedback) {
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
        feedbackImage.setImageResource(feedback.getFeedbackImage());

        closeBtn.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }
    @Override
    public int getItemCount() {
        if (mListFeedback != null) {
            return mListFeedback.size();
        }
        return 0;
    }

    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView userAvatar;
        TextView feedbackUserName;
        TextView rating;
        TextView feedbackDescription;
        ImageView feedbackImage;
        TextView feedbackDate;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);

            userAvatar = itemView.findViewById(R.id.UserAvatar);
            feedbackUserName = itemView.findViewById(R.id.FeedbackUserName);
            rating = itemView.findViewById(R.id.Rating);
            feedbackDescription = itemView.findViewById(R.id.FeedbackDescription);
            feedbackImage = itemView.findViewById(R.id.FeedbackImage);
            feedbackDate = itemView.findViewById(R.id.FeedbackDate);


        }
    }


}
