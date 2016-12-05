package com.example.mju.embeded;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoginFragment extends Fragment {
    private View v;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(Login.Login_State) v = inflater.inflate(R.layout.fragment_intro2, container, true);
        else v = inflater.inflate(R.layout.fragment_intro1, container, true);

        return v;
    }
}
