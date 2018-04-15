package com.example.jayesh.ghostel.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.example.jayesh.ghostel.Adapter.ComplainListAdapter;
import com.example.jayesh.ghostel.Model.ComplainListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayesh on 8/4/18.
 */

public class ComplainList extends Fragment
{
    private RecyclerView rv_complain_list;
    private RecyclerView.Adapter complainListAdapter;
    private ArrayList<ComplainListData> complainListDataArrayList;
    private View view;

    private static final String ARG_PARAM11 = "param11";
    private static final String ARG_PARAM12 = "param12";

    private TextView tv_msg;

    private String mParam11;
    private String mParam12;

    private Session session;

    public ComplainList() {
        // Required empty public constructor
    }

    public static ComplainList newInstance(String param11, String param12) {
        ComplainList fragment = new ComplainList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM11, param11);
        args.putString(ARG_PARAM12, param12);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam11 = getArguments().getString(ARG_PARAM11);
            mParam12 = getArguments().getString(ARG_PARAM12);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        session = new Session(getContext());
        view =  inflater.inflate(R.layout.frag_load_complains, container, false);
        rv_complain_list = (RecyclerView)view.findViewById(R.id.rv_complain_list);
        rv_complain_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        complainListDataArrayList = new ArrayList<>();
        loadComplainListsData();
        return view;
    }

    private void loadComplainListsData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_COMPLAINLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("complain list",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                ComplainListData complainListData = new ComplainListData(
                                        jsonArray.getJSONObject(j).getInt("complainid"),
                                        jsonArray.getJSONObject(j).getString("title"),
                                        jsonArray.getJSONObject(j).getString("detail"),
                                        jsonArray.getJSONObject(j).getInt("hostelid"),
                                        jsonArray.getJSONObject(j).getString("hostelname"),
                                        jsonArray.getJSONObject(j).getInt("blockid"),
                                        jsonArray.getJSONObject(j).getString("blockname"),
                                        jsonArray.getJSONObject(j).getInt("roomid"),
                                        jsonArray.getJSONObject(j).getString("roomno"),
                                        jsonArray.getJSONObject(j).getInt("studentid"),
                                        jsonArray.getJSONObject(j).getString("name"),
                                        jsonArray.getJSONObject(j).getInt("status"));
                                complainListDataArrayList.add(complainListData);
                            }
                            complainListAdapter = new ComplainListAdapter(getContext(),complainListDataArrayList);
                            rv_complain_list.setAdapter(complainListAdapter);
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
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usertype",session.getUsertype());
                params.put("userid",String.valueOf(session.getid()));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
