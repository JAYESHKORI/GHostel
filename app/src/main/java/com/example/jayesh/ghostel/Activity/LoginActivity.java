package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_login_Email,et_login_Pwd;
    private Spinner spn_usertype;
    private Button btnLogin;
    private TextView tvSignup;

    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_Email = (EditText) findViewById(R.id.et_login_Email);
        et_login_Pwd = (EditText) findViewById(R.id.et_login_Pwd);
        spn_usertype = (Spinner)findViewById(R.id.spn_usertype);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvSignup = (TextView)findViewById(R.id.tvSignup);

        btnLogin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view == btnLogin)
        {
            userLogin();
        }
        if(view == tvSignup)
        {
            startActivity(new Intent(this,SignUpActivity.class));
            finish();
        }
    }

    private void userLogin() {
        String email = et_login_Email.getText().toString().trim();
        String password = et_login_Pwd.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!spn_usertype.getSelectedItem().equals("ADMIN"))
        {
            Log.d("username",et_login_Email.getText().toString());
            Log.d("password",et_login_Pwd.getText().toString());
            Log.d("usertype",spn_usertype.getSelectedItem().toString());

            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Validating user..");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                    + Const.API_VALIDATEUSER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Log.d("response",response);
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                if(jsonArray.getJSONObject(0).getInt("response")==1)
                                {
                                    id = jsonArray.getJSONObject(0).getInt("id");
                                    Log.d("id",String.valueOf(jsonArray.getJSONObject(0).getInt("id")));
                                }
                                else if(jsonArray.getJSONObject(0).getInt("response")==0)
                                {
                                    Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,"Try again",Toast.LENGTH_SHORT).show();
                                }

                                if (spn_usertype.getSelectedItem().equals("STUDENT"))
                                {
                                    Intent i = new Intent(LoginActivity.this, StudentActivity.class);
                                    startActivity(i);
                                }
                                else if(spn_usertype.getSelectedItem().equals("RECTOR"))
                                {
                                    Intent i = new Intent(LoginActivity.this, RectorActivity.class);
                                    startActivity(i);
                                }
                                else if(spn_usertype.getSelectedItem().equals("MESS CONTRACTOR"))
                                {
                                    Intent i = new Intent(LoginActivity.this, ContractorActivity.class);
                                    startActivity(i);
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
                    params.put("username",et_login_Email.getText().toString());
                    params.put("password",et_login_Pwd.getText().toString());
                    params.put("usertype",spn_usertype.getSelectedItem().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);
        }
        else
        {
            if (et_login_Email.getText().toString().equals("admin")&&et_login_Pwd.getText().toString().equals("admin"))
            {
                Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                startActivity(i);
            }
            else
                Toast.makeText(LoginActivity.this,"No such user",Toast.LENGTH_SHORT).show();
        }
    }
}
