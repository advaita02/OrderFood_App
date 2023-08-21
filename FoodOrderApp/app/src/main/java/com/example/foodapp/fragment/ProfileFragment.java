package com.example.foodapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.ContentActivity;
import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    Context context;
    UserDataSource userDataSource;

    TextView logOutButton;
    TextView orderedList;

    EditText editTextNameUser;




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

        //dang xuat
        logOutButton = rootView.findViewById(R.id.txtLogOut);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        //edit cai nameuser
        editTextNameUser = rootView.findViewById(R.id.txtNameUser);
        editTextNameUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    String nameEdited = editTextNameUser.getText().toString();
                    editTextNameUser.setText(nameEdited);
                    userDataSource.editNameUser(LoginActivity.getPhoneNum(),nameEdited);

                }
                return true;
            }
        });
        //xem danh sach don hang da dat
        orderedList = rootView.findViewById(R.id.txtBill);
        orderedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new OrderedListFragment());
            }
        });

        return rootView;
    }

    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn muốn đăng xuất ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                Toast.makeText(context,"Bạn đã đăng xuất",Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_profile,fragment);
        fragmentTransaction.commit();
    }
    //set tag fragment
//    private void setTagFragment(){
//        ProfileFragment fragment = new ProfileFragment();
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_profile, fragment, "profile_fragment_tag");
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}