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
import com.example.jayesh.ghostel.Activity.AddNewRoom;
import com.example.jayesh.ghostel.Adapter.RoomListAdapter;
import com.example.jayesh.ghostel.Model.RoomListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jayesh on 8/4/18.
 */

public class LoadRoomList extends Fragment
{
    private RecyclerView rv_block_list;
    private RecyclerView.Adapter roomListAdapter;
    private ArrayList<RoomListData> roomListDataArrayList;
    private View view;
    private FloatingActionButton fab_add_block;

    private TextView tv_msg;
    private String TAG = "HomeFragment";

    public LoadRoomList() {
        // Required empty public constructor
    }

    public static LoadRoomList newInstance(String param3, String param4) {
        LoadRoomList fragment = new LoadRoomList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.frag_load_blocklist, container, false);
        rv_block_list = (RecyclerView)view.findViewById(R.id.rv_block_list);
        rv_block_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        roomListDataArrayList = new ArrayList<>();
        loadBlockListsData();

        fab_add_block = (FloatingActionButton) view.findViewById(R.id.fab_add_block);
        fab_add_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddNewRoom.class);
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
                + Const.API_ROOMLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("response",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                RoomListData roomListData = new RoomListData(
                                        jsonArray.getJSONObject(j).getInt("roomid"),
                                        jsonArray.getJSONObject(j).getString("roomno"),
                                        jsonArray.getJSONObject(j).getInt("hostelid"),
                                        jsonArray.getJSONObject(j).getString("hostelname"),
                                        jsonArray.getJSONObject(j).getInt("blockid"),
                                        jsonArray.getJSONObject(j).getString("blockname"),
                                        jsonArray.getJSONObject(j).getInt("capacity"),
                                        jsonArray.getJSONObject(j).getInt("current_no")
                                );
                                roomListDataArrayList.add(roomListData);
                            }
                            roomListAdapter = new RoomListAdapter(getContext(),roomListDataArrayList);
                            rv_block_list.setAdapter(roomListAdapter);
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

