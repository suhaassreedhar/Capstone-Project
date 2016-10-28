package com.suhaas.capstonestage2.appwidget;


import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;

import com.suhaas.capstonestage2.R;

import java.util.prefs.Preferences;

public abstract class ThemedActivity extends AppCompatActivity {
//    private final MenuTintDelegate mMenuTintDelegate = new MenuTintDelegate();
//    private final Preferences.Observable mThemeObservable = new Preferences.Observable();
    private boolean mResumed = true;
    private boolean mPendingThemeChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Preferences.Theme.apply(this, isDialogTheme(), isTranslucent());
        super.onCreate(savedInstanceState);
//        setTaskTitle(getTitle());
//        mMenuTintDelegate.onActivityCreated(this);
//        mThemeObservable.subscribe(this, (key, contextChanged) ->  onThemeChanged(),
//                R.string.pref_theme);
    }

    @CallSuper
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        mMenuTintDelegate.onOptionsMenuCreated(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mResumed = true;
        if (mPendingThemeChanged) {
//            AppUtils.restart(this, false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mResumed = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mThemeObservable.unsubscribe(this);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
//        setTaskTitle(title);
    }

    protected boolean isDialogTheme() {
        return false;
    }

    protected boolean isTranslucent() {
        return false;
    }

    private void onThemeChanged() {
        if (mResumed) {
//            AppUtils.restart(this, true);
        } else {
            mPendingThemeChanged = true;
        }
    }

//    void setTaskTitle(CharSequence title) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !TextUtils.isEmpty(title)) {
//            setTaskDescription(new ActivityManager.TaskDescription(title.toString(),
//                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_app),
//                    ContextCompat.getColor(this, AppUtils.getThemedResId(this, R.attr.colorPrimary))));
//        }
//    }
}
