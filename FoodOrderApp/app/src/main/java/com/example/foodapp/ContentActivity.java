package com.example.foodapp;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.foodapp.Adapter.FoodAdapter;
import com.example.foodapp.Admin.fragment_admin.ManageFoodFragment;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.MySQLiteHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.foodapp.fragment.CartFragment;
import com.example.foodapp.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.foodapp.Adapter.PhotoAdapter;
import me.relex.circleindicator.CircleIndicator;

public class ContentActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    //<<<<<<< HEAD
    private RecyclerView rvcListContent;
    private ListContentCateAdapter listContentAdapter;
    private CategoryDataSource categoryDataSource;
    private FoodDataSource foodDataSource;
    private MySQLiteHelper mySQLiteHelper;
    private ManageFoodFragment manageFoodFragment;
    private View mView;
    //=======
    BottomNavigationView bottomNavigationView;

    //>>>>>>> ea3e8a2daacbf54febcb1accea284458ae1aef71
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

//<<<<<<< HEAD
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
//        displayFoodsForUser();
//        manageFoodFragment.displayFoods2();

//        displayAllFoodsForUser();

//        Cursor cursor = mySQLiteHelper.GetData("Select * from food");
//        while (cursor.moveToNext()) {
//            String ten = cursor.getString(1);
//            Toast.makeText(this,ten,Toast.LENGTH_SHORT).show();
//        }

//

//

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


    private void displayAllCateForUser() {
        rvcListContent = findViewById(R.id.rcv_list_content);
        listContentAdapter = new ListContentCateAdapter(this);

        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(this , RecyclerView.VERTICAL, false);
        rvcListContent.setLayoutManager(linearLayoutManager);

        listContentAdapter.setData(getAllCatesForUser());
        rvcListContent.setAdapter(listContentAdapter);
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

        list.add(new ListContentCate("Danh mục món ăn", listCate));
        return list;
    }

//    private void displayFoodsForUser(){
////        RecyclerView rcvFood = findViewById(R.id.rcv_list_content_food);
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
////        rcvFood.setLayoutManager(linearLayoutManager);
////
//////        FoodAdapter foodAdapter = new FoodAdapter(getListFood());
////        rcvFood.setAdapter(foodAdapter);
////    }
    private List<Food> getListFood(){
        List<Food> list = new ArrayList<>();
        mySQLiteHelper = new MySQLiteHelper(this);
        Cursor cursor = mySQLiteHelper.GetData("Select * from food");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String namefood = cursor.getString(1);
            int priceFood = cursor.getInt(2);
            String desFood = cursor.getString(3);
            int sizeFood = cursor.getInt(4);
            byte[] img = imagemTratada(cursor.getBlob(5));
            int categoryId = cursor.getInt(6);
            Category category = getCatebyID(categoryId);
            list.add(new Food(id,namefood, desFood,priceFood,sizeFood,img, category ));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

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

    private byte[] imagemTratada(byte[] imagem_img){

        while (imagem_img.length > 500000){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imagem_img = stream.toByteArray();
        }
        return imagem_img;

    }
//=======


    public void FragmentAdd(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_address_pickup, new AddressFragment()).commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_address_pickup,fragment);
        fragmentTransaction.commit();
    }
//>>>>>>> ea3e8a2daacbf54febcb1accea284458ae1aef71
}