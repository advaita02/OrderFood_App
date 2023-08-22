package com.example.foodapp.Admin.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.OrderDataSource;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.R;

import java.util.ArrayList;

public class OrderStatisticAdapter extends RecyclerView.Adapter<OrderStatisticAdapter.OrderViewHolder> {
    ArrayList<Order> orders;
    int singleData;
    private Context context;
    OrderDataSource orderDataSource;

    public OrderStatisticAdapter(Context context, int singleData, ArrayList<Order> orders, OrderDataSource orderDataSource){
        this.context = context;
        this.singleData = singleData;
        this.orders = orders;
        this.orderDataSource = orderDataSource;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_user_statistic_item,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        if (order != null) {
            holder.idOrderUser.setText(order.getId());
            holder.dateUserOrder.setText(OrderDataSource.dateFormat.format(order.getDate()));
            holder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {
            Toast.makeText(context, "Không có user nào đặt hàng", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView idOrderUser;
        TextView dateUserOrder;
        ImageButton detail;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            idOrderUser = itemView.findViewById(R.id.txt_id_user_order);
            dateUserOrder = itemView.findViewById(R.id.txt_date_user_order);
            detail = itemView.findViewById(R.id.img_button_detail_statistic);
        }
    }
}

