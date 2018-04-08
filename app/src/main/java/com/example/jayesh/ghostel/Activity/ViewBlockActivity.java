package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.HashMap;
import java.util.Map;

public class ViewBlockActivity extends AppCompatActivity {

    int blockid;
    TextView tv_bname,tv_hname,tv_cap,tv_type,tv_rector,tv_contractor,tv_total_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_block);

        blockid = Integer.parseInt(getIntent().getExtras().getString("blockid"));

        tv_bname = (TextView)findViewById(R.id.tv_bname);
        tv_hname = (TextView)findViewById(R.id.tv_hname);
        tv_cap = (TextView)findViewById(R.id.tv_cap);
        tv_type = (TextView)findViewById(R.id.tv_type);
        tv_rector = (TextView)findViewById(R.id.tv_rector);
        tv_contractor = (TextView)findViewById(R.id.tv_contractor);
        tv_total_student = (TextView)findViewById(R.id.tv_total_student);
        loadViewBlock();
    }

    private void loadViewBlock()
    {
        final ProgressDialog progressDialog = new ProgressDialog(ViewBlockActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_VIEWBLOCK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if (jsonArray.getJSONObject(0).getInt("response")==1)
                            {
                                tv_bname.setText(jsonArray.getJSONObject(0).getString("blockname"));
                                tv_hname.setText("Hostel : "+jsonArray.getJSONObject(0).getString("hostelname"));
                                tv_cap.setText("Capacity : "+jsonArray.getJSONObject(0).getString("blockcapacity"));
                                tv_rector.setText("Rector : "+jsonArray.getJSONObject(0).getString("rname"));
                                tv_contractor.setText("Mess Contractor : "+jsonArray.getJSONObject(0).getString("cname"));
                                tv_total_student.setText("Total Students : "+jsonArray.getJSONObject(0).getString("totalStudent"));

                                if (jsonArray.getJSONObject(0).getString("blocktype").equals("Both"))
                                    tv_type.setText("For Girls & Boys "+jsonArray.getJSONObject(0).getString(""));
                                else
                                    tv_type.setText("For "+jsonArray.getJSONObject(0).getString("blocktype")+" Only");
                            }
                            else
                            {
                                Log.e("Response Error ",jsonArray.toString());
                            }
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
                params.put("blockid",String.valueOf(blockid));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ViewBlockActivity.this);
        requestQueue.add(stringRequest);
    }
}
