package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.OrderItemDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.OrderItem;
import com.example.foodapp.Database.Entity.OrderItem;
import com.example.foodapp.R;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>{

    private Context mContext;
    int singleData;
    ArrayList<OrderItem> orderItems;
    OrderItemDataSource orderItemDataSource;

    public OrderDetailAdapter(Context mContext, int singleData, ArrayList<OrderItem> foods, OrderItemDataSource orderItemDataSource) {
        this.mContext = mContext;
        this.singleData = singleData;
        this.orderItems = foods;
        this.orderItemDataSource = orderItemDataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.orderdetail_item, null);
        return new OrderDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderItem food = orderItems.get(position);
//        holder.txtNameFood.setText(food.getName());
//        holder.price.setText(Integer.toString(food.getPrice()));
//        holder.quantity.setText("Số lượng: " + Integer.toString(food.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView txtNameFood, quantity, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFood = (ImageView) itemView.findViewById(R.id.fooddetaill_img);
            txtNameFood = (TextView) itemView.findViewById(R.id.txtFoodDetailName);
            quantity = (TextView) itemView.findViewById(R.id.quantityDetail);
            price = (TextView) itemView.findViewById(R.id.txtPriceFoodDetail);
        }
    }

}
