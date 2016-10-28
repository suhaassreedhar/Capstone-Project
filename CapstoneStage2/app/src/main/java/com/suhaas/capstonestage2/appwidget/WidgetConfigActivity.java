package com.suhaas.capstonestage2.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.text.TextUtils;
import android.view.Window;

import com.suhaas.capstonestage2.R;

public class WidgetConfigActivity extends InjectableActivity {
    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);
        if (getIntent().getExtras() == null ||
                (mAppWidgetId = getIntent().getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID)) == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_widget_config);
        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putInt(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.widget_preferences,
                            Fragment.instantiate(this, WidgetConfigurationFragment.class.getName(), args),
                            WidgetConfigurationFragment.class.getName())
                    .commit();
        }
        //noinspection ConstantConditions
        findViewById(R.id.button_ok).setOnClickListener(v -> configure());
    }

    @Override
    protected boolean isDialogTheme() {
        return true;
    }

    private void configure() {
        new WidgetHelper(this).configure(mAppWidgetId);
        setResult(RESULT_OK, new Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId));
        finish();
    }

    public static class WidgetConfigurationFragment extends PreferenceFragmentCompat {

        private SharedPreferences.OnSharedPreferenceChangeListener mListener =
                (sharedPreferences, key) -> {
                    if (TextUtils.equals(key, getString(R.string.pref_widget_query))) {
                        setFilterQuery();
                    }
                };

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getPreferenceManager().setSharedPreferencesName(WidgetHelper.getConfigName(
                    getArguments().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID)));
            getPreferenceManager().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(mListener);
            setFilterQuery();
        }

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            addPreferencesFromResource(R.xml.preferences_widget);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            getPreferenceManager().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(mListener);
        }

        private void setFilterQuery() {
            String key = getString(R.string.pref_widget_query);
            getPreferenceManager().findPreference(key)
                    .setSummary(getPreferenceManager().getSharedPreferences()
                            .getString(key, null));
        }
    }
}
