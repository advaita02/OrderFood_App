package com.example.foodapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.R;

import java.util.ArrayList;

public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.ViewHolder>{

    private Context mContext;
    int singleData;
    ArrayList<Food> foods;
    FoodDataSource foodDataSource;

    public FoodCartAdapter(Context mContext, int singleData, ArrayList<Food> foods, FoodDataSource foodDataSource) {
        this.mContext = mContext;
        this.singleData = singleData;
        this.foods = foods;
        this.foodDataSource = foodDataSource;
    }


    @NonNull
    @Override
    public FoodCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.food_cart_item, null);
        return new FoodCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCartAdapter.ViewHolder holder, int position) {
        final Food food = foods.get(position);
        holder.txtNameFood.setText(food.getName());
        holder.price.setText(Integer.toString(food.getPrice()));
        holder.quantity.setText("Số lượng: " + Integer.toString(food.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameFood, quantity, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameFood = (TextView) itemView.findViewById(R.id.txtFoodCartName);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            price = (TextView) itemView.findViewById(R.id.txtPriceFoodCart);
        }
    }

}
