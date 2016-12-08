package com.example.mju.embeded.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mju.embeded.R;


public class LoginFragment extends Fragment {
    private FragmentManager manager;
    private Fragment frg;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(Login.Login_State) {
            v = inflater.inflate(R.layout.fragment_login2, container, true);

            TextView name = (TextView)v.findViewById(R.id.nav_name);
            TextView depart = (TextView)v.findViewById(R.id.nav_depart);
            TextView phone = (TextView)v.findViewById(R.id.nav_phone);
            TextView email = (TextView)v.findViewById(R.id.nav_email);
            name.setText(Login.current_Account_ID);
            depart.setText(Login.current_Account_DEPART);
            phone.setText(Login.current_Account_PHONE);
            email.setText(Login.current_Account_EMAIL);
        }
        else v = inflater.inflate(R.layout.fragment_login1, container, true);

        return v;
    }
}
