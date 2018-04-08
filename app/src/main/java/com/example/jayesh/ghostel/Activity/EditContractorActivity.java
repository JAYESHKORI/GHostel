package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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

public class EditContractorActivity extends AppCompatActivity {

    private Spinner spn_hostelname,spn_blockname;
    private Button btn_update;

    private int contractorid;
    private String url;

    private ArrayList<Integer> hostelListIdAL = new ArrayList<>();
    private ArrayList<String> hostelListNameAL = new ArrayList<>();;
    private ArrayAdapter<String> hostelListDataAA ;

    private ArrayList<Integer> blockListIdAL = new ArrayList<>();;
    private ArrayList<String> blockListNameAL = new ArrayList<>();;
    private ArrayAdapter<String> blockListDataAA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contractor);

        spn_hostelname = (Spinner)findViewById(R.id.spn_hostel);
        spn_blockname = (Spinner)findViewById(R.id.spn_block);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blockListIdAL.size()==0)
                {
                    showmessage("Error!","There isn't any block inside this hostel " +
                            "Either create new block or select differet hostel");
                }
                else
                {
                    editContractorInfo();
                }
            }
        });

        spn_hostelname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                load_blockList(hostelListIdAL.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        contractorid = getIntent().getExtras().getInt("contractorid");
        load_hostelList();
    }

    private void editContractorInfo()
    {
        final ProgressDialog progressDialog = new ProgressDialog(EditContractorActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_EDITCONTRACTOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
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
                params.put("contractorid",String.valueOf(contractorid));
                params.put("hostelid",String.valueOf(hostelListIdAL.get(spn_hostelname.getSelectedItemPosition())));
                params.put("blockid",String.valueOf(blockListIdAL.get(spn_blockname.getSelectedItemPosition())));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditContractorActivity.this);
        requestQueue.add(stringRequest);
    }

    private void load_hostelList()
    {
        final ProgressDialog progressDialog = new ProgressDialog(EditContractorActivity.this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_HOSTELLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("hostel list",response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                hostelListIdAL.add(jsonArray.getJSONObject(j).getInt("id"));
                                hostelListNameAL.add(jsonArray.getJSONObject(j).getString("name"));
                            }
                            hostelListDataAA = new ArrayAdapter<String>(
                                    EditContractorActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    hostelListNameAL);
                            spn_hostelname.setAdapter(hostelListDataAA);
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
        );
        RequestQueue requestQueue = Volley.newRequestQueue(EditContractorActivity.this);
        requestQueue.add(stringRequest);
    }

    private void load_blockList(final int hostelId)
    {
        final ProgressDialog progressDialog = new ProgressDialog(EditContractorActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        blockListIdAL.clear();blockListNameAL.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_GETBLOCKS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("block list",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                blockListIdAL.add(jsonArray.getJSONObject(j).getInt("blockid"));
                                blockListNameAL.add(jsonArray.getJSONObject(j).getString("bname"));
                            }
                            if (blockListIdAL.size()==0)
                            {
                                spn_blockname.setVisibility(View.GONE);
                                showmessage("Error!","There isn't any block inside this hostel " +
                                        "Either create new block or select differet hostel");
                            }
                            else
                            {
                                blockListDataAA = new ArrayAdapter<String>(
                                        EditContractorActivity.this,
                                        android.R.layout.simple_spinner_item,
                                        blockListNameAL);
                                spn_blockname.setAdapter(blockListDataAA);
                                spn_blockname.setVisibility(View.VISIBLE);
                            }

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
                params.put("hostelid",String.valueOf(hostelId));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditContractorActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showmessage(String title, String msg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(EditContractorActivity.this).create();
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
