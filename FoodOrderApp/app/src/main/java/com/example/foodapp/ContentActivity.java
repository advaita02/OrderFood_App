package com.example.foodapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;

public class ContentActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        ViewPager viewPager = findViewById(R.id.viewPager);
        int[] imageIds = {R.drawable.qc1, R.drawable.qc2, R.drawable.qc3, R.drawable.qc4};
        PhotoAdapter adapter = new PhotoAdapter(this, imageIds);
        viewPager.setAdapter(adapter);
//        viewPager.setId(R.id.view_pager);

        Button edittext_fragment_address;
        ImageView imgAddress_iconDown;
        ImageView imgAdress_iconLocation;

        edittext_fragment_address = findViewById(R.id.edittext_fragment_address);
        imgAdress_iconLocation = findViewById(R.id.icon_location);
        imgAddress_iconDown = findViewById(R.id.address_icon_down);



        edittext_fragment_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_address = new AddressFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, fragment_address, null).commit();
//                BottomNavigationView bottomNavigationView = new BottomNavigationView();
//                bottomNavigationView.findViewById(R.id.bottom_nav).setVisibility(View.INVISIBLE);
            }
        });
//        imgAdress_iconLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, new AddressFragment(), null).commit();
//            }
//        });
//        imgAddress_iconDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_address_pickup,new AddressFragment(), null)
//                        .setReorderingAllowed(true)
//                        .addToBackStack("name") // Name can be null
//                        .commit();            }
//        });

        }
    public void FragmentAdd(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_address_pickup, new AddressFragment()).commit();
    }


}