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
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_login_Email,et_login_Pwd;
    private Button btnLogin;
    private TextView tvSignup;

    private int id = 0;
    private String usertype = "X";

    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_Email = (EditText) findViewById(R.id.et_login_Email);
        et_login_Pwd = (EditText) findViewById(R.id.et_login_Pwd);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvSignup = (TextView)findViewById(R.id.tvSignup);

        btnLogin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);

        session = new Session(LoginActivity.this);
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
        final String email = et_login_Email.getText().toString().trim();
        String password = et_login_Pwd.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Log.d("username",et_login_Email.getText().toString());
            Log.d("password",et_login_Pwd.getText().toString());

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
                                id = jsonArray.getJSONObject(0).getInt("id");
                                usertype = jsonArray.getJSONObject(0).getString("usertype");

                                if(id>0)
                                {
                                    switch (usertype.charAt(0))
                                    {
                                        case 'A':
                                            session.setid(id);
                                            session.setUsername(email);
                                            session.setUsertype("A");
                                            startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                                            finish();
                                            break;
                                        case 'S':
                                            session.setid(id);
                                            session.setUsername(email);
                                            session.setUsertype("S");
                                            startActivity(new Intent(LoginActivity.this,StudentActivity.class));
                                            finish();
                                            break;
                                        case 'R':
                                            session.setid(id);
                                            session.setUsername(email);
                                            session.setUsertype("R");
                                            startActivity(new Intent(LoginActivity.this,RectorActivity.class));
                                            finish();
                                            break;
                                        case 'C':
                                            session.setid(id);
                                            session.setUsername(email);
                                            session.setUsertype("C");
                                            startActivity(new Intent(LoginActivity.this,ContractorActivity.class));
                                            finish();
                                            break;
                                    }
                                }
                                else
                                    Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
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
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);
        }
    }
}
