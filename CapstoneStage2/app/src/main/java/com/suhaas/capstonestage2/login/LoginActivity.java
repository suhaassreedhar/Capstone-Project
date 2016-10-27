package com.suhaas.capstonestage2.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.suhaas.capstonestage2.R;

public class LoginActivity extends AppCompatActivity {

    public static final String VIEW_TOOLBAR_TITLE = "login:toolbar:title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
