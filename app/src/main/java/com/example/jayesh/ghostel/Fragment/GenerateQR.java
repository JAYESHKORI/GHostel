package com.example.jayesh.ghostel.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.Activity.AddNewHostel;
import com.example.jayesh.ghostel.Activity.ShowQRCodeActivity;
import com.example.jayesh.ghostel.Adapter.HostelListAdapter;
import com.example.jayesh.ghostel.Model.HostelListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by jayesh on 7/4/18.
 */

public class GenerateQR extends Fragment implements View.OnClickListener{

    private View view;
    private Button btn_breakfast,btn_lunch,btn_dinner;
    private TextView tv_contact_contractor;
    private Session session;

    public static Bitmap bitmap;

    public GenerateQR() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GenerateQR newInstance(String param1, String param2) {
        GenerateQR fragment = new GenerateQR();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        session = new Session(getContext());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.frag_generate_qr, container, false);
        btn_breakfast = (Button)view.findViewById(R.id.btn_breakfast);
        btn_breakfast.setOnClickListener(this);
        btn_lunch = (Button)view.findViewById(R.id.btn_lunch);
        btn_lunch.setOnClickListener(this);
        btn_dinner = (Button)view.findViewById(R.id.btn_dinner);
        btn_dinner.setOnClickListener(this);

        return view;
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
                    Toast.makeText(getContext(), "Contact Your Mess Contractor", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_lunch:
                if (Integer.parseInt(time) < 9) {
                    qr = date + session.getid() + session.getUsername() + "L";
                    title = "Lunch";
                    saveQR(qr,title);
                } else
                    Toast.makeText(getContext(), "Contact Your Mess Contractor", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dinner: {
                if (Integer.parseInt(time) < 18) {
                    qr = date + session.getid() + session.getUsername() + "D";
                    title = "Dinner";
                    saveQR(qr,title);
                } else
                    Toast.makeText(getContext(), "Contact Your Mess Contractor", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void saveQR(final String qr, final String title)
    {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Generating QR..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_SAVEQR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getContext(), ShowQRCodeActivity.class).putExtra("title", title)
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}


