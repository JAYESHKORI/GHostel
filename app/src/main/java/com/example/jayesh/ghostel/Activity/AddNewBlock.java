package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

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

public class AddNewBlock extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spn_addB_hostelList;
    private ArrayList<Integer> hostelListIdAL;
    private ArrayList<String> hostelListNameAL;
    private ArrayList<String> hostelListTypeAL;
    private ArrayAdapter<String> hostelListDataAA;

    private EditText et_BName, et_Bcapacity;
    private RadioGroup rg_type;
    private String type;
    private int hostelID;
    private String hostelType;
    private String hostelNmae;
    private Button btn_create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_block);

        spn_addB_hostelList = (Spinner)findViewById(R.id.spn_addB_hostelList);
        spn_addB_hostelList.setOnItemSelectedListener(this);
        hostelListIdAL = new ArrayList<>();
        hostelListNameAL = new ArrayList<>();
        hostelListTypeAL = new ArrayList<>();

        et_BName = (EditText) findViewById(R.id.et_addB_bname);
        et_Bcapacity = (EditText) findViewById(R.id.et_Bcapacity);
        rg_type = (RadioGroup)findViewById(R.id.rg_addB_type);
        btn_create = (Button)findViewById(R.id.btn_addB_create);
        btn_create.setOnClickListener(this);

        load_spn_addB_hostelList();
    }

    private void load_spn_addB_hostelList()
    {
        final ProgressDialog progressDialog = new ProgressDialog(AddNewBlock.this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_HOSTELLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("response",response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                hostelListIdAL.add(jsonArray.getJSONObject(j).getInt("id"));
                                hostelListNameAL.add(jsonArray.getJSONObject(j).getString("name"));
                                hostelListTypeAL.add(jsonArray.getJSONObject(j).getString("type"));
                            }
                            hostelListDataAA = new ArrayAdapter<String>(
                                    AddNewBlock.this,
                                    android.R.layout.simple_spinner_item,
                                    hostelListNameAL);
                            spn_addB_hostelList.setAdapter(hostelListDataAA);
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
        RequestQueue requestQueue = Volley.newRequestQueue(AddNewBlock.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view)
    {
        int selectedId = rg_type .getCheckedRadioButtonId();
        final RadioButton radioButton = (RadioButton) findViewById(selectedId);

        if (!hostelType.equals(radioButton.getText().toString())) {
            Toast.makeText(AddNewBlock.this,"Block and Hostel Types are not matching",Toast.LENGTH_SHORT).show();
        }
        else if(et_BName.getText().toString().equals(""))
        {
            Toast.makeText(AddNewBlock.this,"Please enter block name",Toast.LENGTH_SHORT).show();
        }
        else if(et_Bcapacity.getText().toString().equals(""))
        {
            Toast.makeText(AddNewBlock.this,"Please enter block name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            final ProgressDialog progressDialog = new ProgressDialog(AddNewBlock.this);
            progressDialog.setMessage("Creating New Block..");
            progressDialog.show();

            Log.e("hostelId", String.valueOf(hostelID));
            Log.e("hostelName", hostelNmae);
            Log.e("blockNmae", et_BName.getText().toString());
            Log.e("Capacity", et_Bcapacity.getText().toString());
            Log.e("type", radioButton.getText().toString());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                    + Const.API_ADDNEWBLOCK,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(AddNewBlock.this,response,Toast.LENGTH_SHORT).show();
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
                    params.put("hostelId", String.valueOf(hostelID));
                    params.put("blockName", et_BName.getText().toString());
                    params.put("capacity", et_Bcapacity.getText().toString());
                    params.put("type", radioButton.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AddNewBlock.this);
            requestQueue.add(stringRequest);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        hostelNmae = (String)adapterView.getItemAtPosition(i);
        hostelID = hostelListIdAL.get(i);
        hostelType = hostelListTypeAL.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

