package com.example.jayesh.ghostel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jayesh.ghostel.R;

import static com.example.jayesh.ghostel.Activity.StudentActivity.bitmap;

public class ShowQRCodeActivity extends AppCompatActivity {

    private TextView tv_qr_title;
    public static ImageView iv_qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qrcode);

        tv_qr_title = (TextView)findViewById(R.id.tv_qr_title);
        tv_qr_title.setText(getIntent().getCharSequenceExtra("title"));

        iv_qr = (ImageView)findViewById(R.id.iv_qr);
        iv_qr.setImageBitmap(bitmap);
    }
}
