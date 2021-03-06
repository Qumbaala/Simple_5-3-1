package com.crookedqueue.simple531.View;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.NavigationPresenter;
import com.crookedqueue.simple531.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RecordsActivity extends AppCompatActivity implements FragmentInterractor {
    @Bind(R.id.fragment_frame_records_activity)
    FrameLayout fragFrame;
    @Bind(R.id.drawer_layout_records)
    DrawerLayout drawer;
    @Bind(R.id.nav_view_records)
    NavigationView navView;
    @Bind(R.id.toolbar_records)
    Toolbar toolbar;
    @Bind(R.id.coordinator_records)
    CoordinatorLayout coordinator;
    NavigationPresenter navPresenter;
    private static final String TOOLBAR_LABEL = "Records";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        ButterKnife.bind(this);
        toolbar.setTitle(TOOLBAR_LABEL);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navPresenter = new NavigationPresenter(this, drawer);
        navView.setNavigationItemSelectedListener(navPresenter);
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(fragFrame.getId(), new PersonalRecordFragment()).commit();

        BottomNavigationView bottomBar = (BottomNavigationView) findViewById(R.id.bottom_bar_records);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_personal_records:
                        fm.beginTransaction().replace(R.id.fragment_frame_records_activity, new PersonalRecordFragment()).commit();
                        break;
                    case R.id.menu_maxes:
                        fm.beginTransaction().replace(R.id.fragment_frame_records_activity, new MaxListFragment()).commit();
                        break;
                    case R.id.menu_save_file_records:
                        fm.beginTransaction().replace(R.id.fragment_frame_records_activity, new ExportRecordsFragment()).commit();
                }
                return true;
            }
        });
    }

    @Override
    public void setToolbarTitle(String s) {
        toolbar.setTitle(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navPresenter = null;
        ButterKnife.unbind(this);
    }
}
