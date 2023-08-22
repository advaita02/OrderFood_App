package com.example.foodapp.Admin.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Admin.dialog.UserDialog;
import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.Database.Entity.User;
import com.example.foodapp.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private int singleData;
    private ArrayList<User> users;
    private Context context;


    public UserAdapter(Context context, int singleData, ArrayList<User> users) {
        this.context = context;
        this.singleData = singleData;
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row_user, null);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        final User user = users.get(position);
        holder.txtNameUser.setText(user.getName());
        holder.txtNumberPhone.setText(Integer.toString(user.getPn()));
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
                                UserDialog userDialog = UserDialog.newInstance(user);
                                userDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "dialog_fragment");
                                break;
                            case R.id.delete_menu:
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

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameUser, txtNumberPhone;
        ImageButton flow_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameUser = (TextView) itemView.findViewById(R.id.txt_name_user);
            txtNumberPhone = (TextView) itemView.findViewById(R.id.txt_number_phone);
            flow_menu = (ImageButton) itemView.findViewById(R.id.flowmenu);
        }
    }
}
