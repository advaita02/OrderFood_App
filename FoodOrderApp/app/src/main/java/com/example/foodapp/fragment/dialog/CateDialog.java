package com.example.foodapp.fragment.dialog;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.getIntent;

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

import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
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
    private final int REQUEST_CODE_FOLDER = 100;
    CategoryDataSource categoryDataSource;
    CategoryAdapter adapter;

    private int categoryId;
    private String categoryName;
    private byte[] categoryImg;

    public CateDialog() {
        // Required empty constructor
    }

    public static CateDialog newInstance(int id, String name, byte[] img) {
        CateDialog dialog = new CateDialog();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("name", name);
        args.putByteArray("img", img);
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


//        CategoryAdapter adapter = new CategoryAdapter(requireFragmentManager());
//        updateCate();

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

        mapping(view);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        return builder.create();
    }

    private void updateCate() {
        Bundle args = getArguments();
        if (args != null) {
            categoryId = args.getInt("id");
            categoryName = getArguments().getString("name");
            categoryImg = getArguments().getByteArray("img");

            editNameCate.setText(categoryName);
            if (categoryImg != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(categoryImg, 0, categoryImg.length);
                imgView.setImageBitmap(bitmap);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        seletedImage = data.getData();
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(seletedImage);
                imageTostore = BitmapFactory.decodeStream(inputStream);
                imgView.setImageBitmap(imageTostore);
            } catch (FileNotFoundException e) {
                Toast.makeText(getActivity(), "Không thành công", Toast.LENGTH_SHORT ).show();
            }
        }
    }

    public byte[] convertFromDataToBytes () {
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
