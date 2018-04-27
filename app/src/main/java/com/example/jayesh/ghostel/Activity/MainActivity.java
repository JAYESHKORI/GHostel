package com.example.jayesh.ghostel.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.karan.churi.PermissionManager.PermissionManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tv_granted,tv_denied;
    Session session;

    PermissionManager permissionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(MainActivity.this);
        tv_granted = (TextView)findViewById(R.id.tv_granted);
        tv_denied = (TextView)findViewById(R.id.tv_denied);

        permissionManager = new PermissionManager() {};
        if(permissionManager.checkAndRequestPermissions(this))
        {
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
                startActivity(new Intent(MainActivity.this,LoginActivity.class));finish();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionManager.checkResult(requestCode,permissions,grantResults);
        ArrayList<String> granted = permissionManager.getStatus().get(0).granted;
        ArrayList<String> denied = permissionManager.getStatus().get(0).denied;

        if(granted.size()==5)
        {
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
                startActivity(new Intent(MainActivity.this,LoginActivity.class));finish();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(permissionManager.checkAndRequestPermissions(this))
        {
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
                startActivity(new Intent(MainActivity.this,LoginActivity.class));finish();
            }
        }
    }
}
