package com.example.foodapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.R;

import java.util.ArrayList;

public class AdminFoodAdapter extends RecyclerView.Adapter<AdminFoodAdapter.ViewHolder> {

    private Context mContext;
    int singleData;
    ArrayList<Food> foods;
    FoodDataSource foodDataSource;

    public AdminFoodAdapter(Context mContext, int singleData, ArrayList<Food> foods, FoodDataSource foodDataSource) {
        this.mContext = mContext;
        this.singleData = singleData;
        this.foods = foods;
        this.foodDataSource = foodDataSource;
    }

    @NonNull
    @Override
    public AdminFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_row_food, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminFoodAdapter.ViewHolder holder, int position) {
        final Food food = foods.get(position);
        byte[] img = food.getImgFood();
        if (img != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.imageFood.setImageBitmap(bitmap);
            holder.txtNameFood.setText(food.getName());
            holder.price.setText(Integer.toString(food.getPrice()));
            holder.describe.setText(food.getDescribe());
            // Sử dụng bitmap tại đây
        }

        // flow menu
        holder.flow_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.flow_menu);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_menu:
                                //sua food
//
                                break;
                            case R.id.delete_menu:
                                //xoa cate
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                //hien thi menu
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView txtNameFood, describe, price;
        ImageButton flow_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFood = (ImageView) itemView.findViewById(R.id.view_food_img);
            txtNameFood = (TextView) itemView.findViewById(R.id.txt_name_food);
            flow_menu = (ImageButton) itemView.findViewById(R.id.flowmenu);
            describe = (TextView) itemView.findViewById(R.id.describe);
            price = (TextView) itemView.findViewById(R.id.txt_price);
        }
    }
}
