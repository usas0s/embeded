package com.example.mju.embeded;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Bini2 on 2016-11-25.
 */

public class Main_PlaceholderFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Main_PlaceholderFragment() {
    }

    public static Main_PlaceholderFragment newInstance(int sectionNumber) {
        Main_PlaceholderFragment fragment = new Main_PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

        final View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        if(sectionNumber == 1){
            GridView gridView = (GridView) rootView.findViewById(R.id.main_gridView);
            gridView.setAdapter(new Main_GridviewAdapter(getActivity().getApplicationContext()));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity().getApplicationContext(),Details.class);
                    startActivity(intent);
                }
            });
        }else if(sectionNumber == 2){
            GridView gridView = (GridView) rootView.findViewById(R.id.main_gridView);
            gridView.setAdapter(new Main_GridviewAdapter(getActivity().getApplicationContext()));
        }else{
            GridView gridView = (GridView) rootView.findViewById(R.id.main_gridView);
            gridView.setAdapter(new Main_GridviewAdapter(getActivity().getApplicationContext()));
        }
        return rootView;
    }
}
