package com.example.foodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.Database.Adapter.CategoryAdapter;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.R;
import com.example.foodapp.fragment.dialog.InsertCateDialog;

import java.util.ArrayList;
import java.util.TreeSet;

public class ManageCateFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;
    Button buttonOpenDialogInsert;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_managecate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Category> listCategory = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listCate);
        buttonOpenDialogInsert = (Button) view.findViewById(R.id.btnOpenDialogInsert);
        buttonOpenDialogInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogInsert();
            }
        });

//        listCategory.add(new Category("Bánh mì/Sandwich", R.drawable.banhmi));
//        listCategory.add(new Category("Bánh mì/Sandwich", R.drawable.banhmi));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), R.layout.list_row, listCategory);
        listView.setAdapter(categoryAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Bạn đã click", Toast.LENGTH_SHORT ).show();
    }

    public void openDialogInsert() {
        InsertCateDialog insertCateDialog = new InsertCateDialog();
        insertCateDialog.show(getActivity().getSupportFragmentManager(), "insert cate dialog");
    }

}
