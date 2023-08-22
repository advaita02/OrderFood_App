package com.example.foodapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.Adapter.FoodCartAdapter;
import com.example.foodapp.Adapter.OrderDetailAdapter;
import com.example.foodapp.Database.DataSource.OrderItemDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.OrderItem;
import com.example.foodapp.R;

import java.text.ParseException;
import java.util.ArrayList;

public class OrderItemFragment extends Fragment {
    RecyclerView recyclerView;
    OrderDetailAdapter orderDetailAdapter;

    OrderItemDataSource orderItemDataSource;
    ArrayList<OrderItem> orderItems;
    Context context;
    public OrderItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.orderDetailList);
        displayFoods();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        final View rootView = inflater.inflate(R.layout.fragment_order_item, container, false);
        orderItemDataSource = new OrderItemDataSource(context);
        try {
            orderItems = orderItemDataSource.getOrderItemsByOrderId(7);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return rootView;
    }
    public void displayFoods() {

        orderDetailAdapter = new OrderDetailAdapter((Context) getActivity(), R.layout.list_row_cate, orderItems, orderItemDataSource);
        recyclerView.setAdapter(orderDetailAdapter);
    }
}