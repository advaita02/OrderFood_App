package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListContentCateAdapter extends RecyclerView.Adapter<ListContentCateAdapter.ListContentViewHolder>{

    private Context mContext;
    private List<ListContentCate> mListContent;

    public ListContentCateAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<ListContentCate> list) {
        this.mListContent = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content_cate,parent, false);

        return new ListContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListContentViewHolder holder, int position) {
        ListContentCate listContent = mListContent.get(position);
        if (listContent == null) {
            return;
        }
        holder.name_item_content.setText(listContent.getName_content());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvItem.setLayoutManager(linearLayoutManager);

        CateAdapter cateAdapter = new CateAdapter();
        cateAdapter.setData(listContent.getCates());
        holder.rcvItem.setAdapter(cateAdapter);
    }

    @Override
    public int getItemCount() {
        if (mListContent != null) {
            return mListContent.size();
        }
        return 0;
    }

    public class ListContentViewHolder extends RecyclerView.ViewHolder {

        private TextView name_item_content;
        private RecyclerView rcvItem;

        public ListContentViewHolder(@NonNull View itemView) {
            super(itemView);

            name_item_content = itemView.findViewById(R.id.tv_name_content);
            rcvItem = itemView.findViewById(R.id.rcv_content);
        }
    }
}
