package com.example.foodapp.fragment.dialog;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Adapter.CategorySpinnerAdapter;
import com.example.foodapp.Database.Constants;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.MySQLiteHelper;
import com.example.foodapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class InsertFoodDialog extends AppCompatDialogFragment {
    private EditText editTextName, editTextDescription, price;
    private Spinner spinnerCate;
    private ImageButton imgButton;
    private ImageView imgView, imgSpinner;
    private TextView textNameCateSpinner;
    private Uri seletedImage;
    Bitmap imageTostore;
    FoodDataSource foodDataSource;
    CategoryDataSource categoryDataSource;
    CategorySpinnerAdapter spinnerAdapter;


    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_dialog_insertfood, null);

        builder.setView(view).setTitle("THÊM THỨC ĂN")
                .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foodDataSource = new FoodDataSource(getActivity());
                        byte[] bytesIMGFood = convertFromDataToBytes();
                        Category category = (Category) spinnerCate.getSelectedItem();
                        foodDataSource.open();
                        foodDataSource.insertFood(editTextName.getText().toString().trim(),
                                Integer.parseInt(price.getText().toString()),
                                editTextDescription.getText().toString().trim(),
                                1,
                                bytesIMGFood, category.getId()
                        );
                        Toast.makeText(getActivity(), "Đã thêm food", Toast.LENGTH_SHORT).show();
                        foodDataSource.close();
                    }
                });
        mapping(view);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Constants.REQUEST_CODE_FOLDER);
            }
        });
        spinnerCate = (Spinner) view.findViewById(R.id.spinner_cate);
        displayCates();
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        seletedImage = data.getData();
        if(requestCode == Constants.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
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
    public void displayCates() {
        categoryDataSource = new CategoryDataSource(getActivity());
        Cursor cursor = categoryDataSource.getAllCategories();
        ArrayList<Category> listCategory = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            byte[] img = cursor.getBlob(1);
            String nameCate = cursor.getString(2);
            listCategory.add(new Category(id, nameCate, img));
            cursor.moveToNext();
        }
        cursor.close();
        spinnerAdapter = new CategorySpinnerAdapter(getActivity(), listCategory);
        spinnerCate.setAdapter(spinnerAdapter);
        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category clicked = (Category) parent.getItemAtPosition(position);
                String clickedName = clicked.getName();
                Toast.makeText(getActivity(), clickedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void mapping(View view) {
        imgButton = (ImageButton) view.findViewById(R.id.img_button_folder_food);
        editTextName = (EditText) view.findViewById(R.id.edit_food_name);
        price = (EditText) view.findViewById(R.id.edit_price);
        editTextDescription = (EditText) view.findViewById(R.id.edit_describe);
        imgView = (ImageView) view.findViewById(R.id.img_food);
        imgSpinner = (ImageView) view.findViewById(R.id.view_cate_img_spinner);
        textNameCateSpinner = (TextView) view.findViewById(R.id.txtNameCate_spinner);
    }

}
