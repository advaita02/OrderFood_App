package com.example.foodapp.Admin.StatisticActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Admin.AdapterAdmin.OrderStatisticAdapter;
import com.example.foodapp.Database.DataSource.OrderDataSource;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityStatisticDateOrder extends AppCompatActivity {
    private Button btnFromDate, btnToDate, statisDateOrder;
    private TextView txtFromDate, txtToDate;
    private DatePickerDialog.OnDateSetListener onFromDateSetListener, onToDateSetListener;
    private OrderDataSource orderDataSource;
    private OrderStatisticAdapter orderAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_date_order);

        mapping();

        btnFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        ActivityStatisticDateOrder.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onFromDateSetListener,
                        year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onFromDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("ActivityStatisticDateOrder", "onDateSet: dd/mm/yyyy: " + dayOfMonth + month + year);
                String fromDate = dayOfMonth + "-" + month + "-" + year;
                txtFromDate.setText(fromDate);
            }
        };

        btnToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        ActivityStatisticDateOrder.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onToDateSetListener,
                        year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onToDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("ActivityStatisticDateOrder", "onDateSet: dd/mm/yyyy: " + dayOfMonth + month + year);
                String toDate = dayOfMonth + "-" + month + "-" + year;
                txtToDate.setText(toDate);
            }
        };
        statisDateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showOrderDate();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void showOrderDate() throws ParseException {
        orderDataSource = new OrderDataSource(this);
        String fromDate = txtFromDate.getText().toString();
        String toDate = txtToDate.getText().toString();

        if (checkDate(fromDate, toDate)) {
            ArrayList<Order> listUserOrder = orderDataSource.getOrdersByDateRange(fromDate, toDate);
            orderAdapter = new OrderStatisticAdapter(this,
                    R.layout.order_user_statistic_item, listUserOrder, orderDataSource);
            recyclerView.setAdapter(orderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(this , "Ngày bắt đầu phải trước ngày kết thúc", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkDate(String fromDate, String toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date1 = sdf.parse(fromDate);
            Date date2 = sdf.parse(toDate);

            if (date1.compareTo(date2) > 0) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void mapping() {
        btnFromDate = findViewById(R.id.btn_input_from_date);
        btnToDate = findViewById(R.id.btn_input_to_date);
        txtFromDate = findViewById(R.id.txt_from_date);
        txtToDate = findViewById(R.id.txt_to_date);
        statisDateOrder = findViewById(R.id.btn_statistic_date_order);
        recyclerView = findViewById(R.id.list_order_date_statistic);
    }
}