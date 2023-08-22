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

import com.example.foodapp.Admin.AdapterAdmin.CategoryAdapter;
import com.example.foodapp.Admin.dialog.CateDialog;
import com.example.foodapp.Constants;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.R;

import java.util.ArrayList;

public class ManageCateFragment extends Fragment implements AdapterView.OnItemClickListener {
    RecyclerView recyclerView;
    CategoryDataSource categoryDataSource;
    CategoryAdapter categoryAdapter;
    Button buttonOpenDialogInsert;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_managecate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonOpenDialogInsert = (Button) view.findViewById(R.id.btnOpenDialogInsert);
        buttonOpenDialogInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.isInsertCategory = true;
                openDialogInsert();
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.listView);
        displayCates();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Bạn đã click", Toast.LENGTH_SHORT ).show();
    }

    public void openDialogInsert() {
        CateDialog cateDialog = new CateDialog();
        cateDialog.show(getActivity().getSupportFragmentManager(), "insert cate dialog");
    }

    public void displayCates() {
        categoryDataSource = new CategoryDataSource(getActivity());
        Cursor cursor = categoryDataSource.getAllCategories();
        ArrayList<Category> listCategory = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            byte[] img = cursor.getBlob(1);
            String nameCate = cursor.getString(2);
            listCategory.add(new Category(id, nameCate, img));
            cursor.moveToNext();
        }
        cursor.close();
        categoryAdapter = new CategoryAdapter(getActivity(), R.layout.list_row_cate, listCategory, categoryDataSource);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryDataSource.close();
    }
}
