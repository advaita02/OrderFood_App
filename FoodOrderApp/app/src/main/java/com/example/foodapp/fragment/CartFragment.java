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
import android.widget.Toast;

import com.example.foodapp.Adapter.AdminFoodAdapter;
import com.example.foodapp.Adapter.FoodCartAdapter;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.DataSource.OrderDataSource;
import com.example.foodapp.Database.DataSource.OrderItemDataSource;
import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    FoodDataSource foodDataSource;
    FoodCartAdapter foodCartAdapter;
    OrderDataSource orderDataSource;
    UserDataSource userDataSource;

    OrderItemDataSource orderItemDataSource;

    Context context;

    private Integer foodID;
    private String nameFood;
    private Integer priceFood;
    private Integer quantity;
    private ArrayList<Food> foods = new ArrayList<>();

    TextView txtTotalPrice;
    Button btnOrder;
    Date date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ////
        addFoodtoCart(1,"banh mi pew pew",10000,3);
        addFoodtoCart(2,"bun bo",5000,5);
        addFoodtoCart(3,"banh canh",15000,2);
        ///
        final View rootView = inflater.inflate(R.layout.fragment_cart,container,false);
        context = container.getContext();
        userDataSource = new UserDataSource(context);
        orderDataSource = new OrderDataSource(context);
        orderItemDataSource = new OrderItemDataSource(context);
        txtTotalPrice = rootView.findViewById(R.id.priceOrder);
        btnOrder = rootView.findViewById(R.id.btnOrder);
        date = new Date();
        String strDate=OrderDataSource.dateFormat.format(date);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert order
                try {
                    orderDataSource.insertOrder(userDataSource.getIdUser(LoginActivity.getPhoneNum()),strDate);
                    for(Food c : foods){
                        orderItemDataSource.insertOrderItem(orderDataSource.getLatestOrderId(),c.getId(),c.getQuantity());
                    }
                    Toast.makeText(context,"Đặt hàng thành công",Toast.LENGTH_LONG).show();
                    foods.clear();
                } catch (ParseException e) {
                    Toast.makeText(context,"Đặt hàng thất bại",Toast.LENGTH_LONG).show();
                }

            }
        });

        Integer total = 0;

        for(Food c : foods){
           total += c.getPrice()*c.getQuantity();
        }
        txtTotalPrice.setText("Tổng tiền: "+Integer.toString(total)+" VND");

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

    public void addFoodtoCart(int id,String name_food,int price_food,int quantity){
        Food food = new Food(id,name_food,price_food,quantity);
        getFoods().add(food);
        setFoodID(id);
        setNameFood(name_food);
        setPriceFood(price_food);
        setQuantity(quantity);
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public Integer getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(Integer priceFood) {
        this.priceFood = priceFood;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}