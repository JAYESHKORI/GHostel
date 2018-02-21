package com.example.jayesh.ghostel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jayesh.ghostel.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_reg_Email, et_reg_Pwd;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_reg_Email = (EditText) findViewById(R.id.et_reg_Email);
        et_reg_Pwd = (EditText) findViewById(R.id.et_reg_Pwd);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvLogin = (TextView) findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRegister) {
            registerUser();
        }
        if (view == tvLogin) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void registerUser() {

    }
}