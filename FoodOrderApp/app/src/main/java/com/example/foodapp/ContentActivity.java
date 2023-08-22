package com.example.foodapp;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
//<<<<<<< HEAD
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.foodapp.Adapter.FoodAdapterUser;

import com.example.foodapp.Adapter.FoodAdapter;
import com.example.foodapp.Admin.fragment_admin.ManageFoodFragment;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.MySQLiteHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.example.foodapp.fragment.FoodFragment;
import com.example.foodapp.fragment.CartFragment;
import com.example.foodapp.fragment.ProfileFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.foodapp.Adapter.PhotoAdapter;
import me.relex.circleindicator.CircleIndicator;

public class ContentActivity extends AppCompatActivity {
    private ViewPager viewPager;
//    private CircleInicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private RecyclerView rvcListContent, rcvFood;
    private ListContentCateAdapter listContentCateAdapter;
//    private RecyclerView rvcListContent;
    private ListContentCateAdapter listContentAdapter;

    private CategoryDataSource categoryDataSource;
    private FoodDataSource foodDataSource;
    private MySQLiteHelper mySQLiteHelper;
    private ManageFoodFragment manageFoodFragment;
    private View mView;

    private CardView cardView;
    private LinearLayout linearLayout;
    private List<Food> foodList;

    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);
        //switch view khi dung navbar
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.action_home:
                    break;
                case R.id.action_order:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.action_notify:
                    break;
                case R.id.action_profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });

        ViewPager viewPager = findViewById(R.id.viewPager);
        int[] imageIds = {R.drawable.qc1, R.drawable.qc2, R.drawable.qc3, R.drawable.qc4};
        PhotoAdapter adapter = new PhotoAdapter(this, imageIds);
        viewPager.setAdapter(adapter);

        Button edittext_fragment_address;

        edittext_fragment_address = findViewById(R.id.edittext_fragment_address);

        edittext_fragment_address.setOnClickListener(v -> {
            Fragment fragment_address = new AddressFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, fragment_address, null).commit();
        });

        displayAllCateForUser();
        displayFoodsForUser();
            ImageView img_filter = findViewById(R.id.menu_icon);
            img_filter.setOnClickListener(v -> {
                Intent intent = new Intent(this, FoodDetail.class);
                startActivity(intent);
            });



    }


    private class WaitTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // Đây là nơi thực hiện công việc mà bạn muốn chờ
            // Ví dụ: Kết nối vào cơ sở dữ liệu, tải dữ liệu, xử lý dữ liệu, v.v.
            // Sau khi công việc xong, ta return null hoặc giá trị cần trả về.
            displayAllCateForUser();
            displayFoodsForUser();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Đây là nơi chạy sau khi công việc trong doInBackground hoàn thành
            // Ở đây bạn có thể thực hiện các công việc khác sau khi đã chờ xong.
//            linearLayout = (LinearLayout) findViewById(R.id.product);
//            linearLayout.setOnClickListener(v -> {
//                Fragment fragment_food = new FoodFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, fragment_food, null).commit();
//        });

