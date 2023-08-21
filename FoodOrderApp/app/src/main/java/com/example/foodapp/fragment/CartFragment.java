package com.example.foodapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.Adapter.FoodCartAdapter;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    FoodDataSource foodDataSource;
    FoodCartAdapter foodCartAdapter;
    private ArrayList<Food> foods = new ArrayList<>();

    TextView txtTotalPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        txtTotalPrice = rootView.findViewById(R.id.priceOrder);
        addFoodtoCart("Com ga an cut",1000,2);
        addFoodtoCart("Com ga an cut",1000,2);
        addFoodtoCart("Com ga an cut",1000,2);
        addFoodtoCart("Com ga an cut",1000,2);

        Integer total = 0;

        for(Food c : foods){
           total += c.getPrice()*c.getQuantity();
        }
        txtTotalPrice.setText(Integer.toString(total));

        // Inflate the layout for this fragment

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.foodCartList);
        displayFoods();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }
    public void displayFoods() {

        foodCartAdapter = new FoodCartAdapter((Context) getActivity(), R.layout.list_row_cate, foods, foodDataSource);
        recyclerView.setAdapter(foodCartAdapter);
    }

    public void addFoodtoCart(String name_food,int price_food,int quantity){
        Food food = new Food(name_food,price_food,quantity);
        this.getFoods().add(food);
    }

    public List<Food> getFoods() {
        return foods;
    }

}