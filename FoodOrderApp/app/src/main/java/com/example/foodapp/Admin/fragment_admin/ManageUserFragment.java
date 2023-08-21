package com.example.foodapp.Admin.fragment_admin;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Admin.AdapterAdmin.UserAdapter;
import com.example.foodapp.Admin.dialog.UserDialog;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.User;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class ManageUserFragment extends Fragment implements AdapterView.OnItemClickListener {
    RecyclerView recyclerView;
    UserDataSource userDataSource;
    UserAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manageuser, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_user);
        displayUser();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    public void displayUser() {
        userDataSource = new UserDataSource(getActivity());
        userDataSource.open();
        ArrayList<User> users = userDataSource.getAllUsers();
        userAdapter = new UserAdapter(getActivity(), R.layout.list_row_user, users);
        recyclerView.setAdapter(userAdapter);
    }

    public void openInsertUser() {
        UserDialog userDialog = new UserDialog();
        userDialog.show(getActivity().getSupportFragmentManager(), "insert user dialog");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        userDataSource.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Bạn đã click", Toast.LENGTH_SHORT ).show();
    }
}
