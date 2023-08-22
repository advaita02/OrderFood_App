package com.example.foodapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.Entity.Category;

import java.util.List;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.CateViewHolder>{

    private List<Category>  mCategory;

    public void setData(List<Category> list) {
        this.mCategory = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CateViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mCategory != null) {
            return mCategory.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CateViewHolder holder, int position) {
        Category category = mCategory.get(position);
        byte[] img = category.getImg_cate();
        if (category != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.img_cateory.setImageBitmap(bitmap);
            holder.name_category.setText(category.getName());
        }
    }

    public class CateViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_cateory;
        private int id_category;
        private TextView name_category;

        public CateViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cateory = itemView.findViewById(R.id.img_cate);
            name_category = itemView.findViewById(R.id.name_cate);
        }
    }
}


