package com.example.jayesh.ghostel.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayesh on 7/4/18.
 */

public class RaiseComplain extends Fragment implements View.OnClickListener {
    private View view;
    private EditText et_title,et_desc;
    private Button btn_submit;
    private Session session;

    public RaiseComplain() {

    }


    public static RaiseComplain newInstance(String param1, String param2) {
        RaiseComplain fragment = new RaiseComplain();
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

        view =  inflater.inflate(R.layout.frag_raise_complain, container, false);

        et_title = (EditText)view.findViewById(R.id.et_title);
        et_desc = (EditText)view.findViewById(R.id.et_desc);
        btn_submit = (Button)view.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        addComplain();
    }

    private void addComplain()
    {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Registering your complain...");
            progressDialog.show();

            if (et_title.getText().toString().equals(""))
                Toast.makeText(getContext(),"Title is required",Toast.LENGTH_LONG).show();
            else if (et_desc.getText().toString().equals(""))
                Toast.makeText(getContext(),"Description is required",Toast.LENGTH_LONG).show();
            else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                        + Const.API_REGCOMPLAIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Log.e("Error", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("studentid", String.valueOf(session.getid()));
                        params.put("title", et_title.getText().toString());
                        params.put("detail", et_desc.getText().toString());
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequest);
            }
    }
}
