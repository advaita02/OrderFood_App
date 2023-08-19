package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.ListContentFood;
import com.example.foodapp.R;

import java.util.List;

public class ListContentFoodAdapter extends RecyclerView.Adapter<ListContentFoodAdapter.ListContentFoodViewHolder>{
    private final Context mContext;
    private List<ListContentFood> mListContentFood;

    public ListContentFoodAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<ListContentFood> list) {
        this.mListContentFood = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListContentFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content_food,parent, false);

        return new ListContentFoodViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ListContentFoodViewHolder holder, int position) {
        ListContentFood listContentFood = mListContentFood.get(position);
        if (listContentFood == null) {
            return;
        }
        holder.name_item_food.setText(listContentFood.getName_food());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvItem_food.setLayoutManager(linearLayoutManager);

        FoodAdapter foodAdapter = new FoodAdapter();
        foodAdapter.setData(listContentFood.getFoods());
        holder.rcvItem_food.setAdapter(foodAdapter);
    }

    @Override
    public int getItemCount() {
        if (mListContentFood != null) {
            return mListContentFood.size();
        }
        return 0;
    }

    public class ListContentFoodViewHolder extends RecyclerView.ViewHolder {

        private TextView name_item_food;
        private RecyclerView rcvItem_food;

        public ListContentFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            name_item_food = (TextView) itemView.findViewById(R.id.tv_name_content_food);

            rcvItem_food = (RecyclerView) itemView.findViewById(R.id.rcv_content_food);
        }
    }
}
