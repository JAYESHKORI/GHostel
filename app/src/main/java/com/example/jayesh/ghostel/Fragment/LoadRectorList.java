package com.example.jayesh.ghostel.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jayesh.ghostel.Activity.AddNewRector;
import com.example.jayesh.ghostel.R;

/**
 * Created by jayesh on 24/2/18.
 */

public class LoadRectorList extends Fragment
{
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";

    private View view;

    private String mParam5;
    private String mParam6;

    private FloatingActionButton fab_add_rector;

    public LoadRectorList() {
        // Required empty public constructor
    }

    public static LoadRectorList newInstance(String param5, String param6) {
        LoadRectorList fragment = new LoadRectorList();
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

        fab_add_rector = (FloatingActionButton) view.findViewById(R.id.fab_add_rector);
        fab_add_rector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddNewRector.class);
                startActivity(i);
            }
        });
        return view;
    }
}
