package com.example.demofacebook.Adapter.UserPage;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.MyInterface.IClickItemUserListener;
import com.example.demofacebook.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyArrayAdapterHolder> {

    private IClickItemUserListener iClickItemUserListener;
    private final List<Integer> userImage;
    private final List<String> userName;


    public UserAdapter(IClickItemUserListener iClickItemUserListener, List<Integer> userImage, List<String> userName) {
        this.iClickItemUserListener = iClickItemUserListener;
        this.userImage = userImage;
        this.userName = userName;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        String Name = userName.get(position);
        if (Name == null) {
            return;
        }
        holder.userImage.setImageResource(userImage.get(position));
        holder.userName.setText(Name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUserListener.onClickItemUser(Name);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (userName != null) {
            return userImage.size();
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
