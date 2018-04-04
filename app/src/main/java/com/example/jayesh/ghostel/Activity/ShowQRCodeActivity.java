package com.example.jayesh.ghostel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jayesh.ghostel.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static com.example.jayesh.ghostel.Activity.StudentActivity.bitmap;

public class ShowQRCodeActivity extends AppCompatActivity {

    private TextView tv_qr_title;
    public static ImageView iv_qr;

    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qrcode);

        tv_qr_title = (TextView)findViewById(R.id.tv_qr_title);
        iv_qr = (ImageView)findViewById(R.id.iv_qr);

        tv_qr_title.setText(getIntent().getCharSequenceExtra("title"));
        value = String.valueOf(getIntent().getCharSequenceExtra("value"));

        createQR();

    }

    private void createQR()
    {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(value, BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv_qr.setImageBitmap(bitmap);
        }
        catch (WriterException e)
        {

        }
    }
}
