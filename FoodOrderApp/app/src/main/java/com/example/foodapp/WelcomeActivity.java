package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.foodapp.Admin.AdminLoginActivity;
import com.example.foodapp.Database.MySQLiteHelper;


public class WelcomeActivity extends AppCompatActivity {
//    MySQLiteHelper mySQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
//        mySQLiteHelper = new MySQLiteHelper(WelcomeActivity.this);
//        return null;
    }

    public void regis(View view) {
        startActivity(new Intent(WelcomeActivity.this,RegisActivity.class));
    }

    public void login1(View view) {
        startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
    }

    public void staff_login(View view) {
        startActivity(new Intent(WelcomeActivity.this, AdminLoginActivity.class));
    }
}