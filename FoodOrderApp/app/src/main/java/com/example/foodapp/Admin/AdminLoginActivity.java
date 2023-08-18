package com.example.foodapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityLoginadminBinding;

public class AdminLoginActivity extends AppCompatActivity {
    ActivityLoginadminBinding loginadminBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginadminBinding = ActivityLoginadminBinding.inflate(getLayoutInflater());
        setContentView(loginadminBinding.getRoot());

        Button button = findViewById(R.id.btnLoginAdmin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
//        return null;
    }


//    public void dangNhapAdmin() {
//        startActivity(new Intent(AdminLoginActivity.this, AdminActivity.class));
//    }
}