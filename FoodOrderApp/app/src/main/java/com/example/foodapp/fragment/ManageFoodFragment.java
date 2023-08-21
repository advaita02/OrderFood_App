package com.example.foodapp.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.AdminFoodAdapter;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.R;
import com.example.foodapp.fragment.dialog.InsertFoodDialog;

import java.util.ArrayList;

public class ManageFoodFragment extends Fragment {
    Button btnOpenDialogFood;
    RecyclerView recyclerView;
    FoodDataSource foodDataSource;
    AdminFoodAdapter adminFooAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_managefood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnOpenDialogFood = (Button) view.findViewById(R.id.btnDialogInsertFood);
        btnOpenDialogFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogInsert();
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.list_food);
        displayFoods();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }
    public void openDialogInsert() {
        InsertFoodDialog insertFoodDialog = new InsertFoodDialog();
        insertFoodDialog.show(getActivity().getSupportFragmentManager(), "insert food dialog");
    }

    public void displayFoods() {
        foodDataSource = new FoodDataSource(getActivity());
        foodDataSource.open();
        Cursor cursor = foodDataSource.getAllFoods();
        ArrayList<Food> foods = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String nameFood = cursor.getString(1);
            int price = cursor.getInt(2);
            String describe = cursor.getString(3);
            int size = cursor.getInt(4);
            byte[] img = cursor.getBlob(5);
            int categoryId = cursor.getInt(6);

            Category category = getCategory(categoryId);
            foods.add(new Food(id, nameFood, describe, price, size, img, category));
            cursor.moveToNext();
        }
        cursor.close();
        adminFooAdapter = new AdminFoodAdapter(getActivity(), R.layout.list_row_cate, foods, foodDataSource);
        recyclerView.setAdapter(adminFooAdapter);
    }

    private Category getCategory(int categoryId) {
        CategoryDataSource categoryDataSource = new CategoryDataSource(getActivity());
        categoryDataSource.open();
        Category category = categoryDataSource.getCategoryById(categoryId);
        categoryDataSource.close();
        return category;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        foodDataSource.close();
    }
}
