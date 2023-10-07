package com.example.demofacebook.Adapter.TimePicker;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.DatePicker;
import com.example.demofacebook.R;

import java.util.List;

public class DatePickerAdapter extends RecyclerView.Adapter<DatePickerAdapter.OrderViewHolder> {
    private final List<DatePicker> mList;
    private final IClickItemDatePickerListener datePickerListener;
    int row_index = -1;

    public DatePickerAdapter(List<DatePicker> mList, IClickItemDatePickerListener datePickerListener) {
        this.mList = mList;
        this.datePickerListener = datePickerListener;
    }


    @NonNull
    @Override
    public DatePickerAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_date_picker_item, parent, false);
        return new DatePickerAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatePickerAdapter.OrderViewHolder holder, int position) {
        DatePicker datePicker = mList.get(position);
        if (datePicker == null) {
            return;
        }
        holder.datePicker_Date.setText(datePicker.getDate());
        holder.datePicker_Month.setText(datePicker.getMonth());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerListener.onItemDatePickerListener(datePicker);
                row_index = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

        if(row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.item_color_apple_login);
            holder.datePicker_Date.setTextColor(Color.parseColor("#ffffff"));
            holder.datePicker_Month.setTextColor(Color.parseColor("#ffffff"));

        }else {
            holder.linearLayout.setBackgroundResource(R.drawable.item_color_google_login);
            holder.datePicker_Date.setTextColor(Color.parseColor("#000000"));
            holder.datePicker_Month.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView datePicker_Date;
        TextView datePicker_Month;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.LinearLayoutDatePicker);
            datePicker_Date = itemView.findViewById(R.id.datePicker_Date);
            datePicker_Month = itemView.findViewById(R.id.datePicker_Month);
        }
    }
}
