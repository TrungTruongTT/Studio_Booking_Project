package com.example.demofacebook.Adapter.UserPage;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.MyInterface.IClickItemUserOptionListener;
import com.example.demofacebook.R;

import java.util.List;

public class ItemUserAdapter extends RecyclerView.Adapter<ItemUserAdapter.MyArrayAdapterHolder> {
    private final IClickItemUserOptionListener iClickItemUserOptionListener;
    private final List<String> mOptionList;
    private final List<Drawable> mIconList;


    public ItemUserAdapter(IClickItemUserOptionListener iClickItemUserOptionListener, List<String> mOptionList, List<Drawable> mIconList) {
        this.iClickItemUserOptionListener = iClickItemUserOptionListener;
        this.mOptionList = mOptionList;
        this.mIconList = mIconList;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_option_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        String optionList = mOptionList.get(position);
        if (optionList == null) {
            return;
        }
        Drawable icon = mIconList.get(position);
        if (icon == null) {
            return;
        }
        holder.optionName.setText(optionList);
        holder.optionIcon.setCompoundDrawablesWithIntrinsicBounds(icon,null,null,null);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUserOptionListener.onClickItemUserOptionListener(optionList);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mOptionList != null) {
            return mOptionList.size();
        }
        return 0;
    }

    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        TextView optionName;
        TextView optionIcon;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            optionName = itemView.findViewById(R.id.OptionName);
            optionIcon = itemView.findViewById(R.id.IconUserOption);
        }
    }

}
