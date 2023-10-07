package com.example.demofacebook.Adapter.TimePicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.R;
import com.example.demofacebook.TimePicker;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TimePickerAdapter extends RecyclerView.Adapter<TimePickerAdapter.OrderViewHolder> implements Filterable {
    private List<TimePicker> mList;
    private final List<TimePicker> mListOld;

    public TimePickerAdapter(List<TimePicker> mList) {
        this.mList = mList;
        this.mListOld = mList;
    }


    @NonNull
    @Override
    public TimePickerAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_time_picker_item, parent, false);
        return new TimePickerAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimePickerAdapter.OrderViewHolder holder, int position) {
        TimePicker timePicker = mList.get(position);
        if (timePicker == null) {
            return;
        }
        Picasso.get()
                .load(timePicker.getStudioImage())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.studioImage);

        holder.studioName.setText(timePicker.getStudioName());
        holder.studioDescription.setText(timePicker.getStudioDescription());
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        holder.studioPrice.setText(numberFormat.format(timePicker.getPrice()) + " VND");
        holder.timePicked.setText(timePicker.getTime());

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public ImageView studioImage;
        public TextView studioName;
        public TextView studioDescription;
        public TextView studioPrice;
        public TextView timePicked;
        public CheckBox checkBox;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            studioImage = itemView.findViewById(R.id.timepicker_StudioImage);
            studioName = itemView.findViewById(R.id.timepicker_StudioName);
            studioDescription = itemView.findViewById(R.id.timepicker_StudioDescription);
            studioPrice = itemView.findViewById(R.id.timepicker_StudioPrice);
            timePicked = itemView.findViewById(R.id.timepicker_StudioTime);
            checkBox = itemView.findViewById(R.id.timepicker_CheckBox);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strDateMonth = charSequence.toString();
                List<TimePicker> list = new ArrayList<>();
                if (strDateMonth.isEmpty()) {
                    mList = mListOld;
                }
                if (!strDateMonth.isEmpty()) {
                    //sortBy Category - sortByItem
                    if ("@!All".equals(strDateMonth)) {
                        for (TimePicker timePicker : mListOld) {
                            if (true) {
                                list.add(timePicker);
                            }
                        }
                    }
                    //Search bar
                    else {
                        for (TimePicker timePicker : mListOld) {
                            if (timePicker.getStudioName().toLowerCase().trim().contains(strDateMonth.toLowerCase().trim())) {
                                list.add(timePicker);
                            }
                        }
                    }
                }
                mList = list;
                FilterResults filterResults = new FilterResults();
                filterResults.values = mList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mList = (List<TimePicker>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
