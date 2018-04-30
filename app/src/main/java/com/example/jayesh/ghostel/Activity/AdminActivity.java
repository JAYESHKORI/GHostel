package com.example.jayesh.ghostel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jayesh.ghostel.Fragment.BlockList;
import com.example.jayesh.ghostel.Fragment.ComplainList;
import com.example.jayesh.ghostel.Fragment.ContractorList;
import com.example.jayesh.ghostel.Fragment.HostelList;
import com.example.jayesh.ghostel.Fragment.RectorList;
import com.example.jayesh.ghostel.Fragment.RoomList;
import com.example.jayesh.ghostel.Fragment.StudentList;
import com.example.jayesh.ghostel.R;
import com.example.jayesh.ghostel.SharedPrefrences.Session;
import com.example.jayesh.ghostel.Utils.Const;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Session session;
    private ImageView iv_dp;
    private TextView tv_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.screen_area,new HostelList());
        tx.commit();


        session = new Session(AdminActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        tv_username = (TextView)hView.findViewById(R.id.tv_username);
        iv_dp = (ImageView) hView.findViewById(R.id.iv_dp);

        tv_username.setText(session.getUsername());
        Glide.with(AdminActivity.this).load(Const.API_URL+session.getImgURL()).into(iv_dp);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        Fragment fragment =null;

        if (id == R.id.nav_hostel) {
            fragment = new HostelList();
        } else if (id == R.id.nav_blocks) {
            fragment = new BlockList();
        } else if (id == R.id.nav_rooms) {
            fragment = new RoomList();
        }else if (id == R.id.nav_rectors) {
            fragment = new RectorList();
        } else if (id == R.id.nav_mess) {
            fragment = new ContractorList();
        } else if (id == R.id.nav_student) {
            fragment = new StudentList();
        } else if (id == R.id.nav_complains) {
            fragment = new ComplainList();
        }
        if (fragment!=null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area,fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.chngdp:
                startActivity(new Intent(AdminActivity.this,ChangeProfileActivity.class));
                break;
            case R.id.chngpwd:
                startActivity(new Intent(AdminActivity.this,ChangePasswordActivity.class));
                break;
            case R.id.settings:
                break;
            case R.id.logout:
                session.setid(0);
                session.setUsername("");
                session.setUsertype("X");
                session.setImgURL("X");
                session.sethostelid(-1);
                session.setblockid(-1);
                startActivity(new Intent(AdminActivity.this,MainActivity.class));
                finishAffinity();
                break;
        }
        return true;
    }
}
