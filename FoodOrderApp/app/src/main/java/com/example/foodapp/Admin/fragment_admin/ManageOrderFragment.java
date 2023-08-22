package com.example.foodapp.Admin.fragment_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.Admin.StatisticActivity.ActivityStatisticDateOrder;
import com.example.foodapp.Admin.StatisticActivity.ActivityStatisticUser;
import com.example.foodapp.R;

public class ManageOrderFragment extends Fragment {

    Button user, date, bestselling;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manageorder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapping(view);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityStatisticUser.class));
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityStatisticDateOrder.class));
            }
        });
    }

    private void mapping(View view) {
        user = view.findViewById(R.id.btn_open_statistic_user);
        date = view.findViewById(R.id.btn_open_statistic_date);
        bestselling = view.findViewById(R.id.btn_open_best_selling);
    }
}
