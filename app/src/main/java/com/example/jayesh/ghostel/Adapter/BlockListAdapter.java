package com.example.jayesh.ghostel.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jayesh.ghostel.Activity.EditBlockActivity;
import com.example.jayesh.ghostel.Activity.ViewBlockActivity;
import com.example.jayesh.ghostel.Activity.ViewHostelActivity;
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_ad_blockid,tv_ad_bname,tv_ad_hostelid,tv_ad_hname,tv_ad_type,tv_ad_capacity;

        public MyViewHolder(View view) {
            super(view);

            tv_ad_blockid = (TextView)view.findViewById(R.id.tv_ad_blockid);
            tv_ad_bname = (TextView)view.findViewById(R.id.tv_ad_bname);
            tv_ad_hostelid = (TextView)view.findViewById(R.id.tv_ad_hostelid);
            tv_ad_hname = (TextView)view.findViewById(R.id.tv_ad_hname);
            tv_ad_type = (TextView)view.findViewById(R.id.tv_ad_type);
            tv_ad_capacity = (TextView)view.findViewById(R.id.tv_ad_capacity);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    final int pos=getLayoutPosition();
                    final CharSequence[] items = {"View", "Edit", "Delete"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setTitle(blockListData.get(pos).getBname());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item)
                            {
                                case 0:
                                    view.getContext().startActivity(new Intent(view.getContext(),ViewBlockActivity.class)
                                            .putExtra("blockid",blockListData.get(pos).getBlockid()));
                                    break;
                                case 1:
                                    Intent intent = new Intent(view.getContext(), EditBlockActivity.class);
                                    intent.putExtra("blockid", blockListData.get(pos).getBlockid());
                                    intent.putExtra("blockname", blockListData.get(pos).getBname());
                                    intent.putExtra("capacity", blockListData.get(pos).getCapacity());
                                    intent.putExtra("type", blockListData.get(pos).getType());
                                    view.getContext().startActivity(intent);
                                    break;
                                case 2:
                                    break;
                            }
                        }
                    });
                    builder.show();
                    return true;
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getLayoutPosition();
                    System.out.println("Click: "+blockListData.get(pos).getBlockid());
                }
            });
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
