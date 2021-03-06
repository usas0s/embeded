package com.example.mju.embeded;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Copyright (C) 컴퓨터공학과 60112320 김동빈
 */

public class Main_ViewpagerAdapter extends FragmentPagerAdapter {
    public Main_ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return Main_PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "등록순";
            case 1:
                return "인원순";
            case 2:
                return "이름순";
        }
        return null;
    }
}
