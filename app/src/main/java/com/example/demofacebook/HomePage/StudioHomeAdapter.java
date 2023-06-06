package com.example.demofacebook.HomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.MyInterface.IClickItemStudioListener;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;

public class StudioHomeAdapter extends RecyclerView.Adapter<StudioHomeAdapter.MyArrayAdapterHolder> implements Filterable {

    private final IClickItemStudioListener iClickItemStudioListener;
    private List<Studio> mListStudio;
    private final List<Studio> mListStudioOld;


    public StudioHomeAdapter(List<Studio> mListStudio, IClickItemStudioListener listener) {
        this.mListStudio = mListStudio;
        this.mListStudioOld = mListStudio;
        this.iClickItemStudioListener = listener;
    }


    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_studio_home_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        Studio studio = mListStudio.get(position);
        if (studio == null) {
            return;
        }
        holder.img_phone.setImageResource(studio.getImage());
        holder.txtTitle.setText(studio.getTitle());
        holder.txtDescription.setText(studio.getDescription());
        holder.txtPrice.setText(studio.getPrice());
        holder.txtRating.setText(studio.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemStudioListener.onClickItemStudio(studio);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListStudio != null) {
            return mListStudio.size();
        }
        return 0;
    }

    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        private final ImageView img_phone;
        private final TextView txtTitle;
        private final TextView txtDescription;
        private final TextView txtPrice;
        private final TextView txtRating;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            img_phone = itemView.findViewById(R.id.imgListView);
            txtTitle = itemView.findViewById(R.id.txtNameStudio);
            txtDescription = itemView.findViewById(R.id.txtStudioDes);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRating = itemView.findViewById(R.id.txtRating);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                List<Studio> list = new ArrayList<>();
                if (strSearch.isEmpty()) {
                    mListStudio = mListStudioOld;
                }
                if (!strSearch.isEmpty()) {
                    //sortBy Category - sortByItem
                    if ("@!All".equals(strSearch)) {
                        for (Studio studio : mListStudioOld) {
                            if (true) {
                                list.add(studio);
                            }
                        }
                        //Search bar
                    }
                    if ("@!Sort 1".equals(strSearch)) {
                        for (Studio studio : mListStudioOld) {
                            if (studio.getTitle().toLowerCase().trim().contains("1".toLowerCase().trim())) {
                                list.add(studio);
                            }
                        }
                        //Search bar
                    } else {
                        for (Studio studio : mListStudioOld) {
                            if (studio.getTitle().toLowerCase().trim().contains(strSearch.toLowerCase().trim())) {
                                list.add(studio);
                            }
                        }
                    }
                }

                mListStudio = list;
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListStudio;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mListStudio = (List<Studio>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}


