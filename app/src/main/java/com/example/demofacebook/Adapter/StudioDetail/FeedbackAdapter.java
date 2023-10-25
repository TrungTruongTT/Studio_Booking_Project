package com.example.demofacebook.Adapter.StudioDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyArrayAdapterHolder> {
    private final List<Feedback> mListFeedback;

    public FeedbackAdapter(List<Feedback> mListFeedback) {
        this.mListFeedback = mListFeedback;
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


        Picasso.get().
                load(feedback.getAvatarUser())
                .error(R.drawable.placeholder_image)
                .into(holder.userAvatar);
        holder.feedbackUserName.setText(feedback.getFeedbackUserName());
        String rating = "⭐: " + feedback.getRating();
        holder.rating.setText(rating);

        holder.feedbackDescription.setText(feedback.getFeedbackDescription());

        if (feedback.getFeedbackDate() != null) {
            String dateTimeString = feedback.getFeedbackDate();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                java.util.Date utilDate = sdf.parse(dateTimeString);
                SimpleDateFormat outputSdf = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = outputSdf.format(utilDate);
                holder.feedbackDate.setText("Post: " + formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            String str = "2015-03-31";
            Date dateChange = Date.valueOf(str);
            holder.feedbackDate.setText("Post " + dateChange);
        }
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
        TextView feedbackDate;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);

            userAvatar = itemView.findViewById(R.id.UserAvatar);
            feedbackUserName = itemView.findViewById(R.id.FeedbackUserName);
            rating = itemView.findViewById(R.id.Rating);
            feedbackDescription = itemView.findViewById(R.id.FeedbackDescription);
            feedbackDate = itemView.findViewById(R.id.FeedbackDate);


        }
    }


}
