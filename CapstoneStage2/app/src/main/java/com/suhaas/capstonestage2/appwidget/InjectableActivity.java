package com.suhaas.capstonestage2.appwidget;


import android.os.Bundle;

import com.suhaas.capstonestage2.HNewsApplication;

import dagger.ObjectGraph;

public abstract class InjectableActivity extends ThemedActivity implements Injectable {
    private ObjectGraph mActivityGraph;
    private boolean mDestroyed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityGraph = ((HNewsApplication) getApplication()).getApplicationGraph()
                .plus(new ActivityModule(this), new UiModule());
        mActivityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDestroyed = true;
        mActivityGraph = null;
    }

    @Override
    public void onBackPressed() {
        // TODO http://b.android.com/176265
        try {
            super.onBackPressed();
        } catch (IllegalStateException e) {
            supportFinishAfterTransition();
        }
    }

    @Override
    public void inject(Object object) {
        mActivityGraph.inject(object);
    }

    public boolean isActivityDestroyed() {
        return mDestroyed;
    }
}
