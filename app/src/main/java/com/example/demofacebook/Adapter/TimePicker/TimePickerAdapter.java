package com.example.demofacebook.Adapter.TimePicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.SlotBooking;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TimePickerAdapter extends RecyclerView.Adapter<TimePickerAdapter.OrderViewHolder> {
    private List<SlotBooking> mList;
    private Studio studio;

    private List<Boolean> checkboxStates;

    public TimePickerAdapter(List<SlotBooking> mList, Studio studio) {
        this.mList = mList;
        this.studio = studio;
        checkboxStates = new ArrayList<>(Collections.nCopies(mList.size(), false));
    }


    @NonNull
    @Override
    public TimePickerAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_time_picker_item, parent, false);
        return new TimePickerAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimePickerAdapter.OrderViewHolder holder, int position) {
        SlotBooking slotBooking = mList.get(position);
        if (slotBooking == null) {
            return;
        }
        Picasso.get()
                .load(studio.getAvatarStudio())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.studioImage);

        holder.studioName.setText(studio.getName());
        holder.studioDescription.setText(studio.getName());
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        holder.studioPrice.setText(numberFormat.format(slotBooking.getPrice()) + " VND");
        String starTime = slotBooking.getStartTime();
        String endTime = slotBooking.getEndTime();
        holder.timePicked.setText(starTime + " - " + endTime);

        if (slotBooking.isBooked()) {
            holder.checkBox.setChecked(true);
            holder.checkBox.setEnabled(false);
        }

        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            checkboxStates.set(position, isChecked);
        });

    }
    public List<SlotBooking> getCheckedItems() {
        List<SlotBooking> checkedItems = new ArrayList<>();
        for (int i = 0; i < checkboxStates.size(); i++) {
            if (checkboxStates.get(i)) {
                checkedItems.add(mList.get(i));
            }
        }
        return checkedItems;
    }
//    public String formatTimeString(String string) {
//        ZonedDateTime zonedDateTime = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            zonedDateTime = ZonedDateTime.parse(string);
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String timeOnly = zonedDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
//            return timeOnly;
//        }
//        return null;
//    }

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
}
