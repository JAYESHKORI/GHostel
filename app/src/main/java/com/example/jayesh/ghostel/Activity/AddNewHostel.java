package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class AddNewHostel extends AppCompatActivity {

    private EditText et_hostel_name,et_desc;
    private RadioGroup rg_type;
    private Button btn_create_hostel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_hostel);

        et_hostel_name = (EditText) findViewById(R.id.et_hostel_name);
        et_desc = (EditText) findViewById(R.id.et_desc);
        rg_type = (RadioGroup)findViewById(R.id.rg_type);
        btn_create_hostel = (Button)findViewById(R.id.btn_create_hostel);
        btn_create_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createHostel();
            }
        });
    }

    private void createHostel() {
        int selectedId = rg_type.getCheckedRadioButtonId();
        final RadioButton radioButton = (RadioButton) findViewById(selectedId);

        if (et_hostel_name.getText().toString().equals(""))
        {
            Toast.makeText(AddNewHostel.this,"Provide hostel name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.d("hostelName",et_hostel_name.getText().toString());
            Log.d("descrption",et_desc.getText().toString());
            Log.d("type",radioButton.getText().toString());

            final ProgressDialog progressDialog = new ProgressDialog(AddNewHostel.this);
            progressDialog.setMessage("Creating New Block..");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                    + Const.API_ADDNEWHOSTEL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(AddNewHostel.this,response,Toast.LENGTH_SHORT).show();
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
                    params.put("hostelName",et_hostel_name.getText().toString());
                    params.put("descrption",et_desc.getText().toString());
                    params.put("type",radioButton.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AddNewHostel.this);
            requestQueue.add(stringRequest);
        }
    }
}
