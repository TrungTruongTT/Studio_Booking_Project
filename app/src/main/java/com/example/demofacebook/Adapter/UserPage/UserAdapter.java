package com.example.demofacebook.Adapter.UserPage;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.User;
import com.example.demofacebook.MyInterface.IClickItemUserListener;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyArrayAdapterHolder> {

    private final IClickItemUserListener iClickItemUserListener;
    private final User mUser;

    public UserAdapter(IClickItemUserListener iClickItemUserListener, User mUser) {
        this.iClickItemUserListener = iClickItemUserListener;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        User user = mUser;
        if (user == null) {
            return;
        }
//        holder.userImage.setImageResource(user.getImage());

        Picasso.get()
                .load(user.getImage())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.userImage);

        holder.userName.setText(user.getFullName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUserListener.onClickItemUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mUser != null) {
            return 1;
        }
        return 0;
    }

    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.User_Image);
            userName = itemView.findViewById(R.id.User_Name);

        }
    }

}
