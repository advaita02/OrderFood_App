package com.example.foodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.foodapp.ContentActivity;
import com.example.foodapp.R;
//import com.example.foodapp.databinding.FragmentFooddetailBinding;

import org.w3c.dom.Text;

public class FoodFragment extends Fragment {
    public FoodFragment () {

    }
//    private FragmentFooddetailBinding binding;
    View mView;
    ContentActivity mContentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        binding = FragmentFooddetailBinding.inflate(inflater, container, false);
//        mView = binding.getRoot();
        mContentActivity = (ContentActivity) getActivity();
//        ImageView img_food = binding.productImg;
//        TextView name_food = binding.productName;

        return mView;



    }
}
