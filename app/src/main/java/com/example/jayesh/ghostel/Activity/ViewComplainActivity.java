package com.example.jayesh.ghostel.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayesh.ghostel.Adapter.CommentListAdapter;
import com.example.jayesh.ghostel.Model.CommentListData;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.example.jayesh.ghostel.Utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewComplainActivity extends AppCompatActivity {

    private TextView tv_complainid, tv_title, tv_detail, tv_hostelid, tv_hostelname, tv_blockid, tv_blockname, tv_roomid,
            tv_roomno, tv_studentid, tv_studentname, tv_status;
    private Button btn_add_comment;
    private ListView lv_comments;
    private ScrollView scrollView;

    private ArrayList<CommentListData> commentsArrayList = new ArrayList<>();
    private CommentListAdapter commentsAdapter;

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complain);

        tv_complainid = (TextView) findViewById(R.id.tv_complainid);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_detail = (TextView) findViewById(R.id.tv_detail);
        tv_hostelid = (TextView) findViewById(R.id.tv_hostelid);
        tv_hostelname = (TextView) findViewById(R.id.tv_hostelname);
        tv_blockid = (TextView) findViewById(R.id.tv_blockid);
        tv_blockname = (TextView) findViewById(R.id.tv_blockname);
        tv_roomid = (TextView) findViewById(R.id.tv_roomid);
        tv_roomno = (TextView) findViewById(R.id.tv_roomno);
        tv_studentid = (TextView) findViewById(R.id.tv_studentid);
        tv_studentname = (TextView) findViewById(R.id.tv_studentname);
        tv_status = (TextView) findViewById(R.id.tv_status);
        btn_add_comment = (Button) findViewById(R.id.btn_add_comment);
        btn_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        lv_comments = (ListView) findViewById(R.id.lv_comments);
        lv_comments.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

        tv_complainid.setText(getIntent().getExtras().getString("complainid"));
        tv_title.setText(getIntent().getExtras().getString("title"));
        tv_detail.setText(getIntent().getExtras().getString("detail"));
        tv_hostelid.setText(getIntent().getExtras().getString("hostelid"));
        tv_hostelname.setText(getIntent().getExtras().getString("hostelname"));
        tv_blockid.setText(getIntent().getExtras().getString("blockid"));
        tv_blockname.setText(getIntent().getExtras().getString("blockname"));
        tv_roomid.setText(getIntent().getExtras().getString("roomid"));
        tv_roomno.setText(getIntent().getExtras().getString("roomno"));
        tv_studentid.setText(getIntent().getExtras().getString("studentid"));
        tv_studentname.setText(getIntent().getExtras().getString("name"));
        tv_status.setText(getIntent().getExtras().getString("status"));

        session = new Session(this);
        loadComments();
    }

    private void loadComments()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Comments...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                + Const.API_COMMENTLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("comment list",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int j=0;j<jsonArray.length();j++)
                            {
                                CommentListData comment = new CommentListData(
                                        jsonArray.getJSONObject(j).getInt("commentid"),
                                        jsonArray.getJSONObject(j).getString("comment"),
                                        jsonArray.getJSONObject(j).getString("name"),
                                        jsonArray.getJSONObject(j).getString("usertype")
                                );
                                commentsArrayList.add(comment);
                            }
                            commentsAdapter = new CommentListAdapter(commentsArrayList,ViewComplainActivity.this);
                            lv_comments.setAdapter(commentsAdapter);
                            progressDialog.dismiss();
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
                params.put("complainid",String.valueOf(tv_complainid.getText().toString()));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText comment = new EditText(this);
        comment.setMinLines(5);
        alert.setTitle("Add Comment");
        alert.setView(comment);

        alert.setPositiveButton("POST", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (!comment.getText().toString().equals(""))
                    postComment(comment.getText().toString());
                else
                    Toast.makeText(ViewComplainActivity.this, "Write Something.", Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    private void postComment(final String comment)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Posting...");
        progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.API_URL
                    + Const.API_POSTCOMMENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(ViewComplainActivity.this, response, Toast.LENGTH_LONG).show();
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
                    params.put("comment", comment);
                    params.put("username", session.getUsername());
                    params.put("usertype", session.getUsertype());
                    params.put("complainid", tv_complainid.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }
}
