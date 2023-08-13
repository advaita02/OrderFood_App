package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.Admin.AdminActivity;
import com.example.foodapp.Admin.AdminLoginActivity;
import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding bindingActivity;
    UserDataSource userDataSource ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingActivity = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bindingActivity.getRoot());

//        Button button = findViewById(R.id.btnLogin);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        userDataSource = new UserDataSource(this);
        bindingActivity.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                startActivity(intent);

//                Integer pn = Integer.parseInt(bindingActivity.txtPN.getText().toString());
//                String pw = bindingActivity.txtPW.getText().toString();
//
//                if(userDataSource.checkPNPW(pn,pw)==true){
//                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
//
//                }
//                else
//                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_LONG).show();
//
            }
        });

    }
}