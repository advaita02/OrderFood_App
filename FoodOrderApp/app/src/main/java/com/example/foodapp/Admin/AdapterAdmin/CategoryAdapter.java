package com.example.foodapp.Admin.AdapterAdmin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Constants;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.R;
import com.example.foodapp.Admin.dialog.CateDialog;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private int singleData;
    private ArrayList<Category> categories;
    private CategoryDataSource categoryDataSource;
    private Context context;


    public CategoryAdapter(Context context, int singleData, ArrayList<Category> categories, CategoryDataSource categoryDataSource) {
        this.context = context;
        this.singleData = singleData;
        this.categories = categories;
        this.categoryDataSource = categoryDataSource;
    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
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
                PopupMenu popupMenu = new PopupMenu(context, holder.flow_menu);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_menu:
                                Constants.isInsertCategory = false;
                                showCateDialog(category);
                                break;
                            case R.id.delete_menu:
                                categoryDataSource.open();
                                categoryDataSource.deleteCategory(category.getId());
                                Toast.makeText(context, "Đã xoá danh mục", Toast.LENGTH_SHORT).show();
                                categoryDataSource.close();
                                break;
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
    private void showCateDialog(Category category) {
        if (category != null) {
            CateDialog dialogFragment = CateDialog.newInstance(category);
            dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "dialog_fragment");
        }
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
