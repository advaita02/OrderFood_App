package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.Database.Entity.User;
import com.example.foodapp.databinding.ActivityRegisBinding;

public class RegisActivity extends AppCompatActivity {

    ActivityRegisBinding binding;
    UserDataSource userDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userDataSource = new UserDataSource(this);

        binding.btnRegis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = binding.txtName.getText().toString();
                String pw = binding.txtPW.getText().toString();
                String pwCom = binding.txtPWComfirm.getText().toString();
                String stringPn = binding.txtPN.getText().toString();

                if (name.equals("") || stringPn.isEmpty() || pw.equals("") || pwCom.equals("")) {
                    Toast.makeText(RegisActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG).show();
                } else if (userDataSource.phoneFormatCheck(stringPn)==false) {
                    Toast.makeText(RegisActivity.this, "Số điện thoại nhập sai định dạng", Toast.LENGTH_LONG).show();
                } else {
                    Integer pn = Integer.parseInt(stringPn);
                    if (pw.equals(pwCom)) {
                        Boolean checkPhoneNum = userDataSource.checkPN(pn);
                        if (checkPhoneNum == false) {
                            User newUser = userDataSource.insertUser(name, pn, pw);
                            Toast.makeText(RegisActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisActivity.this, LoginActivity.class));

                        } else
                            Toast.makeText(RegisActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(RegisActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                }
            }
        });
//        return null;
    }

    public void login(View view) {
        startActivity(new Intent(RegisActivity.this, LoginActivity.class));
    }
}