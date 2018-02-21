package com.example.jayesh.ghostel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jayesh.ghostel.Model.HostelListData;
import com.example.jayesh.ghostel.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jayesh on 13/2/18.
 */

public class HostelListAdapter extends RecyclerView.Adapter<HostelListAdapter.MyViewHolder> {
    private Context caller;
    private ArrayList<HostelListData> hostelListData;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_ad_id,tv_ad_name,tv_ad_type,tv_ad_description;

        public MyViewHolder(View view) {
            super(view);

            tv_ad_id = (TextView)view.findViewById(R.id.tv_ad_id);
            tv_ad_name = (TextView)view.findViewById(R.id.tv_ad_name);
            tv_ad_type = (TextView)view.findViewById(R.id.tv_ad_type);
            tv_ad_description = (TextView)view.findViewById(R.id.tv_ad_description);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    }

    public HostelListAdapter(Context context, ArrayList<HostelListData> hostelListData) {
        this.caller = context;
        this.hostelListData = hostelListData;
        Log.e("Data",hostelListData.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_hostel_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_ad_id.setText(String.valueOf(hostelListData.get(position).getId()));
        holder.tv_ad_name.setText(hostelListData.get(position).getName());
        holder.tv_ad_type.setText(hostelListData.get(position).getType());
        holder.tv_ad_description.setText(hostelListData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return hostelListData.size();
    }
}


