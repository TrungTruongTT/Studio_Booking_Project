package com.example.demofacebook.Adapter.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.Message;
import com.example.demofacebook.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

private List<Message> sListMessage;
public void setDate(List<Message> list){
this.sListMessage = list;
notifyDataSetChanged();
}
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
    return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Message message = sListMessage.get(position);
        //9:46
    }

    @Override
    public int getItemCount() {
    if(sListMessage !=null){
        return sListMessage.size();
    }
        return 0;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }
    }
}
