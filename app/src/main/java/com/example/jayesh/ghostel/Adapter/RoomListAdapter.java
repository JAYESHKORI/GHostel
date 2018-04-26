package com.example.jayesh.ghostel.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.Activity.ViewBlockActivity;
import com.example.jayesh.ghostel.Activity.ViewRectorActivity;
import com.example.jayesh.ghostel.Activity.ViewRoomActivity;
import com.example.jayesh.ghostel.Model.BlockListData;
import com.example.jayesh.ghostel.Model.RoomListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                    final CharSequence[] items = { "View","Delete"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setTitle(roomListData.get(pos).getRoomno());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item)
                            {
                                case 0:
                                    Intent intent = new Intent(view.getContext(), ViewRoomActivity.class);
                                    intent.putExtra("roomid",roomListData.get(pos).getRoomid());
                                    intent.putExtra("roomno",roomListData.get(pos).getRoomno());
                                    intent.putExtra("capacity",String.valueOf(roomListData.get(pos).getCapacity()));
                                    intent.putExtra("current_no",String.valueOf(roomListData.get(pos).getCurrent_no()));
                                    view.getContext().startActivity(intent);
                                    break;
                                case 1:
                                    showwarning("Warning!","Do you really want to delete this record permenantly?",roomListData.get(pos).getRoomid());
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
                    Intent intent = new Intent(view.getContext(), ViewRoomActivity.class);
                    intent.putExtra("roomid",roomListData.get(pos).getRoomid());
                    intent.putExtra("roomno",roomListData.get(pos).getRoomno());
                    intent.putExtra("capacity",String.valueOf(roomListData.get(pos).getCapacity()));
                    intent.putExtra("current_no",String.valueOf(roomListData.get(pos).getCurrent_no()));
                    view.getContext().startActivity(intent);
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
        holder.tv_hostelname.setText("Hostel : "+roomListData.get(position).getHostelname());
        holder.tv_blockid.setText(String.valueOf(roomListData.get(position).getBlockid()));
        holder.tv_blockname.setText("Block : "+roomListData.get(position).getBlockname());
        holder.tv_capacity.setText("Capacity : "+String.valueOf(roomListData.get(position).getCapacity()));
        holder.tv_current_no.setText("Currently Living : "+String.valueOf(roomListData.get(position).getCurrent_no()));
    }

    @Override
    public int getItemCount() {
        return roomListData.size();
    }

    private void showwarning(String title, String msg, final int roomid)
    {
        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(caller).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteRoom(roomid);
            }
        });
        alertDialog.show();
    }

    private void deleteRoom(final int roomid) {
        final ProgressDialog progressDialog = new ProgressDialog(caller);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_DELROOM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        progressDialog.dismiss();
                        Toast.makeText(caller,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.e("Error",error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("roomid",String.valueOf(roomid));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(caller);
        requestQueue.add(stringRequest);
    }
}
