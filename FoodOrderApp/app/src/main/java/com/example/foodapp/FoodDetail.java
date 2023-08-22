package com.example.foodapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.MySQLiteHelper;
import com.example.foodapp.databinding.ActivityFooddetailBinding;
import com.example.foodapp.fragment.CartFragment;

import java.io.Serializable;

public class FoodDetail extends AppCompatActivity {
    public FoodDetail () {

    }
    private ActivityFooddetailBinding binding;
    View mView;
    MySQLiteHelper mySQLiteHelper;
    int quantity_food_pre = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFooddetailBinding.inflate(getLayoutInflater());
        mView = binding.getRoot();
        setContentView(mView);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Food food = (Food) bundle.get("object");

        TextView id_food = binding.txtIdFood;
        id_food.setText(String.valueOf(bundle.getInt("food_id")));
        TextView name_food = binding.productName;
        name_food.setText(bundle.getString("food_name"));
        TextView des_food = binding.productDes;
        des_food.setText(bundle.getString("food_des"));
        TextView price_food = binding.productPrice;
        price_food.setText(String.valueOf(bundle.getInt("food_price")));
        ImageView img_food = binding.productImg;
        mySQLiteHelper = new MySQLiteHelper(this);
        Cursor cursor = mySQLiteHelper.getReadableDatabase().rawQuery("Select * from food where id_food = ?", new String[]{
                String.valueOf(bundle.getInt("food_id"))
        });
        while (cursor.moveToNext()) {
            byte[] img = cursor.getBlob(5);
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            img_food.setImageBitmap(bitmap);
        }

        TextView food_quantity_pick = binding.productQuanityPick;

        ImageButton img_btn_inc = binding.btnIncreaseQuantity;
        img_btn_inc.setOnClickListener(v -> {
            quantity_food_pre += 1;
            food_quantity_pick.setText(String.valueOf(quantity_food_pre));
        });
        ImageButton img_btn_desc = binding.btnDesceraseQuantity;
        img_btn_desc.setOnClickListener(v -> {
            if (quantity_food_pre > 1 ) {
                quantity_food_pre -= 1;
                food_quantity_pick.setText(String.valueOf(quantity_food_pre));
            }
        });
        int number = Integer.parseInt(food_quantity_pick.getText().toString());
        CartFragment fragment = new CartFragment();
        Button addCart = binding.button;
        addCart.setOnClickListener(v -> {fragment.addFoodtoCart(bundle.getInt("food_id"),bundle.getString("food_name")
                ,bundle.getInt("food_price"),number);});
    }
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void backButtonClicked(View view) {
        finish();
    }
}
