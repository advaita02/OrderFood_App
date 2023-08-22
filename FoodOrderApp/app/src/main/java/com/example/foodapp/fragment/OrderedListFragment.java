package com.example.foodapp.fragment;

import static com.example.foodapp.Database.MySQLiteHelper.TABLE_USER;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.foodapp.Adapter.OrderAdapter;
import com.example.foodapp.Database.DataSource.CategoryDataSource;
import com.example.foodapp.Database.DataSource.FoodDataSource;
import com.example.foodapp.Database.DataSource.OrderDataSource;
import com.example.foodapp.Database.DataSource.UserDataSource;
import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.Database.Entity.User;
import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentOrderedListBinding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class OrderedListFragment extends Fragment {
    RecyclerView recyclerView;
    OrderDataSource orderDataSource;
    OrderAdapter orderAdapter;
    Context context;

    UserDataSource userDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=container.getContext();
        userDataSource = new UserDataSource(context);
        return inflater.inflate(R.layout.fragment_ordered_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.orderList);
        try {
            displayOrders();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    public void displayOrders() throws ParseException {
        orderDataSource = new OrderDataSource(getActivity());
        orderDataSource.open();
        ArrayList<Order> orders = orderDataSource.getOrdersByUserId(userDataSource.getIdUser(LoginActivity.getPhoneNum()));
        orderAdapter = new OrderAdapter(getActivity(), R.layout.orderlist_item, orders, orderDataSource);
        recyclerView.setAdapter(orderAdapter);

    }


    private User getUser(int userId) {
        UserDataSource userDataSource = new UserDataSource(getActivity());
        userDataSource.open();
        User user = userDataSource.getUserById(userId);
        userDataSource.close();
        return user;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        orderDataSource.close();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_orderlist,fragment);
        fragmentTransaction.commit();
    }
//    public void fromCartToOrder(){
//        for(Food c : CartFragment.getFoods()){
//            orderDataSource.insertOrder(c.)
//        }
//    }

}