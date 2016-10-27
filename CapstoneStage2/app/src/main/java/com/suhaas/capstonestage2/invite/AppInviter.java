package com.suhaas.capstonestage2.invite;


import com.suhaas.capstonestage2.BuildConfig;
import com.suhaas.capstonestage2.preferences.AppInviteSharedPreferences;

public class AppInviter {

    private AppInviteSharedPreferences appInviteSharedPreferences;

    public AppInviter() {
        this.appInviteSharedPreferences = AppInviteSharedPreferences.newInstance();
    }

    public boolean shouldShow() {
        return BuildConfig.ENABLE_APP_INVITES && meetsCriteria();
    }

    private boolean meetsCriteria() {
        return appInviteSharedPreferences.isFirstTime() || appInviteSharedPreferences.shouldBeReminded();
    }

}
