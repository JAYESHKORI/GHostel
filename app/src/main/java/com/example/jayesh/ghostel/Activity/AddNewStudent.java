package com.example.jayesh.ghostel.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
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
import com.example.jayesh.ghostel.Utils.RealPathUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNewStudent extends AppCompatActivity {

    private EditText et_enroll,et_fname,et_lname,et_mname,et_email,et_contact,et_address,et_parentcontact,et_em_contact,et_college;
    private TextView tv_dob;
    private Button btn_add;
    private Spinner spn_hostel,spn_block,spn_room;
    private ImageView iv_dp;

    private ArrayList<Integer> hostelListIdAL;
    private ArrayList<String> hostelListNameAL;
    private ArrayAdapter<String> hostelListDataAA;

    private ArrayList<Integer> blockListIdAL;
    private ArrayList<String> blockListNameAL;
    private ArrayAdapter<String> blockListDataAA;

    private ArrayList<Integer> roomListIdAL;
    private ArrayList<String> roomListNoAL;
    private ArrayList<Integer> roomListCapacityAL;
    private ArrayList<Integer> roomListCurrentNoAL;
    private ArrayAdapter<String> roomListDataAA;

    private Bitmap bitmap;

    private final int IMG_REQUEST = 1;

    private String extension=".png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        bitmap = BitmapFactory.decodeResource(AddNewStudent.this.getResources(), R.mipmap.ic_dp);

        et_enroll = (EditText)findViewById(R.id.addSt_et_entoll);
        et_fname = (EditText)findViewById(R.id.addSt_et_fname);
        et_lname = (EditText)findViewById(R.id.addSt_et_lname);
        et_mname = (EditText)findViewById(R.id.addSt_et_mname);
        tv_dob = (TextView)findViewById(R.id.addSt_tv_dob);
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDob();
            }
        });
        et_email = (EditText)findViewById(R.id.addSt_et_email);
        et_contact = (EditText)findViewById(R.id.addSt_et_contact);
        et_address = (EditText)findViewById(R.id.addSt_et_address);
        et_parentcontact = (EditText)findViewById(R.id.addSt_et_parentcontact);
        et_em_contact = (EditText)findViewById(R.id.addSt_et_em_contact);
        et_college = (EditText)findViewById(R.id.addSt_et_college);
        iv_dp = (ImageView)findViewById(R.id.addSt_iv_dp);
        iv_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        spn_hostel = (Spinner)findViewById(R.id.addSt_spn_hostel);
        spn_hostel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                load_blockList(hostelListIdAL.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spn_block = (Spinner)findViewById(R.id.addSt_spn_block);
        spn_block.setVisibility(View.GONE);
        spn_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                load_roomList(blockListIdAL.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spn_room = (Spinner)findViewById(R.id.addSt_spn_room);
        spn_room.setVisibility(View.GONE);
        btn_add = (Button)findViewById(R.id.addSt_btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roomListIdAL.size()==0||blockListIdAL.size()==0)
                {
                    showmessage("Error!","No rooms available. Please select different block or hostel");
                }
                else
                {
                    int cap = roomListCapacityAL.get(spn_room.getSelectedItemPosition());
                    int curno = roomListCurrentNoAL.get(spn_room.getSelectedItemPosition());
                    if(cap>curno)
                        addStudent();
                    else
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(AddNewStudent.this).create();
                        alertDialog.setTitle("Warning");
                        alertDialog.setMessage("There isn't space. Do you still want to proceed?");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                addStudent();
                            }
                        });
                        alertDialog.show();
                    }
                }


            }
        });

        hostelListIdAL = new ArrayList<>();
        hostelListNameAL = new ArrayList<>();
        blockListIdAL = new ArrayList<>();
        blockListNameAL = new ArrayList<>();
        roomListIdAL = new ArrayList<>();
        roomListNoAL = new ArrayList<>();
        roomListCapacityAL = new ArrayList<>();
        roomListCurrentNoAL = new ArrayList<>();

        load_hostelList();
    }

    private void addStudent()
    {
        if(et_enroll.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"Enrollment No. Required",Toast.LENGTH_SHORT).show();
        else if(et_fname.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"First name Required",Toast.LENGTH_SHORT).show();
        else if(et_lname.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"Last name Required",Toast.LENGTH_SHORT).show();
        else if(et_mname.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"Middle name Required",Toast.LENGTH_SHORT).show();
        else if(tv_dob.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"Date of Birth Required",Toast.LENGTH_SHORT).show();
        else if(et_email.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"Email Required",Toast.LENGTH_SHORT).show();
        else if(et_contact.getText().toString().equals("")||et_contact.getText().toString().length()!=10)
            Toast.makeText(AddNewStudent.this,"Contact Required",Toast.LENGTH_SHORT).show();
        else if(et_address.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"Address Required",Toast.LENGTH_SHORT).show();
        else if(et_parentcontact.getText().toString().equals("")||et_parentcontact.getText().toString().length()!=10)
            Toast.makeText(AddNewStudent.this,"Parent contact Required",Toast.LENGTH_SHORT).show();
        else if(et_em_contact.getText().toString().equals("")||et_em_contact.getText().toString().length()!=10)
            Toast.makeText(AddNewStudent.this,"Emergency contact Required",Toast.LENGTH_SHORT).show();
        else if(et_college.getText().toString().equals(""))
            Toast.makeText(AddNewStudent.this,"College Required",Toast.LENGTH_SHORT).show();

        else
        {
            final ProgressDialog progressDialog = new ProgressDialog(AddNewStudent.this);
            progressDialog.setMessage("Creating New Student...");
            progressDialog.show();

            Log.e("enrollment no",et_enroll.getText().toString());
            Log.e("first_name",et_fname.getText().toString());
            Log.e("last_name",et_lname.getText().toString());
            Log.e("middle_name",et_mname.getText().toString());
            Log.e("dob",tv_dob.getText().toString());
            Log.e("email",et_email.getText().toString());
            Log.e("contact",et_contact.getText().toString());
            Log.e("address",et_address.getText().toString());
            Log.e("parent contact",et_parentcontact.getText().toString());
            Log.e("emergency contact",et_em_contact.getText().toString());
            Log.e("college",et_college.getText().toString());
            Log.e("Hostel Id",String.valueOf(hostelListIdAL.get(spn_hostel.getSelectedItemPosition())));
            Log.e("Block Id",String.valueOf(blockListIdAL.get(spn_block.getSelectedItemPosition())));
            Log.e("Room Id",String.valueOf(roomListIdAL.get(spn_room.getSelectedItemPosition())));
            Log.e("dp",imageToString(bitmap));
            Log.e("extension",extension);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                    + Const.API_ADDNEWSTUDENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                response = jsonObject.getString("response");
                                Toast.makeText(AddNewStudent.this,response,Toast.LENGTH_LONG).show();
                                startActivity(new Intent(AddNewStudent.this,AdminActivity.class));
                                finish();
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
                    params.put("enroll_no",et_enroll.getText().toString());
                    params.put("first_name",et_fname.getText().toString());
                    params.put("last_name",et_lname.getText().toString());
                    params.put("middle_name",et_mname.getText().toString());
                    params.put("dob",tv_dob.getText().toString());
                    params.put("email",et_email.getText().toString().toLowerCase());
                    params.put("contact",et_contact.getText().toString());
                    params.put("address",et_address.getText().toString());
                    params.put("p_contact",et_parentcontact.getText().toString());
                    params.put("em_contact",et_em_contact.getText().toString());
                    params.put("college",et_college.getText().toString());
                    params.put("hostelid",String.valueOf(hostelListIdAL.get(spn_hostel.getSelectedItemPosition())));
                    params.put("blockid",String.valueOf(blockListIdAL.get(spn_block.getSelectedItemPosition())));
                    params.put("roomid",String.valueOf(roomListIdAL.get(spn_room.getSelectedItemPosition())));
                    params.put("password",tv_dob.getText().toString());
                    params.put("dp",imageToString(bitmap));
                    params.put("extension",extension);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AddNewStudent.this);
            requestQueue.add(stringRequest);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null)
        {
            Uri path = data.getData();
            String realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());
            extension =  realPath.substring(realPath.lastIndexOf("."));
            Log.d("extension",extension);
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
        final ProgressDialog progressDialog = new ProgressDialog(AddNewStudent.this);
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
                                    AddNewStudent.this,
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
        RequestQueue requestQueue = Volley.newRequestQueue(AddNewStudent.this);
        requestQueue.add(stringRequest);
    }

    private void load_blockList(final int hostelId)
    {
        final ProgressDialog progressDialog = new ProgressDialog(AddNewStudent.this);
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
                                spn_block.setVisibility(View.GONE);
                                spn_room.setVisibility(View.GONE);
                                showmessage("Error!","There isn't any block inside this hostel " +
                                        "Either create new block or select differet hostel");
                            }
                            else
                            {
                                blockListDataAA = new ArrayAdapter<String>(
                                        AddNewStudent.this,
                                        android.R.layout.simple_spinner_item,
                                        blockListNameAL);
                                spn_block.setAdapter(blockListDataAA);
                                spn_block.setVisibility(View.VISIBLE);
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
        RequestQueue requestQueue = Volley.newRequestQueue(AddNewStudent.this);
        requestQueue.add(stringRequest);
    }

    private void load_roomList(final int blockid)
    {
        Log.d("blockid ",String.valueOf(blockid));
        final ProgressDialog progressDialog = new ProgressDialog(AddNewStudent.this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        roomListIdAL.clear();roomListNoAL.clear();roomListCapacityAL.clear();roomListCurrentNoAL.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_GETROOMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("room list",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                roomListIdAL.add(jsonArray.getJSONObject(j).getInt("roomid"));
                                roomListNoAL.add(jsonArray.getJSONObject(j).getString("roomno"));
                                roomListCapacityAL.add(jsonArray.getJSONObject(j).getInt("capacity"));
                                roomListCurrentNoAL.add(jsonArray.getJSONObject(j).getInt("current_no"));
                            }
                            if (roomListIdAL.size()==0)
                            {
                                spn_room.setVisibility(View.GONE);
                                showmessage("Error!","There isn't any room inside this block. " +
                                        "Either create new block or select differet hostel");
                            }
                            else
                            {
                                roomListDataAA = new ArrayAdapter<String>(
                                        AddNewStudent.this,
                                        android.R.layout.simple_spinner_item,
                                        roomListNoAL);
                                spn_room.setAdapter(roomListDataAA);
                                spn_room.setVisibility(View.VISIBLE);
                            }
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
        RequestQueue requestQueue = Volley.newRequestQueue(AddNewStudent.this);
        requestQueue.add(stringRequest);
    }

    private void getDob()
    {
        Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewStudent.this,
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

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

    private void showmessage(String title, String msg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(AddNewStudent.this).create();
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

