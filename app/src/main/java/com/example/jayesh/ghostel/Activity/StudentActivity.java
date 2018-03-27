package com.example.jayesh.ghostel.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_breakfast,btn_lunch,btn_dinner;
    private TextView tv_contact_contractor;
    private Session session;

    public static Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        session = new Session(StudentActivity.this);

        btn_breakfast = (Button)findViewById(R.id.btn_breakfast);
        btn_breakfast.setOnClickListener(this);
        btn_lunch = (Button)findViewById(R.id.btn_lunch);
        btn_lunch.setOnClickListener(this);
        btn_dinner = (Button)findViewById(R.id.btn_dinner);
        btn_dinner.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.student_settings:
                break;
            case R.id.student_logout:
                session.setid(0);
                session.setUsername("");
                session.setUsertype("X");
                startActivity(new Intent(StudentActivity.this,MainActivity.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH").format(new Date());
        switch (view.getId())
        {
            case R.id.btn_breakfast:
                if(Integer.parseInt(time)<7)
                startActivity(new Intent(StudentActivity.this,ShowQRCodeActivity.class).putExtra("title","Breakfast")
                        .putExtra("value",date+session.getid()+session.getUsername()+"B"));
                else
                    Toast.makeText(StudentActivity.this,"Contact Your Mess Contractor",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_lunch:
                if(Integer.parseInt(time)<9)
                startActivity(new Intent(StudentActivity.this,ShowQRCodeActivity.class).putExtra("title","Lunch")
                        .putExtra("value",date+session.getid()+session.getUsername()+"L"));
                else
                    Toast.makeText(StudentActivity.this,"Contact Your Mess Contractor",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dinner:
                if(Integer.parseInt(time)<18)
                startActivity(new Intent(StudentActivity.this,ShowQRCodeActivity.class).putExtra("title","Dinner")
                        .putExtra("value",date+session.getid()+session.getUsername()+"D"));
                else
                    Toast.makeText(StudentActivity.this,"Contact Your Mess Contractor",Toast.LENGTH_SHORT).show();
                break;
        }

    }


}
