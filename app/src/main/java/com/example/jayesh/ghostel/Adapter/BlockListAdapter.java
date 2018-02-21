package com.example.jayesh.ghostel.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jayesh.ghostel.Model.BlockListData;
import com.example.jayesh.ghostel.R;

import java.util.ArrayList;

/**
 * Created by jayesh on 19/2/18.
 */

public class BlockListAdapter extends RecyclerView.Adapter<BlockListAdapter.MyViewHolder>
{
    private Context caller;
    private ArrayList<BlockListData> blockListData;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_ad_blockid,tv_ad_bname,tv_ad_hostelid,tv_ad_hname,tv_ad_type,tv_ad_capacity;

        public MyViewHolder(View view) {
            super(view);

            tv_ad_blockid = (TextView)view.findViewById(R.id.tv_ad_blockid);
            tv_ad_bname = (TextView)view.findViewById(R.id.tv_ad_bname);
            tv_ad_hostelid = (TextView)view.findViewById(R.id.tv_ad_hostelid);
            tv_ad_hname = (TextView)view.findViewById(R.id.tv_ad_hname);
            tv_ad_type = (TextView)view.findViewById(R.id.tv_ad_type);
            tv_ad_capacity = (TextView)view.findViewById(R.id.tv_ad_capacity);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    }

    public BlockListAdapter(Context context, ArrayList<BlockListData> blockListData) {
        this.caller = context;
        this.blockListData = blockListData;
        Log.e("Data",blockListData.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_block_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BlockListAdapter.MyViewHolder holder, final int position)
    {
        holder.tv_ad_blockid.setText(String.valueOf(blockListData.get(position).getBlockid()));
        holder.tv_ad_bname.setText(blockListData.get(position).getBname());
        holder.tv_ad_hostelid.setText(String.valueOf(blockListData.get(position).getHostelid()));
        holder.tv_ad_hname.setText(blockListData.get(position).gethName());
        holder.tv_ad_type.setText(blockListData.get(position).getType());
        holder.tv_ad_capacity.setText(String.valueOf(blockListData.get(position).getCapacity()));
    }

    @Override
    public int getItemCount() {
        return blockListData.size();
    }
}
