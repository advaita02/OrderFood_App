package com.example.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.R;
import com.example.foodapp.fragment.dialog.CateDialog;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mContext;
    int singleData;
    ArrayList<Category> categories;
    CategoryDataSource categoryDataSource;
    private FragmentManager fragmentManager;

    public CategoryAdapter(Context mContext, int singleData, ArrayList<Category> categories, CategoryDataSource categoryDataSource) {
        this.mContext = mContext;
        this.singleData = singleData;
        this.categories = categories;
        this.categoryDataSource = categoryDataSource;
    }

    public CategoryAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_row_cate, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        final Category category = categories.get(position);
        byte[] img = category.getImg_cate();
        if (img != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.imageCate.setImageBitmap(bitmap);
            holder.txtNameCate.setText(category.getName());
        }

        // flow menu
        holder.flow_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.flow_menu);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_menu:
//                                 Sửa cate
//                                Bundle bundle = new Bundle();
//                                bundle.putInt("id", category.getId());
//                                bundle.putString("name", category.getName());
//                                bundle.putByteArray("img", category.getImg_cate());
//
//                                CateDialog cateDialog = CateDialog.newInstance(category.getId(), category.getName(), category.getImg_cate());
//                                cateDialog.setArguments(bundle); // Truyền dữ liệu vào hộp thoại
//                                fragmentManager = cateDialog.getFragmentManager();
//                                fragmentManager = cateDialog.requireFragmentManager();
//                                cateDialog.show(fragmentManager, "cate_dialog_tag");
                                break;
                            case R.id.delete_menu:
                                //xoa cate
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                //hien thi menu
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCate;
        TextView txtNameCate;
        ImageButton flow_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCate = (ImageView) itemView.findViewById(R.id.view_cate_img);
            txtNameCate = (TextView) itemView.findViewById(R.id.txtNameCate);
            flow_menu = (ImageButton) itemView.findViewById(R.id.flowmenu);
        }
    }
}
