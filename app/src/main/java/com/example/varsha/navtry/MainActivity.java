package com.example.varsha.navtry;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView=null;
    Toolbar toolbar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager=getFragmentManager();

        if (id == R.id.nav_first_layout)
        {
            FirstFragment fragment= new FirstFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_second_layout)
        {
            SecondFragment fragment= new SecondFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_third_layout)
        {

            ThirdFragment fragment= new ThirdFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_fourth_layout)
        {
            FourthFragment fragment= new FourthFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_fifth_layout)
        {
            FifthFragment fragment= new FifthFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_sixth_layout)
        {
            SixthFragment fragment= new SixthFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
