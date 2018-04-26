package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashMap;
import java.util.Map;

public class ViewRoomActivity extends AppCompatActivity {
    private TextView tv_roomno,tv_capacity,tv_current_no;
    private ListView lv_studentlist;
    private ArrayList<String> studentnames = new ArrayList<>();
    private ArrayList<Integer> studentids = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room);

        tv_roomno = (TextView)findViewById(R.id.tv_roomno);
        tv_capacity = (TextView)findViewById(R.id.tv_capacity);
        tv_current_no = (TextView)findViewById(R.id.tv_current_no);
        lv_studentlist = (ListView)findViewById(R.id.lv_studentlist);

        tv_roomno.setText(getIntent().getStringExtra("roomno"));
        tv_capacity.setText(getIntent().getStringExtra("capacity"));
        tv_current_no.setText(getIntent().getStringExtra("current_no"));

        loadroomdata(getIntent().getIntExtra("roomid",0));
    }

    private void loadroomdata(final int roomid) {
        final ProgressDialog progressDialog = new ProgressDialog(ViewRoomActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_VIEWROOM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("room mates",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                studentnames.add(jsonArray.getJSONObject(i).getString("name"));
                                studentids.add(jsonArray.getJSONObject(i).getInt("studentid"));
                            }
                            Log.d("studentnames",studentnames.toString());
                            ArrayAdapter<String> AAstudentnames = new ArrayAdapter<String>(ViewRoomActivity.this, android.R.layout.simple_list_item_1,studentnames);
                            lv_studentlist.setAdapter(AAstudentnames);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
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
        RequestQueue requestQueue = Volley.newRequestQueue(ViewRoomActivity.this);
        requestQueue.add(stringRequest);
    }
}
