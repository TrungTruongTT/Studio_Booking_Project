package com.example.demofacebook.HomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Model.Category;
import com.example.demofacebook.R;

import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.MyArrayAdapterHolder>{

    private Context mContext;
    private List<Category> mListCategory;

    public CategoryHomeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void SetData(List<Category> list){
        this.mListCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyArrayAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_item,parent,false);
        return new MyArrayAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapterHolder holder, int position) {
        Category category = mListCategory.get(position);
        if(category== null){
            return;
        }
        holder.imageCategory.setImageResource(category.getImageCategory());
        holder.categoryName.setText(category.getCategoryName());
    }

    @Override
    public int getItemCount() {
        if(mListCategory!= null){
            return mListCategory.size();
        }
        return 0;
    }

    public  class MyArrayAdapterHolder  extends RecyclerView.ViewHolder {
        ImageView imageCategory;
        TextView categoryName;
    public MyArrayAdapterHolder(@NonNull View itemView) {
        super(itemView);
        imageCategory = itemView.findViewById(R.id.ImageCategory);
        categoryName = itemView.findViewById(R.id.CategoryName);

    }
}

}
