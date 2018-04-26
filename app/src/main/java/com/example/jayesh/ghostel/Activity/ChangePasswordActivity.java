package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextView tv_username;
    private EditText et_oldpwd,et_newpwd1,et_newpwd2;
    private Button btn_chngpwd;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        tv_username = (TextView)findViewById(R.id.tv_username);
        et_newpwd1 = (EditText)findViewById(R.id.et_newpwd1);
        et_newpwd2 = (EditText)findViewById(R.id.et_newpwd2);
        btn_chngpwd = (Button)findViewById(R.id.btn_chngpwd);

        session = new Session(ChangePasswordActivity.this);

        tv_username.setText(session.getUsername());
        btn_chngpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_newpwd1.getText().toString().equals(et_newpwd2.getText().toString()))
                {
                    Toast.makeText(ChangePasswordActivity.this,"New Passwords are not matching",Toast.LENGTH_LONG).show();
                }
                else
                {
                    changePassword();
                    finish();
                }
            }
        });
    }

    private void changePassword() {
        final ProgressDialog progressDialog = new ProgressDialog(ChangePasswordActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_CHANGEPASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        Toast.makeText(ChangePasswordActivity.this,response,Toast.LENGTH_LONG).show();
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
                params.put("username",session.getUsername());
//                params.put("oldpwd",et_oldpwd.getText().toString());
                params.put("newpwd",et_newpwd1.getText().toString());
                params.put("usertype",session.getUsertype());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ChangePasswordActivity.this);
        requestQueue.add(stringRequest);
    }
}
