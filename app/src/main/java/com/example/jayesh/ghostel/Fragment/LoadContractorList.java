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
import com.example.jayesh.ghostel.Activity.AddNewContractor;
import com.example.jayesh.ghostel.Activity.AddNewRector;
import com.example.jayesh.ghostel.Adapter.CommonAdapter;
import com.example.jayesh.ghostel.Model.CommonData;
import com.example.jayesh.ghostel.Model.HostelListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jayesh on 28/2/18.
 */

public class LoadContractorList extends Fragment
{
    private static final String ARG_PARAM7 = "param7";
    private static final String ARG_PARAM8 = "param8";

    private View view;

    private String mParam7;
    private String mParam8;

    private RecyclerView rv_contractorList;
    private RecyclerView.Adapter commonAdapter;
    private ArrayList<CommonData> commonDataArrayList;
    private FloatingActionButton fab_add_contractor;

    public LoadContractorList() {
        // Required empty public constructor
    }

    public static LoadContractorList newInstance(String param7, String param8) {
        LoadContractorList fragment = new LoadContractorList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM7, param7);
        args.putString(ARG_PARAM8, param8);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam7 = getArguments().getString(ARG_PARAM7);
            mParam8 = getArguments().getString(ARG_PARAM8);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.frag_load_contractorlist, container, false);
        rv_contractorList = (RecyclerView)view.findViewById(R.id.rv_contractor_list);
        rv_contractorList.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonDataArrayList = new ArrayList<>();
        loadContractorList();

        fab_add_contractor = (FloatingActionButton) view.findViewById(R.id.fab_add_contractor);
        fab_add_contractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddNewContractor.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void loadContractorList()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_CONTRACTORLIST,
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
                                        jsonArray.getJSONObject(j).getInt("contractorid"),
                                        jsonArray.getJSONObject(j).getString("first_name"),
                                        jsonArray.getJSONObject(j).getString("last_name"),
                                        jsonArray.getJSONObject(j).getInt("hostelid"),
                                        jsonArray.getJSONObject(j).getInt("blockid"),
                                        jsonArray.getJSONObject(j).getString("hostelname"),
                                        jsonArray.getJSONObject(j).getString("blockname")
                                );
                                commonDataArrayList.add(commonData);
                            }
                            commonAdapter = new CommonAdapter(getContext(),commonDataArrayList);
                            rv_contractorList.setAdapter(commonAdapter);
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

