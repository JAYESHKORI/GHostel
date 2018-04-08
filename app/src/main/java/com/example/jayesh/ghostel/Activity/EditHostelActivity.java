package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

public class EditHostelActivity extends AppCompatActivity {

    private EditText et_hostelname,et_desc;
    private RadioGroup rg_type;
    private RadioButton rb_boys,rb_girls,rb_both;
    private Button btn_update;

    private int hostelid;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hostel);

        et_hostelname = (EditText)findViewById(R.id.et_hostelname);
        et_desc = (EditText)findViewById(R.id.et_desc);
        rg_type = (RadioGroup)findViewById(R.id.rg_type);
        rb_boys =(RadioButton)findViewById(R.id.rb_boys);
        rb_girls =(RadioButton)findViewById(R.id.rb_girls);
        rb_both =(RadioButton)findViewById(R.id.rb_both);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHostel();
            }
        });

        hostelid = getIntent().getExtras().getInt("hostelid");
        et_hostelname.setText(getIntent().getExtras().getString("hostelname"));
        et_desc.setText(getIntent().getExtras().getString("description"));
        type = getIntent().getExtras().getString("type");

        if (rb_boys.getText().toString().equals(type))
            rb_boys.setChecked(true);
        else if (rb_girls.getText().toString().equals(type))
            rb_girls.setChecked(true);
        else
            rb_both.setChecked(true);
    }

    private void updateHostel()
    {
        int selectedId = rg_type .getCheckedRadioButtonId();
        final RadioButton radioButton = (RadioButton) findViewById(selectedId);

        final ProgressDialog progressDialog = new ProgressDialog(EditHostelActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_EDITHOSTEL,
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
                params.put("hostelid", String.valueOf(hostelid));
                params.put("hostelname", et_hostelname.getText().toString());
                params.put("description", et_desc.getText().toString());
                params.put("type", radioButton.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditHostelActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showmessage(String title, String msg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(EditHostelActivity.this).create();
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
