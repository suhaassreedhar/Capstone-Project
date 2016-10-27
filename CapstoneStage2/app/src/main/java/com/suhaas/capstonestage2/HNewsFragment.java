package com.suhaas.capstonestage2;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.suhaas.capstonestage2.connectivity.NetworkChecker;

public class HNewsFragment extends Fragment {

    private NetworkChecker networkChecker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetworkChecker();
    }

    private void initNetworkChecker() {
        networkChecker = new NetworkChecker(getActivity());
    }

    protected boolean isOnline() {
        return networkChecker.isConnected();
    }
}
