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

import com.example.jayesh.ghostel.Activity.ViewBlockActivity;
import com.example.jayesh.ghostel.Model.BlockListData;
import com.example.jayesh.ghostel.Model.RoomListData;
import com.example.jayesh.ghostel.R;

import java.util.ArrayList;

/**
 * Created by jayesh on 19/2/18.
 */

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.MyViewHolder>
{
    private Context caller;
    private ArrayList<RoomListData> roomListData;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_roomid,tv_roomno,tv_hostelid,tv_hostelname,tv_blockid,tv_blockname,tv_capacity,tv_current_no;

        public MyViewHolder(View view) {
            super(view);

            tv_roomid = (TextView)view.findViewById(R.id.tv_roomid);
            tv_roomno = (TextView)view.findViewById(R.id.tv_roomno);
            tv_hostelid = (TextView)view.findViewById(R.id.tv_hostelid);
            tv_hostelname = (TextView)view.findViewById(R.id.tv_hostelname);
            tv_blockid = (TextView)view.findViewById(R.id.tv_blockid);
            tv_blockname = (TextView)view.findViewById(R.id.tv_blockname);
            tv_capacity = (TextView)view.findViewById(R.id.tv_capacity);
            tv_current_no = (TextView)view.findViewById(R.id.tv_current_no);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    final int pos=getLayoutPosition();
                    final CharSequence[] items = {"View", "Edit", "Delete"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setTitle(roomListData.get(pos).getRoomno());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item)
                            {
                                case 0:
                                    break;
                                case 1:
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
                    System.out.println("Click: "+ roomListData.get(pos).getBlockid());
                }
            });
        }
    }

    public RoomListAdapter(Context context, ArrayList<RoomListData> roomListData) {
        this.caller = context;
        this.roomListData = roomListData;
        Log.e("Data", roomListData.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_room_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomListAdapter.MyViewHolder holder, final int position)
    {
        holder.tv_roomid.setText(String.valueOf(roomListData.get(position).getRoomid()));
        holder.tv_roomno.setText(roomListData.get(position).getRoomno());
        holder.tv_hostelid.setText(String.valueOf(roomListData.get(position).getHostelid()));
        holder.tv_hostelname.setText(roomListData.get(position).getHostelname());
        holder.tv_blockid.setText(String.valueOf(roomListData.get(position).getBlockid()));
        holder.tv_blockname.setText(roomListData.get(position).getBlockname());
        holder.tv_capacity.setText(String.valueOf(roomListData.get(position).getCapacity()));
        holder.tv_current_no.setText(String.valueOf(roomListData.get(position).getCurrent_no()));
    }

    @Override
    public int getItemCount() {
        return roomListData.size();
    }
}
