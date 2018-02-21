package com.example.jayesh.ghostel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jayesh.ghostel.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_login_Email,et_login_Pwd;
    private Button btnLogin;
    private TextView tvSignup;

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
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please provide username and password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.equals("admin")&&password.equals("admin"))
        {
            startActivity(new Intent(this,AdminActivity.class));
            finish();
        }
        else
        {

        }
    }
}
