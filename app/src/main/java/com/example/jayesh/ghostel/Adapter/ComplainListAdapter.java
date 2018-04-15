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
import com.example.jayesh.ghostel.Activity.EditStudentActivity;
import com.example.jayesh.ghostel.Activity.ViewComplainActivity;
import com.example.jayesh.ghostel.Activity.ViewStudentActivity;
import com.example.jayesh.ghostel.Model.CommonData;
import com.example.jayesh.ghostel.Model.ComplainListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayesh on 19/2/18.
 */

public class ComplainListAdapter extends RecyclerView.Adapter<ComplainListAdapter.MyViewHolder>
{
    private Context caller;
    private ArrayList<ComplainListData> complainListData;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_complainid,tv_title,tv_detail,tv_hostelid,tv_hostelname,tv_blockid,tv_blockname,tv_roomid,
                tv_roomno,tv_studentid,tv_studentname,tv_status;

        public MyViewHolder(View view) {
            super(view);


            tv_complainid = (TextView)view.findViewById(R.id.tv_complainid);
            tv_title = (TextView)view.findViewById(R.id.tv_title);
            tv_detail = (TextView)view.findViewById(R.id.tv_detail);
            tv_hostelid = (TextView)view.findViewById(R.id.tv_hostelid);
            tv_hostelname = (TextView)view.findViewById(R.id.tv_hostelname);
            tv_blockid = (TextView)view.findViewById(R.id.tv_blockid);
            tv_blockname = (TextView)view.findViewById(R.id.tv_blockname);
            tv_roomid = (TextView)view.findViewById(R.id.tv_roomid);
            tv_roomno = (TextView)view.findViewById(R.id.tv_roomno);
            tv_studentid = (TextView)view.findViewById(R.id.tv_studentid);
            tv_studentname = (TextView)view.findViewById(R.id.tv_studentname);
            tv_status = (TextView)view.findViewById(R.id.tv_status);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    final int pos=getLayoutPosition();
                    final CharSequence[] items = {"View"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setTitle(complainListData.get(pos).getTitle());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item)
                            {
                                case 0:
                                    view.getContext().startActivity(new Intent(view.getContext(),ViewComplainActivity.class)
                                            .putExtra("complainid",complainListData.get(pos).getComplainid()));

                                    Intent intent = new Intent(view.getContext(), ViewComplainActivity.class);
                                    intent.putExtra("complainid",String.valueOf(complainListData.get(pos).getComplainid()));
                                    intent.putExtra("title",complainListData.get(pos).getTitle());
                                    intent.putExtra("detail",complainListData.get(pos).getDetail());
                                    intent.putExtra("hostelid",String.valueOf(complainListData.get(pos).getHostelid()));
                                    intent.putExtra("hostelname",complainListData.get(pos).getHostelname());
                                    intent.putExtra("blockid",String.valueOf(complainListData.get(pos).getBlockid()));
                                    intent.putExtra("blockname",complainListData.get(pos).getBlockname());
                                    intent.putExtra("roomid",String.valueOf(complainListData.get(pos).getRoomid()));
                                    intent.putExtra("roomno",complainListData.get(pos).getRoomnono());
                                    intent.putExtra("studentid",String.valueOf(complainListData.get(pos).getStudentid()));
                                    intent.putExtra("name",complainListData.get(pos).getStudentname());
                                    intent.putExtra("status",String.valueOf(complainListData.get(pos).getStatus()));
                                    view.getContext().startActivity(intent);
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

                }
            });
        }

    }

    public ComplainListAdapter(Context context, ArrayList<ComplainListData> complainListData) {
        this.caller = context;
        this.complainListData = complainListData;
        Log.e("Data",complainListData.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_complain_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ComplainListAdapter.MyViewHolder holder, final int position)
    {
        holder.tv_complainid.setText(String.valueOf(complainListData.get(position).getComplainid()));
        holder.tv_title.setText(complainListData.get(position).getTitle());
        holder.tv_detail.setText(complainListData.get(position).getDetail());
        holder.tv_hostelid.setText(String.valueOf(complainListData.get(position).getHostelid()));
        holder.tv_hostelname.setText(complainListData.get(position).getHostelname());
        holder.tv_blockid.setText(String.valueOf(complainListData.get(position).getBlockid()));
        holder.tv_blockname.setText(complainListData.get(position).getBlockname());
        holder.tv_roomid.setText(String.valueOf(complainListData.get(position).getRoomid()));
        holder.tv_roomno.setText(complainListData.get(position).getRoomnono());
        holder.tv_studentid.setText(String.valueOf(complainListData.get(position).getStudentid()));
        holder.tv_studentname.setText(complainListData.get(position).getStudentname());
        holder.tv_status.setText(String.valueOf(complainListData.get(position).getStatus()));
    }

    @Override
    public int getItemCount() {
        return complainListData.size();
    }

}
