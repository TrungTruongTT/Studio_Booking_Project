package com.example.demofacebook.Adapter.StudioDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackListener;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyArrayAdapterHolder> {
    private final List<Feedback> mListFeedback;
    private final IClickItemFeedbackListener iClickItemFeedbackListener;


    public FeedbackAdapter(List<Feedback> mListFeedback, IClickItemFeedbackListener iClickItemFeedbackListener) {
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
        holder.feedbackDate.setText("Create at " + feedback.getFeedbackDate().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemFeedbackListener.onClickItemFeedback(feedback);
            }
        });
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
