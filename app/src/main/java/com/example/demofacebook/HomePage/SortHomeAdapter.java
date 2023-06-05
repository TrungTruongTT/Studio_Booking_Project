package com.example.demofacebook.HomePage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.MyInterface.IClickItemSortListener;
import com.example.demofacebook.R;

import java.util.List;

public class SortHomeAdapter extends RecyclerView.Adapter<SortHomeAdapter.MyArrayAdapterHolder> {
private final IClickItemSortListener iClickItemSortListener;
    private final List<String> mSortList;

    public SortHomeAdapter(List<String> mSortList, IClickItemSortListener listener) {
        this.mSortList = mSortList;
        this.iClickItemSortListener =listener;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sort_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        String sortItem = mSortList.get(position);
        if (sortItem == null) {
            return;
        }
        holder.txtSortBy.setText(sortItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemSortListener.onClickItemSort(sortItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mSortList != null) {
            return mSortList.size();
        }
        return 0;
    }

    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        TextView txtSortBy;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            txtSortBy = itemView.findViewById(R.id.SortBy);
        }
    }

}
