package com.example.mju.embeded;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

/**
 * Copyright (C) 컴퓨터공학과 60112320 김동빈
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Main_ViewpagerAdapter mViewpagerAdapter;
    private ViewPager mViewPager;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // navigation bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navView);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        // viewpager & tabs
        mViewpagerAdapter = new Main_ViewpagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        mViewPager.setAdapter(mViewpagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // action bar & floating action button
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // menu 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // menu 아이템 선택시 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_search:
                Intent search_intent = new Intent(this, SearchResult.class);
                startActivity(search_intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // navigation menu
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // navigation menu 클릭시 action
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void onClickLogin(View view) {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    public void onClickHome(View view){
        mDrawerLayout.closeDrawers();
    }
    public void onClickSetting(View view){
        //Intent home_intent = new Intent(this,MainActivity.class);
        //startActivity(home_intent);
    }
    public void onClickClose(View view){
        mDrawerLayout.closeDrawers();
    }

}
