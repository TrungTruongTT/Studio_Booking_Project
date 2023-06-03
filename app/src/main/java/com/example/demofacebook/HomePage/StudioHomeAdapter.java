package com.example.demofacebook.HomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.List;

public class StudioHomeAdapter extends RecyclerView.Adapter<StudioHomeAdapter.MyArrayAdapterHolder>{

    private Context mContext;
    private List<Studio> mListStudio;

    public StudioHomeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void SetData(List<Studio> list){
        this.mListStudio = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_studio_home_item,parent,false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        Studio studio = mListStudio.get(position);
        if(studio== null){
            return;
        }
        holder.img_phone.setImageResource(studio.getImage());
        holder.txtTitle.setText(studio.getTitle());
        holder.txtDescription.setText(studio.getDescription());
        holder.txtPrice.setText(studio.getPrice());
        holder.txtRating.setText(studio.getRating());
    }

    @Override
    public int getItemCount() {
        if(mListStudio!= null){
            return mListStudio.size();
        }
        return 0;
    }

    public  class MyArrayAdapterHolder  extends RecyclerView.ViewHolder {
        ImageView img_phone;
        TextView txtTitle;
        TextView txtDescription;
        TextView txtPrice;
        TextView txtRating;
    public MyArrayAdapterHolder(@NonNull View itemView) {
        super(itemView);
         img_phone = itemView.findViewById(R.id.imgListView);
         txtTitle = itemView.findViewById(R.id.txtNameStudio);
         txtDescription = itemView.findViewById(R.id.txtStudioDes);
         txtPrice = itemView.findViewById(R.id.txtPrice);
         txtRating = itemView.findViewById(R.id.txtRating);
    }
}

}


//    LayoutInflater myinflater = context.getLayoutInflater();
//        convertView = myinflater.inflate(idLayout,null);
//                Studio myStudio = myList.get(position);
//                ImageView img_phone = convertView.findViewById(R.id.imgListView);
//                img_phone.setImageResource(myStudio.getImage());
//                TextView txtTitle = convertView.findViewById(R.id.txtNameStudio);
//                txtTitle.setText(myStudio.getTitle());
//                TextView txtDescription = convertView.findViewById(R.id.txtStudioDes);
//
//                TextView txtPrice = convertView.findViewById(R.id.txtPrice);
//
//                TextView txtRating = convertView.findViewById(R.id.txtRating);
//