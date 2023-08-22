package com.example.foodapp.Admin.StatisticActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.Admin.AdapterAdmin.OrderStatisticAdapter;
import com.example.foodapp.Database.DataSource.OrderDataSource;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.R;

import java.text.ParseException;
import java.util.ArrayList;

public class ActivityStatisticUser extends AppCompatActivity {
    private EditText idUser;
    private Button buttonStatisUser;
    private RecyclerView recyclerView;
    private OrderDataSource orderDataSource;
    private OrderStatisticAdapter orderStatisticAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_user);

        mapping();

        buttonStatisUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showOrderUser();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void showOrderUser() throws ParseException {
        orderDataSource = new OrderDataSource(this);
        String idUserText = idUser.getText().toString();
        if (!TextUtils.isEmpty(idUserText)) {
            int userId = Integer.parseInt(idUserText);
            ArrayList<Order> listUserOrder = orderDataSource.getOrdersByUser(userId);
            orderStatisticAdapter = new OrderStatisticAdapter(this,
                    R.layout.order_user_statistic_item, listUserOrder, orderDataSource);
            recyclerView.setAdapter(orderStatisticAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(this, "Bạn chưa nhập id", Toast.LENGTH_SHORT).show();
        }
    }

    public void mapping() {
        idUser = findViewById(R.id.edit_user_statistic);
        buttonStatisUser = findViewById(R.id.btn_statistic_user);
        recyclerView = findViewById(R.id.list_user_order);
    }
}