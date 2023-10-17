package com.example.demofacebook.Adapter.StudioDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.MyArrayAdapterHolder> implements Filterable {
    private List<Studio> mListStudio;
    private List<Studio> mListStudioOld;
    private IClickItemServiceListener iClickItemServiceListener;

    public StudioAdapter(List<Studio> mListStudio, IClickItemServiceListener iClickItemServiceListener) {
        this.mListStudio = mListStudio;
        this.mListStudioOld = mListStudio;
        this.iClickItemServiceListener = iClickItemServiceListener;
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_studio_item, parent, false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        Studio studio = mListStudio.get(position);
        if (studio == null) {
            return;
        }
        Picasso.get().load(studio.getAvatarStudio())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.imageService);
        holder.serviceName.setText(studio.getName());
        holder.ratingService.setText("⭐: " + studio.getTotalRating());

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
//        holder.servicePrice.setText(numberFormat.format(studio.getBalance()) + " VND");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemServiceListener.onClickItemService(studio);
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
                    }
                    if ("@!Top Rating".equals(strSearch)) {
                        list = mListStudioOld;
                        list.sort((service, t1) -> {
                            return (int) (t1.getTotalRating() - service.getTotalRating());
                        });
                    }
                    //Search bar
                    else {
                        for (Studio studio : mListStudioOld) {
                            if (studio.getName().toLowerCase().trim().contains(strSearch.toLowerCase().trim())) {
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


    public class MyArrayAdapterHolder extends RecyclerView.ViewHolder {
        ImageView imageService;
        TextView serviceName;
        TextView ratingService;

        public MyArrayAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageService = itemView.findViewById(R.id.ServiceImage);
            serviceName = itemView.findViewById(R.id.NameService);
            ratingService = itemView.findViewById(R.id.ServiceRating);
        }
    }
}
