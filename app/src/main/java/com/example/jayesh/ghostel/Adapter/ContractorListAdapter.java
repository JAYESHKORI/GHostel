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
import com.example.jayesh.ghostel.Activity.EditContractorActivity;
import com.example.jayesh.ghostel.Activity.ViewBlockActivity;
import com.example.jayesh.ghostel.Activity.ViewCommonDataActivity;
import com.example.jayesh.ghostel.Activity.ViewContractorActivity;
import com.example.jayesh.ghostel.Model.CommonData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayesh on 19/2/18.
 */

public class ContractorListAdapter extends RecyclerView.Adapter<ContractorListAdapter.MyViewHolder>
{
    private Context caller;
    private ArrayList<CommonData> commonData;

    public class MyViewHolder extends RecyclerView.ViewHolder{

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

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    final int pos=getLayoutPosition();
                    final CharSequence[] items = {"View", "Edit", "Delete"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setTitle(commonData.get(pos).getFname()+" "+commonData.get(pos).getLname());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item)
                            {
                                case 0:
                                    view.getContext().startActivity(new Intent(view.getContext(),ViewContractorActivity.class)
                                            .putExtra("contractorid",commonData.get(pos).getId()));
                                    break;
                                case 1:
                                    view.getContext().startActivity(new Intent(view.getContext(),EditContractorActivity.class)
                                            .putExtra("contractorid",commonData.get(pos).getId()));
                                    break;
                                case 2:
                                    showwarning("Warning!","Do you really want to delete this record permenantly?",commonData.get(pos).getId());
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
                    view.getContext().startActivity(new Intent(view.getContext(),ViewContractorActivity.class)
                            .putExtra("contractorid",commonData.get(pos).getId()));

                }
            });
        }

    }

    public ContractorListAdapter(Context context, ArrayList<CommonData> commondata) {
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
    public void onBindViewHolder(ContractorListAdapter.MyViewHolder holder, final int position)
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

    private void showwarning(String title, String msg, final int contractorid)
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
                deleteStudent(contractorid);
            }
        });
        alertDialog.show();
    }

    private void deleteStudent(final int contractorid) {
        final ProgressDialog progressDialog = new ProgressDialog(caller);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_DELCONTRACTOR,
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
                params.put("contractorid",String.valueOf(contractorid));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(caller);
        requestQueue.add(stringRequest);
    }
}
