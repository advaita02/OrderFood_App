package com.example.foodapp.Admin.StatisticActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodapp.Admin.AdapterAdmin.AdminFoodAdapter;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.R;

import java.util.ArrayList;

public class ActivityListBestSelling extends AppCompatActivity {
    private FoodDataSource foodDataSource;
    RecyclerView listBestSelling;
    AdminFoodAdapter adminFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_best_selling);

        listBestSelling = findViewById(R.id.list_bestselling);
        foodDataSource = new FoodDataSource(this);
        foodDataSource.open();
        ArrayList<Food> bestSellings = foodDataSource.getBestSellingFoods();
        foodDataSource.close();
        adminFoodAdapter = new AdminFoodAdapter(this, R.layout.list_row_cate,
                bestSellings, foodDataSource);
        listBestSelling.setAdapter(adminFoodAdapter);
        listBestSelling.setLayoutManager(new LinearLayoutManager(this));
    }
}