//            ImageView img_filter = findViewById(R.id.img_product);
//            img_filter.setOnClickListener(v -> {
//                Fragment fragment_food = new FoodFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, fragment_food, null).commit();
//            });

        }
    }

    private void sendDataFoodToFoodDetail() {
//        linearLayout = (LinearLayout) findViewById(R.id.product);
//        linearLayout.setOnClickListener(v -> {
//            Fragment fragment_food = new FoodFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, fragment_food, null).commit();
//        });
    }
    private void displayAllCateForUser() {
        rvcListContent = findViewById(R.id.rcv_list_content);
        listContentCateAdapter = new ListContentCateAdapter(this);

        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(this , RecyclerView.VERTICAL, false);
        rvcListContent.setLayoutManager(linearLayoutManager);

        listContentCateAdapter.setData(getAllCatesForUser());
        rvcListContent.setAdapter(listContentCateAdapter);

    }

    private List<ListContentCate> getAllCatesForUser() {
        List<ListContentCate> list = new ArrayList<>();

        List<Category> listCate = new ArrayList<>();
        mySQLiteHelper = new MySQLiteHelper(this);
        Cursor data = mySQLiteHelper.GetData("Select * from category");
        while (data.moveToNext()) {
            int id = data.getInt(0);
            byte[] img = data.getBlob(1);
            String nameCate = data.getString(2);
            listCate.add(new Category(id, nameCate, img));
        }
        data.close();
        list.add(new ListContentCate("Danh mục món ăn", listCate));
        return list;
    }

    private void displayFoodsForUser(){
        rcvFood = findViewById(R.id.rcv_list_content_food);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , RecyclerView.VERTICAL, false);
        FoodAdapterUser foodAdapterUser = new FoodAdapterUser(this,getListFood());
        rcvFood.setAdapter(foodAdapterUser);

        rcvFood.setLayoutManager(linearLayoutManager);




    }

    private List<Food> getListFood() {
        ArrayList<Food> foods = new ArrayList<>();
        FoodDataSource foodDataSource = new FoodDataSource(this);
        int length_id_food = foodDataSource.getCountOfIds();

        for ( int id_cursor = 0; id_cursor < length_id_food; id_cursor++) {
            Cursor cursor = mySQLiteHelper.getReadableDatabase().rawQuery("Select * from food where id_food = ?",
                    new String[]{String.valueOf(id_cursor )});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String nameFood = cursor.getString(1);
                int price = cursor.getInt(2);
                String describe = cursor.getString(3);
                int size = cursor.getInt(4);
                byte[] img = cursor.getBlob(5);
                int categoryId = cursor.getInt(6);
                Category category = getCatebyID(categoryId);
                foods.add(new Food(id, nameFood, describe, price, size, img, category));
            }
            cursor.close();
        }
        return foods;
    }





//    private void displayFoodsForUser(){
////        RecyclerView rcvFood = findViewById(R.id.rcv_list_content_food);
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
////        rcvFood.setLayoutManager(linearLayoutManager);
////
//////        FoodAdapter foodAdapter = new FoodAdapter(getListFood());
////        rcvFood.setAdapter(foodAdapter);
////    }


//    private void displayAllFoodsForUser () {
//        rvcListContent = new RecyclerView(this);
//        rvcListContent = findViewById(R.id.rcv_list_content_food);
//        listContentFoodAdapter = new ListContentFoodAdapter(this);
//
//        LinearLayoutManager linearLayout = new  LinearLayoutManager(this , RecyclerView.HORIZONTAL, false);
//        rvcListContent.setLayoutManager(linearLayout);
//
//        listContentFoodAdapter.setData(getAllFoodsForUser());
//        rvcListContent.setAdapter(listContentFoodAdapter);
//
//    }
//    private List<ListContentFood> getAllFoodsForUser() {
//        List<ListContentFood> list = new ArrayList<>();
//
//        List<Food> listFood = new ArrayList<>();
//        mySQLiteHelper = new MySQLiteHelper(this);
//        Cursor data = mySQLiteHelper.GetData("Select * from food");
//        while (data.moveToNext()) {
//            int id = data.getInt(0);
//            String namefood = data.getString(1);
//            int priceFood = data.getInt(2);
//            String desFood = data.getString(3);
//            int sizeFood = data.getInt(4);
//            byte[] img = imagemTratada(data.getBlob(5));
//            int categoryId = data.getInt(6);
//            Category category = getCatebyID(categoryId);
//            listFood.add(new Food(id,namefood, desFood,priceFood,sizeFood,img, category ));
//        }
//
//        list.add(new ListContentFood("Danh sach món ăn", listFood));
//        return list;
//    }

    private Category getCatebyID(int id){
        Cursor cursor_cate = mySQLiteHelper.getReadableDatabase().rawQuery("Select * from category where id_category = ?",
                new String[] {String.valueOf(id)});
        Category category = new Category();
        if (cursor_cate.moveToFirst()) {
            do {
                category.setId(cursor_cate.getInt(0));
                category.setImg_cate(cursor_cate.getBlob(1));
                category.setName(cursor_cate.getString(2));
            } while (cursor_cate.moveToNext());
        }
        return category;
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_address_pickup,fragment);
        fragmentTransaction.commit();
    }
}