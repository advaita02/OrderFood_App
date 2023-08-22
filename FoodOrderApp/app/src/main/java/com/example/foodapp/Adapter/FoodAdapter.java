package com.example.foodapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> mFood;
    private Context mContext;
    int singleData;
    FoodDataSource foodDataSource;



    public void setData(List<Food> list) {
        this.mFood = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = mFood.get(position);
        byte[] img = food.getImgFood();
        if (food != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.img_food.setImageBitmap(bitmap);
            holder.name_food.setText(food.getName());
            holder.des_food.setText(food.getDescribe());
            holder.price_food.setText(food.getPrice());
        }
    }

    @Override
    public int getItemCount() {
        if (mFood != null) {
            return mFood.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_food;
        private int id_food;
        private TextView name_food;
        private TextView des_food;
        private  TextView price_food;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            img_food = itemView.findViewById(R.id.img_product);
            name_food = itemView.findViewById(R.id.name_product);
            des_food = itemView.findViewById(R.id.des_product);
            price_food = itemView.findViewById(R.id.price_product);
        }
    }

}
