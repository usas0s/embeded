package com.example.mju.embeded;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Copyright (C) 컴퓨터공학과 60112320 김동빈
 */

public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Main_ViewpagerAdapter mViewpagerAdapter;
    private ViewPager mViewPager;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent intent = getIntent();

        // navigation bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // action bar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            mDrawerLayout.setDrawerListener(mToggle);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navView);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


        // viewpager
        mViewpagerAdapter = new Main_ViewpagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            mViewPager.setAdapter(mViewpagerAdapter);
        }

        // tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regist_intent = new Intent(getApplicationContext(),RegisterPost.class);
                startActivity(regist_intent);
            }
        });
    }

    // navigation menu
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // navigation menu 클릭시 action
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                            mDrawerLayout.closeDrawers();
                        }
                        return true;
                    }
                });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            case R.id.action_search:
                Intent search_intent = new Intent(this, SearchResult.class);
                startActivity(search_intent);
                return true;
            case R.id.action_setting:
                Intent setting_intent = new Intent(this, Main_SettingsActivity.class);
                startActivity(setting_intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, Service_SoundEffect.class);
        intent.putExtra("sound", R.raw.water);
        startService(intent);
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
    public void onClickPush(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("FCM Message")
                .setContentText("test")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public void onClickHome(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            mDrawerLayout.closeDrawers();
        }
    }
    public void onClickClose(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            mDrawerLayout.closeDrawers();
        }
    }

    public void onClickLogout(View view){
        ((Login)Login.Lcontext).logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onClickLeave(View view){
        ((Login)Login.Lcontext).memberLeave();
        ((Login)Login.Lcontext).logout();
        Toast.makeText(this, "회원 탈퇴 되었습니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
