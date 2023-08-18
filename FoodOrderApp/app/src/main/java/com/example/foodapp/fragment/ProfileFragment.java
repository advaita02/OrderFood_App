package com.example.foodapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    Context context;
    UserDataSource userDataSource;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = container.getContext();
        userDataSource = new UserDataSource(context);
        final View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        TextView nameUser = (TextView) rootView.findViewById(R.id.txtNameUser);
        TextView phoneNum = (TextView) rootView.findViewById(R.id.textPhoneNum);
        phoneNum.setText("+84"+LoginActivity.getPhoneNum());
        userDataSource.open();
        nameUser.setText(userDataSource.loginInfo(LoginActivity.getPhoneNum()));
        userDataSource.close();

        return rootView;
    }
//    private void logout(ProfileFragment profileFragment){
//        AlertDialog.Builder builder = new AlertDialog.Builder(profileFragment.context);
//        builder.setTitle("Đăng xuất");
//        builder.setMessage("Bạn muốn đăng xuất ?");
//        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(getActivity(),LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//        builder.show();
//    }

}