package com.example.foodapp.Database.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context mContext;
    private int mResource;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Category> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        byte[] img = getItem(position).getImg_cate();
//        Category category = new Category(txtName)
////        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
////        convertView = layoutInflater.inflate(mResource, parent, false);
////
////        ImageView imageView = convertView.findViewById(R.id.image);
////        TextView txtName = convertView.findViewById(R.id.txtNameCate);
////
////        imageView.setImageResource(getItem(position).getImg_cate());
////        txtName.setText(getItem(position).getName());
////        return convertView;
//    }
}
