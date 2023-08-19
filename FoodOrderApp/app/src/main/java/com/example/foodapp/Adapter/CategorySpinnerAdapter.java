package com.example.foodapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.List;

public class CategorySpinnerAdapter extends ArrayAdapter<Category> {
    private ArrayList<Category> categories;
    private Context mContext;

    public CategorySpinnerAdapter(Context context, ArrayList<Category> categoryList) {
        super(context, 0, categoryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_row_cate, parent, false
            );
        }

        ImageView imageView = convertView.findViewById(R.id.view_cate_img);
        TextView txtName = convertView.findViewById(R.id.txtNameCate);
        Category currentCate = getItem(position);

        // Chuyển đổi byte[] thành Bitmap
        if(currentCate != null) {
            byte[] imageData = currentCate.getImg_cate(); // Đây là dữ liệu hình ảnh dạng byte[]
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            imageView.setImageBitmap(bitmap);
            txtName.setText(currentCate.getName());
        }
        return convertView;
    }
}
