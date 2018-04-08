package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ViewHostelActivity extends AppCompatActivity {

    int hostelid,totalCapacity;
    String name,description,Htype;
    ArrayList<Integer> blockid = new ArrayList<>();
    ArrayList<String> blockname = new ArrayList<>();
    ArrayList<Integer> blockcapacity = new ArrayList<>();
    ArrayList<String> blocktype = new ArrayList<>();

    TextView tv_hname,tv_total_cap,tv_type,tv_desc;
    ListView lv_blocklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hostel);
        lv_blocklist = (ListView)findViewById(R.id.lv_blocklist);
        hostelid = getIntent().getExtras().getInt("hostelid");
        loadViewHostel();

        tv_hname = (TextView)findViewById(R.id.tv_hname);
        tv_total_cap = (TextView)findViewById(R.id.tv_total_cap);
        tv_type = (TextView)findViewById(R.id.tv_type);
        tv_desc = (TextView)findViewById(R.id.tv_desc);
    }

    private void loadViewHostel()
    {
        final ProgressDialog progressDialog = new ProgressDialog(ViewHostelActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_VIEWHOSTEL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Log.d("viewHostel ",jsonArray.toString());
                            JSONArray hostelDetails = jsonArray.getJSONObject(0).getJSONArray("hostelDetails");
                            JSONArray blockList = jsonArray.getJSONObject(0).getJSONArray("blockList");
                            for (int i=0;i<blockList.length();i++)
                            {
                                blockid.add(Integer.parseInt(blockList.getJSONObject(i).getString("blockid")));
                                blockname.add(blockList.getJSONObject(i).getString("blockname"));
                                blockcapacity.add(Integer.parseInt(blockList.getJSONObject(i).getString("capacity")));
                                blocktype.add(blockList.getJSONObject(i).getString("type"));
                            }

                            hostelid = Integer.parseInt(hostelDetails.getJSONObject(0).getString("hostelid"));
                            name = hostelDetails.getJSONObject(0).getString("name");
                            description = hostelDetails.getJSONObject(0).getString("description");
                            Htype = hostelDetails.getJSONObject(0).getString("type");
                            for (int j=0;j<blockcapacity.size();j++)
                            {
                                totalCapacity = totalCapacity + blockcapacity.get(j);
                            }
                            fillData();
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        RequestQueue requestQueue = Volley.newRequestQueue(ViewHostelActivity.this);
        requestQueue.add(stringRequest);
    }

    private void fillData()
    {
        tv_hname.setText(name);
        tv_total_cap.setText("Total Capacity : "+totalCapacity);
        tv_desc.setText(description);
        if (Htype.equals("Both"))
            tv_type.setText("For Girls & Boys "+Htype);
        else
            tv_type.setText("For "+Htype+" Only");

        ArrayAdapter<String> blocklistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, blockname);
        lv_blocklist.setAdapter( blocklistAdapter );
        lv_blocklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(view.getContext(),ViewBlockActivity.class)
                        .putExtra("blockid",String.valueOf(blockid.get(i))));
            }
        });
    }
}
