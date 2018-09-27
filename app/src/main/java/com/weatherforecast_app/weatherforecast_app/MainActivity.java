package com.weatherforecast_app.weatherforecast_app;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceManager;
import android.util.SparseIntArray;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private SparseIntArray mErrorString = new SparseIntArray();
    private static final int REQUEST_PERMISSIONS = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        requestAppPermissions(new String[]{
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, R.string.runtime_permissions_txt
                , REQUEST_PERMISSIONS);
        displayView(R.id.nav_daily_forecast);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Quit Weather Forecast")
                    .setMessage("Are you sure you want to quit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displayView(id);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String cityName = sharedPref.getString("pref_key_city_name", "");

        switch (viewId) {
            case R.id.nav_daily_forecast:
                fragment = DailyFragment.newInstance(cityName);
                title = "Daily";
                break;
            case R.id.nav_weekly_forecast:
                fragment = WeeklyFragment.newInstance(cityName);
                title = "Weekly";
                break;
            case  R.id.nav_settings:
                fragment = new SettingsFragment();
                title = "Settings";
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
