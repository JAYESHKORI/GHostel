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
import com.example.jayesh.ghostel.Activity.AddBlock;
import com.example.jayesh.ghostel.Adapter.BlockListAdapter;
import com.example.jayesh.ghostel.Model.BlockListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jayesh on 19/2/18.
 */

public class BlockList extends Fragment
{
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    private RecyclerView rv_block_list;
    private RecyclerView.Adapter blockListAdapter;
    private ArrayList<BlockListData> blockListDataArrayList;
    private View view;
    private FloatingActionButton fab_add_block;

    private TextView tv_msg;
    private String TAG = "HomeFragment";

    private String mParam3;
    private String mParam4;

    public BlockList() {
        // Required empty public constructor
    }

    public static BlockList newInstance(String param3, String param4) {
        BlockList fragment = new BlockList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.frag_load_blocklist, container, false);
        rv_block_list = (RecyclerView)view.findViewById(R.id.rv_block_list);
        rv_block_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        blockListDataArrayList = new ArrayList<>();
        loadBlockListsData();

        fab_add_block = (FloatingActionButton) view.findViewById(R.id.fab_add_block);
        fab_add_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddBlock.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void loadBlockListsData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Const.API_URL
                + Const.API_BLOCKLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("response",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                BlockListData blockListData = new BlockListData(
                                        jsonArray.getJSONObject(j).getInt("blockid"),
                                        jsonArray.getJSONObject(j).getString("bname"),
                                        jsonArray.getJSONObject(j).getInt("hostelid"),
                                        jsonArray.getJSONObject(j).getString("hname"),
                                        jsonArray.getJSONObject(j).getString("type"),
                                        jsonArray.getJSONObject(j).getInt("capacity")
                                );
                                blockListDataArrayList.add(blockListData);
                            }
                            blockListAdapter = new BlockListAdapter(getContext(),blockListDataArrayList);
                            rv_block_list.setAdapter(blockListAdapter);
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
