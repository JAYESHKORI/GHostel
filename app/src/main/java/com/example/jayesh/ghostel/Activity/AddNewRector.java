package com.example.jayesh.ghostel.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNewRector extends AppCompatActivity{

    private EditText et_fname,et_lname,et_mname,et_email,et_contact,et_address,et_em_contact;
    private TextView tv_dob;
    private Button btn_add;
    private Spinner spn_hostel,spn_block;
    private ImageView iv_dp;

    private ArrayList<Integer> hostelListIdAL;
    private ArrayList<String> hostelListNameAL;
    private ArrayAdapter<String> hostelListDataAA;

    private ArrayList<Integer> blockListIdAL;
    private ArrayList<String> blockListNameAL;
    private ArrayAdapter<String> blockListDataAA;

    private Bitmap bitmap;

    private final int IMG_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_rector);

        et_fname = (EditText)findViewById(R.id.addRact_et_fname);
        et_lname = (EditText)findViewById(R.id.addRact_et_lname);
        et_mname = (EditText)findViewById(R.id.addRact_et_mname);
        tv_dob = (TextView)findViewById(R.id.addRact_tv_dob);
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDob();
            }
        });
        et_email = (EditText)findViewById(R.id.addRact_et_email);
        et_contact = (EditText)findViewById(R.id.addRact_et_contact);
        et_address = (EditText)findViewById(R.id.addRact_et_address);
        et_em_contact = (EditText)findViewById(R.id.addRact_et_em_contact);
        iv_dp = (ImageView)findViewById(R.id.addRact_iv_dp);
        iv_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        spn_hostel = (Spinner)findViewById(R.id.addRact_spn_hostel);
        spn_hostel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                load_blockList(hostelListIdAL.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spn_block = (Spinner)findViewById(R.id.addRact_spn_block);
        btn_add = (Button)findViewById(R.id.addRact_btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRector();
            }
        });

        hostelListIdAL = new ArrayList<>();
        hostelListNameAL = new ArrayList<>();
        blockListIdAL = new ArrayList<>();
        blockListNameAL = new ArrayList<>();

        load_hostelList();
    }

    private void addRector()
    {
        if(et_fname.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"First name Required",Toast.LENGTH_SHORT).show();
        else if(et_lname.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"Last name Required",Toast.LENGTH_SHORT).show();
        else if(et_mname.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"Middle name Required",Toast.LENGTH_SHORT).show();
        else if(tv_dob.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"Date of Birth Required",Toast.LENGTH_SHORT).show();
        else if(et_email.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"Email Required",Toast.LENGTH_SHORT).show();
        else if(et_contact.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"Contact Required",Toast.LENGTH_SHORT).show();
        else if(et_address.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"Address Required",Toast.LENGTH_SHORT).show();
        else if(et_em_contact.getText().toString().equals(""))
            Toast.makeText(AddNewRector.this,"Emergency contact Required",Toast.LENGTH_SHORT).show();
        else
        {
            final ProgressDialog progressDialog = new ProgressDialog(AddNewRector.this);
            progressDialog.setMessage("Creating New Rector...");
            progressDialog.show();

            Log.e("first_name",et_fname.getText().toString());
            Log.e("last_name",et_lname.getText().toString());
            Log.e("middle_name",et_mname.getText().toString());
            Log.e("dob",tv_dob.getText().toString());
            Log.e("email",et_email.getText().toString());
            Log.e("contact",et_contact.getText().toString());
            Log.e("address",et_address.getText().toString());
            Log.e("emergency contact",et_em_contact.getText().toString());
            Log.e("Hostel Id",spn_hostel.getSelectedItem().toString());
            Log.e("Block Id",spn_block.getSelectedItem().toString());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                    + Const.API_ADDNEWRECTOR,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(AddNewRector.this,response,Toast.LENGTH_LONG).show();
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
                    params.put("first_name",et_fname.getText().toString());
                    params.put("last_name",et_lname.getText().toString());
                    params.put("middle_name",et_mname.getText().toString());
                    params.put("dob",tv_dob.getText().toString());
                    params.put("email",et_email.getText().toString());
                    params.put("contact",et_contact.getText().toString());
                    params.put("address",et_address.getText().toString());
                    params.put("em_contact",et_em_contact.getText().toString());
                    params.put("hostelid",spn_hostel.getSelectedItem().toString());
                    params.put("blockid",spn_block.getSelectedItem().toString());
                    params.put("password",tv_dob.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AddNewRector.this);
            requestQueue.add(stringRequest);

        }
    }

    private void getDob()
    {
        Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewRector.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private  void selectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null)
        {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                iv_dp.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load_hostelList()
    {
        final ProgressDialog progressDialog = new ProgressDialog(AddNewRector.this);
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
                                    AddNewRector.this,
                                    android.R.layout.simple_spinner_item,
                                    hostelListNameAL);
                            spn_hostel.setAdapter(hostelListDataAA);
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
        RequestQueue requestQueue = Volley.newRequestQueue(AddNewRector.this);
        requestQueue.add(stringRequest);
    }

    private void load_blockList(final int hostelId)
    {
        Log.d("hostelId ",String.valueOf(hostelId));
        final ProgressDialog progressDialog = new ProgressDialog(AddNewRector.this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        blockListIdAL.clear();
        blockListNameAL.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_BLOCKLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("response",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                if (jsonArray.getJSONObject(j).getInt("hostelid")==hostelId)
                                {
                                    blockListIdAL.add(jsonArray.getJSONObject(j).getInt("blockid"));
                                    blockListNameAL.add(jsonArray.getJSONObject(j).getString("bname"));
                                }
                            }
                            blockListDataAA = new ArrayAdapter<String>(
                                    AddNewRector.this,
                                    android.R.layout.simple_spinner_item,
                                    blockListNameAL);
                            spn_block.setAdapter(blockListDataAA);
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
        RequestQueue requestQueue = Volley.newRequestQueue(AddNewRector.this);
        requestQueue.add(stringRequest);
    }

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}
