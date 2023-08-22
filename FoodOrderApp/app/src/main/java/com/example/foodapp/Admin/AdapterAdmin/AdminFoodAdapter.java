package com.example.foodapp.Admin.AdapterAdmin;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Constants;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.R;
import com.example.foodapp.Admin.dialog.FoodDialog;

import java.util.ArrayList;

public class AdminFoodAdapter extends RecyclerView.Adapter<AdminFoodAdapter.ViewHolder> {

    private Context context;
    private int singleData;
    private ArrayList<Food> foods;
    private FoodDataSource foodDataSource;

    public AdminFoodAdapter(Context context, int singleData, ArrayList<Food> foods, FoodDataSource foodDataSource) {
        this.context = context;
        this.singleData = singleData;
        this.foods = foods;
        this.foodDataSource = foodDataSource;
    }

    @NonNull
    @Override
    public AdminFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
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
            if(food.getCategory() != null) {
                holder.nameCateOfFood.setText(food.getCategory().getName());
            }
        }

        // flow menu
        holder.flow_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.flow_menu);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_menu:
                                //sua food
                                Constants.isInsertFood = false;
                                showFoodDialog(food);
                                break;
                            case R.id.delete_menu:
                                //xoa cate
                                foodDataSource.open();
                                foodDataSource.deleteFood(food);
                                Toast.makeText(context, "Đã xoá thức ăn", Toast.LENGTH_SHORT).show();
                                foodDataSource.close();
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

    private void showFoodDialog(Food food) {
        if(food != null) {
            FoodDialog dialogFragment = FoodDialog.newInstance(food);
            dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "dialog_fragment");
        }
    }


    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView txtNameFood, describe, price, nameCateOfFood;
        ImageButton flow_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFood = (ImageView) itemView.findViewById(R.id.view_food_img);
            txtNameFood = (TextView) itemView.findViewById(R.id.txt_name_food);
            flow_menu = (ImageButton) itemView.findViewById(R.id.flowmenu);
            describe = (TextView) itemView.findViewById(R.id.describe);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            nameCateOfFood = (TextView) itemView.findViewById(R.id.name_cate_of_food);
        }
    }
}
