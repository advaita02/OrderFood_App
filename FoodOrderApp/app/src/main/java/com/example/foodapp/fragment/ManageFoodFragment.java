package com.example.foodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.fragment.dialog.InsertCateDialog;
import com.example.foodapp.fragment.dialog.InsertFoodDialog;

public class ManageFoodFragment extends Fragment {
    Button btnOpenDialogFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_managefood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnOpenDialogFood = (Button) view.findViewById(R.id.btnDialogInsertFood);
        btnOpenDialogFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogInsert();
            }
        });
    }
    public void openDialogInsert() {
        InsertFoodDialog insertFoodDialog = new InsertFoodDialog();
        insertFoodDialog.show(getActivity().getSupportFragmentManager(), "insert food dialog");
    }
}
