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

public class EditBlockActivity extends AppCompatActivity {

    private EditText et_blockname, et_capacity;
    private RadioGroup rg_type;
    private RadioButton rb_boys,rb_girls,rb_both;
    private Button btn_update;

    private int blockid;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_block);

        et_blockname = (EditText) findViewById(R.id.et_blockname);
        et_capacity = (EditText) findViewById(R.id.et_capacity);
        rg_type = (RadioGroup)findViewById(R.id.rg_type);
        rb_boys =(RadioButton)findViewById(R.id.rb_boys);
        rb_girls =(RadioButton)findViewById(R.id.rb_girls);
        rb_both =(RadioButton)findViewById(R.id.rb_both);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBlock();
            }
        });

        blockid = getIntent().getExtras().getInt("blockid");
        et_capacity.setText(String.valueOf(getIntent().getExtras().getInt("capacity")));
        et_blockname.setText(getIntent().getExtras().getString("blockname"));
        type = getIntent().getExtras().getString("type");

        if (rb_boys.getText().toString().equals(type))
            rb_boys.setChecked(true);
        else if (rb_girls.getText().toString().equals(type))
            rb_girls.setChecked(true);
        else
            rb_both.setChecked(true);
    }

    private void updateBlock()
    {
        int selectedId = rg_type .getCheckedRadioButtonId();
        final RadioButton radioButton = (RadioButton) findViewById(selectedId);

        final ProgressDialog progressDialog = new ProgressDialog(EditBlockActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_EDITBLOCK,
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
                params.put("blockid", String.valueOf(blockid));
                params.put("blockname", et_blockname.getText().toString());
                params.put("capacity", et_capacity.getText().toString());
                params.put("type", radioButton.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditBlockActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showmessage(String title, String msg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(EditBlockActivity.this).create();
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
