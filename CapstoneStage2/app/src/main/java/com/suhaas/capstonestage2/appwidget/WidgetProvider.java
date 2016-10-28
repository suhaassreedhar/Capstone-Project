package com.suhaas.capstonestage2.appwidget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;

import com.suhaas.capstonestage2.BuildConfig;
import com.suhaas.capstonestage2.R;

public class WidgetProvider extends AppWidgetProvider {

    static final String ACTION_REFRESH_WIDGET = BuildConfig.APPLICATION_ID + ".ACTION_REFRESH_WIDGET";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            Toast.makeText(context, R.string.not_supported, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.equals(intent.getAction(), ACTION_REFRESH_WIDGET)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            new WidgetHelper(context).refresh(appWidgetId);
        } else if (TextUtils.equals(intent.getAction(), AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            if (appWidgetIds != null) {
                WidgetHelper widgetHelper = new WidgetHelper(context);
                for (int appWidgetId : appWidgetIds) {
                    widgetHelper.configure(appWidgetId);
                }
            }
        } else {
            super.onReceive(context, intent);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        WidgetHelper widgetHelper = new WidgetHelper(context);
        for (int appWidgetId : appWidgetIds) {
            widgetHelper.update(appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        WidgetHelper widgetHelper = new WidgetHelper(context);
        for (int appWidgetId : appWidgetIds) {
            widgetHelper.remove(appWidgetId);
        }
    }
}
