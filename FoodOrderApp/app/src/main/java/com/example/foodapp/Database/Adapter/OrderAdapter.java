package com.example.foodapp.Database.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.DataSource.OrderDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList<Order> orders;
    int singleData;
    private Context context;
    OrderDataSource orderDataSource;
    public OrderAdapter (Context context, int singleData, ArrayList<Order> orders, OrderDataSource orderDataSource){
        this.context = context;
        this.singleData = singleData;
        this.orders = orders;
        this.orderDataSource = orderDataSource;
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist_item,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        if (order == null) {
            return;
        }
        holder.idOrder.setText(order.getId());
        holder.dateOrder.setText(OrderDataSource.dateFormat.format(order.getDate()));
    }

    @Override
    public int getItemCount() {
            return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView idOrder;
        TextView dateOrder;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            idOrder = itemView.findViewById(R.id.idOrder);
            dateOrder = itemView.findViewById(R.id.dateOrder);

        }
    }
}
