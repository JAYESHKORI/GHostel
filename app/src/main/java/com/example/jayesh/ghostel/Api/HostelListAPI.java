package com.example.jayesh.ghostel.Api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.jayesh.ghostel.Model.HostelListData;
import com.example.jayesh.ghostel.Utils.ApiResponse;
import com.example.jayesh.ghostel.Utils.Const;
import com.example.jayesh.ghostel.Utils.ResponseListener;
import com.example.jayesh.ghostel.Utils.WebInterface;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jayesh on 13/2/18.
 */

public class HostelListAPI extends AsyncTask<Void, Void, Integer> {
    public Context caller;
    public ResponseListener handler;
    public int result=-1;

    public String Tag = "HostelListAPI";

    private ArrayList<HostelListData> hostelListDataArrayList;

    public HostelListAPI(Context context,ResponseListener responseListener) {
        this.caller = context;
        this.handler = responseListener;
    }

    protected void onPreExecute() {

    }

    protected Integer doInBackground(Void... arg0) {
        ApiResponse apiResponse = null;

        try {

            apiResponse = WebInterface.doGet(Const.API_URL
                    + Const.API_HOSTELLIST);

            result = apiResponse.code;

            if (apiResponse.code == 200) {
                if (apiResponse.response != null
                        && !apiResponse.response.equals("")) {
                    this.Parse(apiResponse.response);
                } else {
                    result = -1;
                }
            }

            Log.d(Tag, "User Story Json Response :----->" + apiResponse.response);

        } catch (UnknownHostException ue) {
            result = -2;
            ue.printStackTrace();
        } catch (Exception e) {
            result = -3;
            e.printStackTrace();
        }
        return result;
    }

    protected void onPostExecute(Integer result) {
        this.handler.onResponce(Const.API_HOSTELLIST,
                Const.API_RESULT.SUCCESS, hostelListDataArrayList);
    }

    public void Parse(String response) {
//        try {
//            if (response!=null)
//                Log.e("Response",response);
//            else
//                Log.e("Response","Null");
//            JSONArray jsonArray = null;
//            hostelListDataArrayList = new ArrayList<HostelListData>();
//            jsonArray = new JSONArray(response);
//            for (int j = 0; j < jsonArray.length(); j++) {
//                HostelListData hostelListData = new HostelListData();
//                hostelListData.setId(Integer.parseInt(jsonArray.getJSONObject(j).getString("id")));
//                hostelListData.setName(jsonArray.getJSONObject(j).getString("name"));
//                hostelListData.setDescription(jsonArray.getJSONObject(j).getString("description"));
//                hostelListData.setType(jsonArray.getJSONObject(j).getString("type"));
//
//                hostelListDataArrayList.add(hostelListData);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}



