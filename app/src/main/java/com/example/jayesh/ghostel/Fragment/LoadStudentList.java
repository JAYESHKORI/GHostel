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
import com.example.jayesh.ghostel.Activity.AddNewStudent;
import com.example.jayesh.ghostel.Adapter.StudentListAdapter;
import com.example.jayesh.ghostel.Model.CommonData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jayesh on 2/3/18.
 */

public class LoadStudentList extends Fragment
{
    private static final String ARG_PARAM9 = "param9";
    private static final String ARG_PARAM10 = "param10";

    private View view;

    private String mParam9;
    private String mParam10;

    private RecyclerView rv_studentList;
    private RecyclerView.Adapter commonAdapter;
    private ArrayList<CommonData> commonDataArrayList;
    private FloatingActionButton fab_add_student;

    public LoadStudentList() {
        // Required empty public constructor
    }

    public static LoadContractorList newInstance(String param9, String param10) {
        LoadContractorList fragment = new LoadContractorList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM9, param9);
        args.putString(ARG_PARAM10, param10);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam9 = getArguments().getString(ARG_PARAM9);
            mParam10 = getArguments().getString(ARG_PARAM10);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.frag_load_studentlist, container, false);
        rv_studentList = (RecyclerView)view.findViewById(R.id.rv_student_list);
        rv_studentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonDataArrayList = new ArrayList<>();
        loadStudentList();

        fab_add_student = (FloatingActionButton) view.findViewById(R.id.fab_add_student);
        fab_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddNewStudent.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void loadStudentList()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_STUDENTLIST,
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
                                        jsonArray.getJSONObject(j).getInt("studentid"),
                                        jsonArray.getJSONObject(j).getString("first_name"),
                                        jsonArray.getJSONObject(j).getString("last_name"),
                                        jsonArray.getJSONObject(j).getInt("hostelid"),
                                        jsonArray.getJSONObject(j).getInt("blockid"),
                                        jsonArray.getJSONObject(j).getString("hostelname"),
                                        jsonArray.getJSONObject(j).getString("blockname")
                                );
                                commonDataArrayList.add(commonData);
                            }
                            commonAdapter = new StudentListAdapter(getContext(),commonDataArrayList);
                            rv_studentList.setAdapter(commonAdapter);
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


