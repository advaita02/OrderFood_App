package com.example.foodapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.Entity.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> mFood;

    public void setData(List<Food> list) {
        this.mFood = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = mFood.get(position);
        if (food == null) {
            return;
        }
        holder.img_food.setImageResource(food.getId());
        holder.name_food.setText(food.getName());
        holder.des_food.setText(food.getDescribe());
        holder.price_food.setText(food.getPrice());
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
