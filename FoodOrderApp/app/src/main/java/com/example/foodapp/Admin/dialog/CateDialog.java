package com.example.foodapp.Admin.dialog;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.foodapp.Database.Constants;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CateDialog extends AppCompatDialogFragment {
    private EditText editNameCate;
    private ImageButton imgButton;
    private ImageView imgView;
    private Uri seletedImage;
    Bitmap imageTostore;
    CategoryDataSource categoryDataSource;
    private Category category;
    private int categoryId;
    private String categoryName;
    private byte[] categoryImg;


    public CateDialog() {
        // Required empty constructor
    }

    public static CateDialog newInstance(Category category) {
        CateDialog dialog = new CateDialog();
        Bundle args = new Bundle();
        args.putParcelable("category", category);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_dialog_insertcate, null);

        mapping(view);

        if (Constants.isInsertCategory == true) {
            builder.setView(view).setTitle("THÊM DANH MỤC")
                    .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            categoryDataSource = new CategoryDataSource(getActivity());
                            byte[] bytesIMGCate = convertFromDataToBytes();

                            categoryDataSource.createCategory(bytesIMGCate,
                                    editNameCate.getText().toString().trim());

                            Toast.makeText(getActivity(), "Đã thêm vào danh mục", Toast.LENGTH_SHORT).show();
                        }
                    });
            imgButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
                }
            });
            return builder.create();
        } else {
            Bundle args = getArguments();
            if(args != null) {
                category = args.getParcelable("category");
                updateCate();
            }
            builder.setView(view).setTitle("SỬA DANH MỤC")
                    .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            categoryDataSource = new CategoryDataSource(getActivity());
                            categoryDataSource.open();
                            categoryDataSource.updateCategory(categoryId, convertFromDataToBytes(), editNameCate.getText().toString().trim());
                            Toast.makeText(getActivity(), "Đã cập nhật danh mục", Toast.LENGTH_SHORT).show();
                            categoryDataSource.close();
                        }
                    });
            imgButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
                }
            });
            return builder.create();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        seletedImage = data.getData();
        if (requestCode == Constants.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(seletedImage);
                imageTostore = BitmapFactory.decodeStream(inputStream);
                imgView.setImageBitmap(imageTostore);
            } catch (FileNotFoundException e) {
                Toast.makeText(getActivity(), "Không thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateCate() {
        if (category != null) {
            categoryId = category.getId();
            categoryImg = category.getImg_cate();

            editNameCate.setText(category.getName());
            if (categoryImg != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(categoryImg, 0, categoryImg.length);
                imgView.setImageBitmap(bitmap);
            }
        }
    }

    public byte[] convertFromDataToBytes() {
        //chuyen data img -> bytes
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        return byteArray.toByteArray();
    }

    public void mapping(View view) {
        imgButton = (ImageButton) view.findViewById(R.id.img_button_folder);
        editNameCate = (EditText) view.findViewById(R.id.edit_catename);
        imgView = (ImageView) view.findViewById(R.id.img_cate);
    }
}
