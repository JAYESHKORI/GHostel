package com.example.jayesh.ghostel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jayesh.ghostel.R;

public class ViewComplainActivity extends AppCompatActivity {

    private TextView tv_complainid,tv_title,tv_detail,tv_hostelid,tv_hostelname,tv_blockid,tv_blockname,tv_roomid,
            tv_roomno,tv_studentid,tv_studentname,tv_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complain);

        tv_complainid = (TextView)findViewById(R.id.tv_complainid);
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_detail = (TextView)findViewById(R.id.tv_detail);
        tv_hostelid = (TextView)findViewById(R.id.tv_hostelid);
        tv_hostelname = (TextView)findViewById(R.id.tv_hostelname);
        tv_blockid = (TextView)findViewById(R.id.tv_blockid);
        tv_blockname = (TextView)findViewById(R.id.tv_blockname);
        tv_roomid = (TextView)findViewById(R.id.tv_roomid);
        tv_roomno = (TextView)findViewById(R.id.tv_roomno);
        tv_studentid = (TextView)findViewById(R.id.tv_studentid);
        tv_studentname = (TextView)findViewById(R.id.tv_studentname);
        tv_status = (TextView)findViewById(R.id.tv_status);

        tv_complainid.setText(getIntent().getExtras().getString("complainid"));
        tv_title.setText(getIntent().getExtras().getString("title"));
        tv_detail.setText("Detail "+getIntent().getExtras().getString("detail"));
        tv_hostelid.setText(getIntent().getExtras().getString("hostelid"));
        tv_hostelname.setText("Hostelname "+getIntent().getExtras().getString("hostelname"));
        tv_blockid.setText(getIntent().getExtras().getString("blockid"));
        tv_blockname.setText("Blockname "+getIntent().getExtras().getString("blockname"));
        tv_roomid.setText(getIntent().getExtras().getString("roomid"));
        tv_roomno.setText("Roomno "+getIntent().getExtras().getString("roomno"));
        tv_studentid.setText(getIntent().getExtras().getString("studentid"));
        tv_studentname.setText("By "+getIntent().getExtras().getString("name"));
        tv_status.setText(getIntent().getExtras().getString("status"));
    }
}
