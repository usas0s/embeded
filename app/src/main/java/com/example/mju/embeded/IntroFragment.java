package com.example.mju.embeded;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;


public class IntroFragment extends Fragment {
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Random r = new Random();
        int t = r.nextInt(9);
        if(t>4)  v = inflater.inflate(R.layout.fragment_intro1, container, true);
        else v = inflater.inflate(R.layout.fragment_intro2, container, true);
        return v;
    }

}
