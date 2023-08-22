package com.example.foodapp.Admin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.Database.Entity.User;
import com.example.foodapp.R;

public class UserDialog extends AppCompatDialogFragment {
    private EditText nameUser, numberPhoneUser, passWord;
    private UserDataSource userDataSource;
    private User user;

    public static UserDialog newInstance(User user) {
        UserDialog userDialog = new UserDialog();
        Bundle args = new Bundle();
        args.putParcelable("user_key", user);
        userDialog.setArguments(args);
        return userDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_dialog_user, null);

        mapping(view);
        //lay gia tri user tu bundle
        Bundle args = getArguments();
        if(args != null) {
            user = args.getParcelable("user_key");
            if(user != null) {
                nameUser.setText(user.getName());
                numberPhoneUser.setText(String.valueOf(user.getPn()));
                passWord.setText(user.getPw());
            }
        }

        builder.setView(view).setTitle("SỬA THÔNG TIN NGƯỜI DÙNG")
                .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDataSource = new UserDataSource(getActivity());
                        userDataSource.open();
                        userDataSource.updateUser(
                                nameUser.getText().toString().trim(),
                                Integer.parseInt(numberPhoneUser.getText().toString()),
                                passWord.getText().toString().trim()
                        );
                        Toast.makeText(getActivity(), "Đã sửa user", Toast.LENGTH_SHORT).show();
                        userDataSource.close();
                    }
                });
        return builder.create();
    }

    public void mapping(View view) {
        nameUser = (EditText) view.findViewById(R.id.edit_name_user);
        numberPhoneUser = view.findViewById(R.id.edit_number_phone_user);
        passWord = view.findViewById(R.id.edit_password_user);
    }
}
