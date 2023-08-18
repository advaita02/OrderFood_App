package com.example.foodapp;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import com.example.foodapp.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.foodapp.Database.Adapter.PhotoAdapter;


import me.relex.circleindicator.CircleIndicator;

public class ContentActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //switch view khi dung navbar
        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.action_home:
                    break;
                case R.id.action_order:
                    break;
                case R.id.action_notify:
                    break;
                case R.id.action_profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });

//        ViewPager viewPager = findViewById(R.id.viewPager);
//        int[] imageIds = {R.drawable.qc1, R.drawable.qc2, R.drawable.qc3, R.drawable.qc4};
//        PhotoAdapter adapter = new PhotoAdapter(this, imageIds);
//        viewPager.setAdapter(adapter);
//        viewPager.setId(R.id.view_pager);

//        Button edittext_fragment_address;
//        ImageView imgAddress_iconDown;
//        ImageView imgAdress_iconLocation;
//
//        edittext_fragment_address = findViewById(R.id.edittext_fragment_address);
//        imgAdress_iconLocation = findViewById(R.id.icon_location);
//        imgAddress_iconDown = findViewById(R.id.address_icon_down);
//
//
//
//        edittext_fragment_address.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment_address = new AddressFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, fragment_address, null).commit();
////                BottomNavigationView bottomNavigationView = new BottomNavigationView();
////                bottomNavigationView.findViewById(R.id.bottom_nav).setVisibility(View.INVISIBLE);
//            }
//        });
////        imgAdress_iconLocation.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_address_pickup, new AddressFragment(), null).commit();
////            }
////        });
////        imgAddress_iconDown.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                FragmentManager fragmentManager = getSupportFragmentManager();
////                fragmentManager.beginTransaction()
////                        .replace(R.id.fragment_address_pickup,new AddressFragment(), null)
////                        .setReorderingAllowed(true)
////                        .addToBackStack("name") // Name can be null
////                        .commit();            }
////        });

    }
    public void FragmentAdd(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_address_pickup, new AddressFragment()).commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutMain,fragment);
        fragmentTransaction.commit();
    }
}
