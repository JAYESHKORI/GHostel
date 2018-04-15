package com.example.jayesh.ghostel.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.Activity.AddRector;
import com.example.jayesh.ghostel.Adapter.RectorListAdapter;
import com.example.jayesh.ghostel.Model.CommonData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jayesh on 24/2/18.
 */

public class RectorList extends Fragment
{
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";

    private View view;

    private String mParam5;
    private String mParam6;

    private RecyclerView rv_rectorList;
    private RecyclerView.Adapter commonAdapter;
    private ArrayList<CommonData> commonDataArrayList;
    private FloatingActionButton fab_add_rector;

    public RectorList() {
        // Required empty public constructor
    }

    public static RectorList newInstance(String param5, String param6) {
        RectorList fragment = new RectorList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM5, param5);
        args.putString(ARG_PARAM6, param6);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam5 = getArguments().getString(ARG_PARAM5);
            mParam6 = getArguments().getString(ARG_PARAM6);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.frag_load_rectorlist, container, false);
        rv_rectorList = (RecyclerView)view.findViewById(R.id.rv_rector_list);
        rv_rectorList.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonDataArrayList = new ArrayList<>();
        loadRectorList();

        fab_add_rector = (FloatingActionButton) view.findViewById(R.id.fab_add_rector);
        fab_add_rector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddRector.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void loadRectorList()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_RECTORLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("response",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                CommonData commonData = new CommonData(
                                        jsonArray.getJSONObject(j).getInt("rectorid"),
                                        jsonArray.getJSONObject(j).getString("first_name"),
                                        jsonArray.getJSONObject(j).getString("last_name"),
                                        jsonArray.getJSONObject(j).getInt("hostelid"),
                                        jsonArray.getJSONObject(j).getInt("blockid"),
                                        jsonArray.getJSONObject(j).getString("hostelname"),
                                        jsonArray.getJSONObject(j).getString("blockname")
                                );
                                commonDataArrayList.add(commonData);
                                Log.d("al",commonDataArrayList.toString());
                            }
                            commonAdapter = new RectorListAdapter(getContext(),commonDataArrayList);
                            rv_rectorList.setAdapter(commonAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.e("Error",error.toString());
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
