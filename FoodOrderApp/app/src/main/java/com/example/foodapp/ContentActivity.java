package com.example.foodapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ContentActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private RecyclerView rvcListContent;
    private ListContentCateAdapter listContentAdapter;
    private ListContentFoodAdapter listContentFoodAdapter;
    private CategoryDataSource categoryDataSource;
    private FoodDataSource foodDataSource;
    private MySQLiteHelper mySQLiteHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        ViewPager viewPager = findViewById(R.id.viewPager);
        int[] imageIds = {R.drawable.qc1, R.drawable.qc2, R.drawable.qc3, R.drawable.qc4};
        PhotoAdapter adapter = new PhotoAdapter(this, imageIds);
        viewPager.setAdapter(adapter);

        Button edittext_fragment_address;


        edittext_fragment_address = findViewById(R.id.edittext_fragment_address);



        edittext_fragment_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_address = new AddressFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, fragment_address, null).commit();
            }
        });

//        mySQLiteHelper = new MySQLiteHelper(this);
//        int j = 1;
//        for (int i = 1; i <= 10; i ++) {
//            String temp = "Com tam suon so ";
//            temp = temp.concat(Integer.toString(j));
//            j++;
//            mySQLiteHelper.insertCategory(temp);
//        }
//        mySQLiteHelper.insertCategory("Com tam so 1");
//        mySQLiteHelper.insertCategory("Com tam so 2");
//        mySQLiteHelper.insertCategory("Com tam so 3");
//        mySQLiteHelper.insertCategory("Com tam so 4");
//        mySQLiteHelper.insertCategory("Com tam so 5");
        mySQLiteHelper = new MySQLiteHelper(this);
        mySQLiteHelper.insertFood("Bun thit nuong 1", 75, "ngon vcl");
        mySQLiteHelper.insertFood("Bun thit nuong 2", 85, "ngon vcl");
        mySQLiteHelper.insertFood("Bun thit nuong 3", 95, "ngon vcl");
        mySQLiteHelper.insertFood("Bun thit nuong 4", 100, "ngon vcl");

        foodDataSource = new FoodDataSource(this);
        foodDataSource.insertFood("bun thit nuong 1", 10, "good");



        Cursor data = mySQLiteHelper.GetData("Select * from food");
        while (data.moveToNext()) {
            String ten = data.getString(1);
            Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
        }

        rvcListContent = findViewById(R.id.rcv_list_content);
        listContentAdapter = new ListContentCateAdapter(this);

        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(this , RecyclerView.VERTICAL, false);
        rvcListContent.setLayoutManager(linearLayoutManager);

        listContentAdapter.setData(getListOfListContent());
        rvcListContent.setAdapter(listContentAdapter);

//        rvcListContent = new RecyclerView(this);
//        rvcListContent = findViewById(R.id.rcv_list_content_food);
//        listContentFoodAdapter = new ListContentFoodAdapter(this);
//
//        LinearLayoutManager linearLayout = new  LinearLayoutManager(this , RecyclerView.HORIZONTAL, false);
//        rvcListContent.setLayoutManager(linearLayout);
//
//        listContentFoodAdapter.setData(getListOfListContentFood());
//        rvcListContent.setAdapter(listContentFoodAdapter);

//        Fragment fragment_product = getSupportFragmentManager().findFragmentById(R.id.cardview);
//        LinearLayout imageView =(LinearLayout) fragment_product.requireView().findViewById(R.id.product);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment_address = new FoodFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_product_detail, fragment_address, null).commit();
//                    }
//                });
    }




    private List<ListContentCate> getListOfListContent() {
        List<ListContentCate> list = new ArrayList<>();

        List<Category> listCate = new ArrayList<>();
        mySQLiteHelper = new MySQLiteHelper(this);
        Cursor data = mySQLiteHelper.GetData("Select * from category");
        while (data.moveToNext()) {
            String ten = data.getString(1);
            listCate.add(new Category(R.drawable.pizza, ten));
        }

        list.add(new ListContentCate("Danh mục món ăn", listCate));
        return list;
    }

    private List<ListContentFood> getListOfListContentFood() {
        List<ListContentFood> list = new ArrayList<>();

        List<Food> listFood = new ArrayList<>();
        mySQLiteHelper = new MySQLiteHelper(this);
        Cursor data = mySQLiteHelper.GetData("Select * from food");
        while (data.moveToNext()) {
            String ten = data.getString(1);
            int price = data.getInt(2);
            String describe = data.getString(3);
            listFood.add(new Food(R.drawable.hamburger, ten, describe, price));
        }

        list.add(new ListContentFood("Danh sach món ăn", listFood));
        return list;
    }

}
