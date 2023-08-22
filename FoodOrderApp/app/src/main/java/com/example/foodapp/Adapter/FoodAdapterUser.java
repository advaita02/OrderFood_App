package com.example.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.FoodDetail;
import com.example.foodapp.databinding.ItemFoodBinding;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapterUser extends RecyclerView.Adapter<FoodAdapterUser.FoodUserViewHolder>   {
    private List<Food> mListFoods;
    private Context mContext;
    int singleData;
    ArrayList<Food> foods;
    FoodDataSource foodDataSource;

    public FoodAdapterUser(Context context ,List<Food> mListFoods) {
        this.mListFoods = mListFoods;
        this.mContext = context;
    }
    public FoodAdapterUser(Context mContext, int singleData, ArrayList<Food> foods, FoodDataSource foodDataSource) {
        this.mContext = mContext;
        this.singleData = singleData;
        this.foods = foods;
        this.foodDataSource = foodDataSource;
    }

    private ItemFoodBinding itemFoodBinding;

    @NonNull
    @Override
    public FoodUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodBinding itemFoodBinding = ItemFoodBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FoodUserViewHolder(itemFoodBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodUserViewHolder holder, int position) {
        final Food food = mListFoods.get(position);
        byte[] img = food.getImgFood();

        if (food != null) {
            holder.txtNameFood.setText(food.getName());
            holder.price.setText(String.valueOf(food.getPrice()));
            holder.describe.setText(food.getDescribe());
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.imageFood.setImageBitmap(bitmap);
            holder.linearLayout.setOnClickListener(v -> {
                onClickGoToDetail(food);
            });
        }
    }

    private void onClickGoToDetail(Food food){
        Intent intent = new Intent(mContext, FoodDetail.class);
        Bundle bundle = new Bundle();
        int food_id = food.getId();
        String food_name = food.getName();
        String food_des = food.getDescribe();
        int food_price = food.getPrice();
        int food_size = food.getSize();
        Toast.makeText(mContext,food_name,Toast.LENGTH_SHORT).show();
        Category id_cate = food.getCategory();
        bundle.putSerializable("object", food);
        intent.putExtra("food_id",food_id);
        intent.putExtra("food_name", food_name);
        intent.putExtra("food_des", food_des);
        intent.putExtra("food_price", food_price);
        intent.putExtra("food_size", food_size);
        intent.putExtra("id_cate", id_cate);

       mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mListFoods != null) {
            return mListFoods.size();
        }
        return 0;
    }

    public static class FoodUserViewHolder extends RecyclerView.ViewHolder{
        private final ItemFoodBinding itemFoodBinding;
        CardView cardView;
        LinearLayout linearLayout;
        ImageView imageFood;
        TextView txtNameFood, describe, price;

        public FoodUserViewHolder(@NonNull ItemFoodBinding itemFoodBinding ) {
            super(itemFoodBinding.getRoot());
            this.itemFoodBinding = itemFoodBinding;
            imageFood = itemFoodBinding.imgProduct;
            txtNameFood = itemFoodBinding.nameProduct;
            describe = itemFoodBinding.desProduct;
            price = itemFoodBinding.priceProduct;
            linearLayout = itemFoodBinding.product;
        }
    }


}
