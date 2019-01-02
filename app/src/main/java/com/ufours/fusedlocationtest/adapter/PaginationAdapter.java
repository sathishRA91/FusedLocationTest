package com.ufours.fusedlocationtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.ufours.fusedlocationtest.R;
import com.ufours.fusedlocationtest.model.DataModel;

import java.util.ArrayList;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.ViewHolder> {
    private ArrayList<DataModel> dataList;
    private Context context;

    public PaginationAdapter(Context context, ArrayList<DataModel> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public PaginationAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_paggination, viewGroup, false);
        return new PaginationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaginationAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.Tv_name.setText(dataList.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Tv_name;

        public ViewHolder(View view) {
            super(view);
            Tv_name = (TextView) view.findViewById(R.id.Tv_name);
        }
    }

}