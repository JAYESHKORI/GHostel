package com.example.jayesh.ghostel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jayesh.ghostel.R;

public class AddNewHostel extends AppCompatActivity {

    private EditText et_hostel_name,et_desc;
    private RadioButton rb_boys,rb_girls,rb_both;
    private Button btn_create_hostel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_hostel);

        et_hostel_name = (EditText) findViewById(R.id.et_hostel_name);
        et_desc = (EditText) findViewById(R.id.et_desc);
        rb_boys = (RadioButton)findViewById(R.id.rb_boys);
        rb_girls = (RadioButton)findViewById(R.id.rb_girls);
        rb_both = (RadioButton)findViewById(R.id.rb_both);
        btn_create_hostel = (Button)findViewById(R.id.btn_create_hostel);
        btn_create_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createHostel();
            }
        });
    }

    private void createHostel()
    {
        Toast.makeText(getApplicationContext(),et_hostel_name.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),et_desc.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),et_hostel_name.toString(),Toast.LENGTH_SHORT).show();
    }
}
