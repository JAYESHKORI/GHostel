package com.example.jayesh.ghostel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;

public class MainActivity extends AppCompatActivity {

    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(MainActivity.this);
        Log.d("id",String.valueOf(session.getid()));
        if(session.getid()!=0)
        {
            if (session.getUsertype().charAt(0)=='S'){
                startActivity(new Intent(MainActivity.this,StudentActivity.class));finish();
            }
            if (session.getUsertype().charAt(0)=='R'){
                startActivity(new Intent(MainActivity.this,RectorActivity.class));finish();
            }
            if (session.getUsertype().charAt(0)=='C'){
                startActivity(new Intent(MainActivity.this,ContractorActivity.class));finish();
            }
            if (session.getUsertype().charAt(0)=='A'){
                startActivity(new Intent(MainActivity.this,AdminActivity.class));finish();
            }
        }
        else
        {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
    }
}
