package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.settings:
                break;
            case R.id.logout:
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
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH").format(new Date());
        String qr = "";
        String title = "";
        switch (view.getId()) {
            case R.id.btn_breakfast:
                if (Integer.parseInt(time) < 7) {
                    qr = date + session.getid() + session.getUsername() + "B";
                    title = "Breakfast";
                    saveQR(qr,title);
                } else
                    Toast.makeText(StudentActivity.this, "Contact Your Mess Contractor", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_lunch:
                if (Integer.parseInt(time) < 9) {
                    qr = date + session.getid() + session.getUsername() + "L";
                    title = "Lunch";
                    saveQR(qr,title);
                } else
                    Toast.makeText(StudentActivity.this, "Contact Your Mess Contractor", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dinner: {
                if (Integer.parseInt(time) < 18) {
                    qr = date + session.getid() + session.getUsername() + "D";
                    title = "Dinner";
                    saveQR(qr,title);
                } else
                    Toast.makeText(StudentActivity.this, "Contact Your Mess Contractor", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void saveQR(final String qr, final String title)
    {
        final ProgressDialog progressDialog = new ProgressDialog(StudentActivity.this);
        progressDialog.setMessage("Generating QR..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_SAVEQR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        startActivity(new Intent(StudentActivity.this, ShowQRCodeActivity.class).putExtra("title", title)
                                .putExtra("value", qr));
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
                params.put("code",qr);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(StudentActivity.this);
        requestQueue.add(stringRequest);

    }
}
