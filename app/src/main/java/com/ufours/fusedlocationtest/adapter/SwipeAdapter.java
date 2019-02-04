package com.ufours.fusedlocationtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ufours.fusedlocationtest.R;
import com.ufours.fusedlocationtest.model.ItemModel;

import java.util.ArrayList;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder> {
    private ArrayList<ItemModel> dataList;
    private Context context;

    public SwipeAdapter(Context context, ArrayList<ItemModel> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public SwipeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_swipe, viewGroup, false);
        return new SwipeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SwipeAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.Tv_name.setText(dataList.get(i).getFavourite_title_name());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void removeItem(int position) {
        dataList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(ItemModel item, int position) {
        dataList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Tv_name;
        public RelativeLayout viewBackground, viewForeground;
        public ViewHolder(View view) {
            super(view);
            Tv_name = (TextView) view.findViewById(R.id.Tv_name);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }

}