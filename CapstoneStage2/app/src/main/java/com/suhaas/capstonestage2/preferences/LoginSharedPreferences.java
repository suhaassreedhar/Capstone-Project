package com.suhaas.capstonestage2.preferences;


import android.content.Context;
import android.content.SharedPreferences;

import com.suhaas.capstonestage2.BuildConfig;
import com.suhaas.capstonestage2.HNewsApplication;
import com.suhaas.capstonestage2.login.InputFieldValidator;
import com.suhaas.capstonestage2.model.Login;

public class LoginSharedPreferences {

    public static final String EMPTY_VALUE = "";
    private static final String PREFERENCE_NAME = BuildConfig.APPLICATION_ID + ".LOGIN_PREFERENCES";
    private static final String KEY_COOKIE = BuildConfig.APPLICATION_ID + ".KEY_COOKIE";
    private static final String KEY_USERNAME = BuildConfig.APPLICATION_ID + ".KEY_USERNAME";
    private final SharedPreferences preferences;

    private LoginSharedPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public static LoginSharedPreferences newInstance() {
        SharedPreferences preferences = HNewsApplication.context().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return new LoginSharedPreferences(preferences);
    }

    public void saveLogin(Login login) {
        if (login.getStatus() == Login.Status.SUCCESSFUL) {
            preferences.edit().putString(KEY_COOKIE, login.getCookie()).apply();
            preferences.edit().putString(KEY_USERNAME, login.getUsername()).apply();
        } else {
            logout();
        }
    }

    public Login getLogin() {
        InputFieldValidator inputFieldValidator = new InputFieldValidator();
        String cookie = preferences.getString(KEY_COOKIE, EMPTY_VALUE);
        if (inputFieldValidator.isValid(cookie)) {
            return new Login(preferences.getString(KEY_USERNAME, EMPTY_VALUE), cookie, Login.Status.SUCCESSFUL);
        } else {
            return new Login(EMPTY_VALUE, EMPTY_VALUE, Login.Status.WRONG_CREDENTIALS);
        }
    }

    public boolean isLoggedIn() {
        return getLogin().getStatus() == Login.Status.SUCCESSFUL;
    }

    public String getCookie() {
        return getLogin().getCookie();
    }

    public void logout() {
        preferences.edit().putString(KEY_COOKIE, EMPTY_VALUE).apply();
        preferences.edit().putString(KEY_USERNAME, EMPTY_VALUE).apply();
    }

}
