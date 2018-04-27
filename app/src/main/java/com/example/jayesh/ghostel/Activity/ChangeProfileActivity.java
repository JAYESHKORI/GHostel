package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.example.jayesh.ghostel.Utils.Const;
import com.example.jayesh.ghostel.Utils.RealPathUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChangeProfileActivity extends AppCompatActivity {

    private ImageView iv_dp;
    private Button btn_update;
    private Session session;

    private Bitmap bitmap;
    private final int IMG_REQUEST = 1;
    private String extension=".png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        session = new Session(ChangeProfileActivity.this);
        iv_dp = (ImageView)findViewById(R.id.iv_dp);
        iv_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        Glide.with(ChangeProfileActivity.this).load(Const.API_URL+session.getImgURL()).into(iv_dp);

        btn_update = (Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    private void updateProfile()
    {
        final ProgressDialog progressDialog = new ProgressDialog(ChangeProfileActivity.this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        Log.d("username",session.getUsername());
        Log.d("username",session.getUsertype());
        Log.d("dp",imageToString(bitmap));
        Log.d("extension",extension);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_CHANGEPROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(ChangeProfileActivity.this,response,Toast.LENGTH_LONG).show();
                        finish();
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
                params.put("username",session.getUsername());
                params.put("usertype",session.getUsertype());
                params.put("dp",imageToString(bitmap));
                params.put("extension",extension);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ChangeProfileActivity.this);
        requestQueue.add(stringRequest);
    }

    private  void selectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null)
        {
            Uri path = data.getData();
            String realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());
            extension =  realPath.substring(realPath.lastIndexOf("."));
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                iv_dp.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
