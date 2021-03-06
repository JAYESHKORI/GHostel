package com.example.jayesh.ghostel.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.Activity.EditHostelActivity;
import com.example.jayesh.ghostel.Activity.ViewHostelActivity;
import com.example.jayesh.ghostel.Model.HostelListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                                    Intent intent = new Intent(view.getContext(), EditHostelActivity.class);
                                    intent.putExtra("hostelid", hostelListData.get(pos).getId());
                                    intent.putExtra("hostelname", hostelListData.get(pos).getName());
                                    intent.putExtra("description", hostelListData.get(pos).getDescription());
                                    intent.putExtra("type", hostelListData.get(pos).getType());
                                    view.getContext().startActivity(intent);
                                    break;
                                case 2:
                                    deleteHostel(hostelListData.get(pos).getId());
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
                    view.getContext().startActivity(new Intent(view.getContext(),ViewHostelActivity.class)
                            .putExtra("hostelid",hostelListData.get(pos).getId()));
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

    private void deleteHostel(final int hostelid) {
        final ProgressDialog progressDialog = new ProgressDialog(caller);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_DELHOSTEL,
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
                params.put("hostelid",String.valueOf(hostelid));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(caller);
        requestQueue.add(stringRequest);
    }
}


