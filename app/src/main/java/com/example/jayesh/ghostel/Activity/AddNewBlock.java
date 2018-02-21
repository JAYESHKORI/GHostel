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
import com.example.jayesh.ghostel.Model.HostelListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class AddNewBlock extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spn_addB_hostelList;
    private ArrayList<Integer> hostelListIdAL;
    private ArrayList<String> hostelListNameAL;
    private ArrayAdapter<String> hostelListDataAA;

    private EditText et_BName;
    private RadioGroup rg_type;
    private RadioButton rb_type;
    private String type;
    private int hostelID;
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

        et_BName = (EditText) findViewById(R.id.et_addB_bname);
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
//        int selectedId = rg_type.getCheckedRadioButtonId();
//        rb_type = (RadioButton)findViewById(selectedId);
//        type = rb_type.getText().toString();
        type = ((RadioButton)findViewById(rg_type.getCheckedRadioButtonId())).getText().toString();
        Log.e("hostelId",String.valueOf(hostelID));
        Log.e("hostelNmae",hostelNmae);
        Log.e("blockNmae",et_BName.getText().toString());
        Log.e("type",type);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        hostelNmae = (String)adapterView.getItemAtPosition(i);
        hostelID = hostelListIdAL.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

