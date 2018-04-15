package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RectorAddRoom extends AppCompatActivity {

    private TextView et_capacity,et_roomno;
    private Button btn_createRoom;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rector_add_room);

        session = new Session(RectorAddRoom.this);
        et_roomno = (TextView)findViewById(R.id.et_roomno);
        et_capacity = (TextView)findViewById(R.id.et_capacity);
        btn_createRoom = (Button)findViewById(R.id.btn_createRoom);
        btn_createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    createRoom();
            }
        });
    }

    private void createRoom()
    {
        if(et_capacity.getText().toString().equals(""))
            Toast.makeText(RectorAddRoom.this,"Room capacity required",Toast.LENGTH_SHORT).show();
        else if(et_roomno.getText().toString().equals(""))
            Toast.makeText(RectorAddRoom.this,"Room no required",Toast.LENGTH_SHORT).show();
        else
        {
            final ProgressDialog progressDialog = new ProgressDialog(RectorAddRoom.this);
            progressDialog.setMessage("Creating New Room...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                    + Const.API_ADDNEWROOM,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            showmessage("Message",response);
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
                    params.put("hostelid",String.valueOf(session.gethostelid()));
                    params.put("blockid",String.valueOf(session.getblockid()));
                    params.put("capacity",et_capacity.getText().toString());
                    params.put("roomno",et_roomno.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RectorAddRoom.this);
            requestQueue.add(stringRequest);
        }
    }

    private void showmessage(String title, String msg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(RectorAddRoom.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
