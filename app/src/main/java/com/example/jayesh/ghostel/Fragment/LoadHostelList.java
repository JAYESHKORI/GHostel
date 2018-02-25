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
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.Adapter.HostelListAdapter;
import com.example.jayesh.ghostel.Activity.AddNewHostel;
import com.example.jayesh.ghostel.Model.HostelListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jayesh on 12/2/18.
 */

public class LoadHostelList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView rv_hostel_list;
    private RecyclerView.Adapter hostelListAdapter;
    private ArrayList<HostelListData> hostelListDataArrayList;
    private View view;
    private FloatingActionButton fab_add_hostel;

    private TextView tv_msg;
    private String TAG = "HomeFragment";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoadHostelList() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoadHostelList newInstance(String param1, String param2) {
        LoadHostelList fragment = new LoadHostelList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.frag_load_hostellist, container, false);
        rv_hostel_list = (RecyclerView)view.findViewById(R.id.rv_hostel_list);
        rv_hostel_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        hostelListDataArrayList = new ArrayList<>();
        loadHostelListsData();

        fab_add_hostel = (FloatingActionButton) view.findViewById(R.id.fab_add_hostel);
        fab_add_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddNewHostel.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void loadHostelListsData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_HOSTELLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("response",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                HostelListData hostelListData = new HostelListData(
                                        jsonArray.getJSONObject(j).getInt("id"),
                                        jsonArray.getJSONObject(j).getString("name"),
                                        jsonArray.getJSONObject(j).getString("description"),
                                        jsonArray.getJSONObject(j).getString("type")
                                );
                                hostelListDataArrayList.add(hostelListData);
                            }
                            hostelListAdapter = new HostelListAdapter(getContext(),hostelListDataArrayList);
                            rv_hostel_list.setAdapter(hostelListAdapter);
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

