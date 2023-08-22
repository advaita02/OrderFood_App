package com.example.foodapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.DataSource.OrderDataSource;
import com.example.foodapp.Database.DataSource.OrderItemDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.R;
import com.example.foodapp.fragment.OrderItemFragment;
import com.example.foodapp.fragment.OrderedListFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList<Order> orders;
    int singleData;
    private Context mContext;
    OrderDataSource orderDataSource;
    OrderedListFragment fragment = new OrderedListFragment();

    public OrderAdapter (Context context, int singleData, ArrayList<Order> orders, OrderDataSource orderDataSource){
        this.mContext = context;
        this.singleData = singleData;
        this.orders = orders;
        this.orderDataSource = orderDataSource;
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist_item,parent,false);
        return new OrderViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        if (order == null) {
            return;
        }
        holder.idOrder.setText("Mã đơn hàng: "+Integer.toString(order.getId()));
        holder.dateOrder.setText("Ngày đặt: "+OrderDataSource.dateFormat.format(order.getDate()));
        holder.relativeLayout.setOnClickListener(view -> {replaceFragment(new OrderItemFragment());});

    }

    @Override
    public int getItemCount() {
            return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView idOrder;
        TextView dateOrder;
        RelativeLayout relativeLayout;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            idOrder = itemView.findViewById(R.id.idOrder);
            dateOrder = itemView.findViewById(R.id.dateOrder);
            relativeLayout = itemView.findViewById(R.id.relativelayout);
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_orderlist, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
