package com.suhaas.capstonestage2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suhaas.capstonestage2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAB_POSITION = "tab_position";


    public MainActivityFragment() {
        // Required empty public constructor
    }

    public static MainActivityFragment newInstance(int tabPosition) {
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_POSITION, tabPosition);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle args = getArguments();
        int tabPosition = args.getInt(TAB_POSITION);
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER);
        tv.setText("Text in Tab #" + tabPosition);
        return tv;
    }

}
