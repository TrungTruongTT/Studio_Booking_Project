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
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        if (feedback.getAvatarUser() != null) {
            Picasso.get().
                    load(feedback.getAvatarUser())
                    .error(R.drawable.placeholder_image)
                    .into(holder.userAvatar);
        } else {
            Picasso.get()
                    .load("https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg")
                    .placeholder(R.drawable.placeholder_image)
                    .into(holder.userAvatar);
        }
        if (feedback.getFeedbackUserName() == null) {
            holder.feedbackUserName.setText("Huynh Phi");
        } else {
            holder.feedbackUserName.setText(feedback.getFeedbackUserName());
        }

        if (Integer.valueOf(feedback.getRating()) == null) {
            String rating = "⭐: " + 4;
            holder.rating.setText(rating);
        } else {
            String rating = "⭐: " + feedback.getRating();
            holder.rating.setText(rating);
        }

        if (feedback.getFeedbackDescription() == null) {
            holder.feedbackDescription.setText("Null Data");
        } else {
            holder.feedbackDescription.setText(feedback.getFeedbackDescription());
        }

        if (feedback.getFeedbackDate() != null) {

            String dateTimeString = feedback.getFeedbackDate();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                java.util.Date utilDate = sdf.parse(dateTimeString);
                SimpleDateFormat outputSdf = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = outputSdf.format(utilDate);
                holder.feedbackDate.setText("Create at: " + formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {
            String str = "2015-03-31";
            Date dateChange = Date.valueOf(str);
            holder.feedbackDate.setText("Create at " + dateChange);
        }

//        if (feedback.getFeedbackImage() != null) {
//            if (feedback.getFeedbackImage().size() != 0) {
//                Picasso.get().load(feedback.getFeedbackImage().get(0).getFilePath())
//                        .placeholder(R.drawable.download)
//                        .error(R.drawable.download)
//                        .into(holder.feedbackImage);
//            } else {
//                Picasso.get().load("https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg")
//                        .placeholder(R.drawable.download)
//                        .error(R.drawable.download)
//                        .into(holder.feedbackImage);
//            }
//        } else {
//            Picasso.get().load("https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg")
//                    .placeholder(R.drawable.download)
//                    .error(R.drawable.download)
//                    .into(holder.feedbackImage);
//        }

//        holder.feedbackImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openViewFeedbackImageDialog(Gravity.TOP, feedback);
//            }
//        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iClickItemFeedbackListener.onClickItemFeedback(feedback);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (mListFeedback != null) {
            return mListFeedback.size();
        }
        return 0;
    }

    private void openViewFeedbackImageDialog(int gravity, Feedback feedback) {
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

        if (feedback.getFeedbackImage() != null) {
            if (feedback.getFeedbackImage().size() != 0) {
                Picasso.get().load(feedback.getFeedbackImage().get(0).getFilePath())
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .into(feedbackImage);
            } else {
                Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .into(feedbackImage);
            }
        } else {
            Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(feedbackImage);
        }

        Button closeBtn = dialog.findViewById(R.id.CloseBtn);
        closeBtn.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
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
//            feedbackImage = itemView.findViewById(R.id.FeedbackImage);
            feedbackDate = itemView.findViewById(R.id.FeedbackDate);


        }
    }


}
