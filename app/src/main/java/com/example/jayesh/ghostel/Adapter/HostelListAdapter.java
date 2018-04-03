package com.example.jayesh.ghostel.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.jayesh.ghostel.Activity.ViewHostelActivity;
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_ad_id,tv_ad_name,tv_ad_type,tv_ad_description;

        public MyViewHolder(View view) {
            super(view);

            tv_ad_id = (TextView)view.findViewById(R.id.tv_ad_id);
            tv_ad_name = (TextView)view.findViewById(R.id.tv_ad_name);
            tv_ad_type = (TextView)view.findViewById(R.id.tv_ad_type);
            tv_ad_description = (TextView)view.findViewById(R.id.tv_ad_description);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    final int pos=getLayoutPosition();
                    final CharSequence[] items = {"View", "Edit", "Delete"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setTitle(hostelListData.get(pos).getName());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item)
                            {
                                case 0:
                                    view.getContext().startActivity(new Intent(view.getContext(),ViewHostelActivity.class)
                                            .putExtra("hostelid",hostelListData.get(pos).getId()));
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
                    System.out.println("Click: "+hostelListData.get(pos).getId());
                }
            });
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


