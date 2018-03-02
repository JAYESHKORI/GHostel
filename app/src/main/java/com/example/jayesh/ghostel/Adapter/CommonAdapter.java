package com.example.jayesh.ghostel.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jayesh.ghostel.Model.BlockListData;
import com.example.jayesh.ghostel.Model.CommonData;
import com.example.jayesh.ghostel.R;

import java.util.ArrayList;

/**
 * Created by jayesh on 19/2/18.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.MyViewHolder>
{
    private Context caller;
    private ArrayList<CommonData> commonData;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_id,tv_fname,tv_lname,tv_hid,tv_hname,tv_bid,tv_bname;

        public MyViewHolder(View view) {
            super(view);


            tv_id = (TextView)view.findViewById(R.id.tv_common_id);
            tv_fname = (TextView)view.findViewById(R.id.tv_common_fname);
            tv_lname = (TextView)view.findViewById(R.id.tv_common_lname);
            tv_hid = (TextView)view.findViewById(R.id.tv_common_hid);
            tv_hname = (TextView)view.findViewById(R.id.tv_common_hname);
            tv_bid = (TextView)view.findViewById(R.id.tv_common_bid);
            tv_bname = (TextView)view.findViewById(R.id.tv_common_bname);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    }

    public CommonAdapter(Context context, ArrayList<CommonData> commondata) {
        this.caller = context;
        this.commonData = commondata;
        Log.e("Data",commonData.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_common_all, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommonAdapter.MyViewHolder holder, final int position)
    {
        holder.tv_id.setText(String.valueOf(commonData.get(position).getId()));
        holder.tv_fname.setText(commonData.get(position).getFname());
        holder.tv_lname.setText(commonData.get(position).getLname());
        holder.tv_hid.setText(String.valueOf(commonData.get(position).getHid()));
        holder.tv_hname.setText(commonData.get(position).getHname());
        holder.tv_bid.setText(String.valueOf(commonData.get(position).getBid()));
        holder.tv_bname.setText(commonData.get(position).getBname());
    }

    @Override
    public int getItemCount() {
        return commonData.size();
    }
}
