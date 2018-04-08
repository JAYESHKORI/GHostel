package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class ViewRectorActivity extends AppCompatActivity {

    private TextView tv_fname,tv_lname,tv_mname,tv_email,tv_contact,tv_address,tv_econtact,tv_dob,tv_hostel,tv_block;
    private ImageView iv_dp;
    private String url;
    int rectorid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rector);

        tv_fname = (TextView)findViewById(R.id.tv_fname);
        tv_lname = (TextView)findViewById(R.id.tv_lname);
        tv_mname = (TextView)findViewById(R.id.tv_mname);
        tv_email = (TextView)findViewById(R.id.tv_email);
        tv_contact = (TextView)findViewById(R.id.tv_contact);
        tv_econtact = (TextView)findViewById(R.id.tv_em_contact);
        tv_dob = (TextView)findViewById(R.id.tv_dob);
        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_hostel = (TextView)findViewById(R.id.tv_hostel);
        tv_block = (TextView)findViewById(R.id.tv_block);
        iv_dp = (ImageView)findViewById(R.id.iv_dp);

        rectorid = getIntent().getExtras().getInt("rectorid");
        loadRectordata();
    }

    private void loadRectordata()
    {
        final ProgressDialog progressDialog = new ProgressDialog(ViewRectorActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_VIEWRECTOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            tv_fname.setText(jsonArray.getJSONObject(0).getString("fname"));
                            tv_lname.setText(jsonArray.getJSONObject(0).getString("lname"));
                            tv_mname.setText(jsonArray.getJSONObject(0).getString("mname"));
                            tv_email.setText(jsonArray.getJSONObject(0).getString("email"));
                            tv_contact.setText(jsonArray.getJSONObject(0).getString("contact"));
                            tv_econtact.setText(jsonArray.getJSONObject(0).getString("econtact"));
                            tv_address.setText(jsonArray.getJSONObject(0).getString("address"));
                            tv_dob.setText(jsonArray.getJSONObject(0).getString("dob"));
                            tv_hostel.setText(jsonArray.getJSONObject(0).getString("hostel"));
                            tv_block.setText(jsonArray.getJSONObject(0).getString("block"));
                            url = jsonArray.getJSONObject(0).getString("url");
                            Log.d("url",Const.API_URL+url);
                            Glide.with(ViewRectorActivity.this).load(Const.API_URL+url).into(iv_dp);
                            progressDialog.dismiss();
                        } catch (Exception e) {
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
                params.put("rectorid",String.valueOf(rectorid));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ViewRectorActivity.this);
        requestQueue.add(stringRequest);
    }
}
