package com.example.foodapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityAdminBinding;
import com.example.foodapp.fragment.ManageCateFragment;
import com.example.foodapp.fragment.ManageFoodFragment;
import com.example.foodapp.fragment.ManageOrderFragment;
import com.example.foodapp.fragment.ManageUserFragment;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_FOOD = 0;
    private static final int FRAGMENT_CATE = 1;
    private static final int FRAGMENT_ORDER = 2;
    private static final int FRAGMENT_USER = 3;

    private int mCurrentFrag = FRAGMENT_FOOD;
    private DrawerLayout mDrawerLayout;

    ActivityAdminBinding activityAdminBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //chinh sua cac thu
        super.onCreate(savedInstanceState);
//        activityAdminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new ManageFoodFragment());
        navigationView.getMenu().findItem(R.id.nav_food).setChecked(true);
//        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_food) {
            if(mCurrentFrag != FRAGMENT_FOOD) {
                replaceFragment(new ManageFoodFragment());
                mCurrentFrag = FRAGMENT_FOOD;
            }
        } else if (id == R.id.nav_category) {
            if(mCurrentFrag != FRAGMENT_CATE) {
                replaceFragment(new ManageCateFragment());
                mCurrentFrag = FRAGMENT_CATE;
            }
        } else if (id == R.id.nav_order) {
            if(mCurrentFrag != FRAGMENT_ORDER) {
                replaceFragment(new ManageOrderFragment());
                mCurrentFrag = FRAGMENT_ORDER;
            }
        } else if (id == R.id.nav_user) {
            if(mCurrentFrag != FRAGMENT_USER) {
                replaceFragment(new ManageUserFragment());
                mCurrentFrag = FRAGMENT_USER;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}