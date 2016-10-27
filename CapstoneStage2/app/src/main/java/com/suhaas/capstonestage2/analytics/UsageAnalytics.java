package com.suhaas.capstonestage2.analytics;


import android.content.Context;
import android.os.Bundle;

import com.suhaas.capstonestage2.BuildConfig;
import com.suhaas.capstonestage2.model.Story;

public class UsageAnalytics {

    private com.google.firebase.analytics.FirebaseAnalytics analytics;

    private boolean isActive() {
        return BuildConfig.ENABLE_USAGE_ANALYTICS;
    }

    public void initTracker(Context context) {
        analytics = com.google.firebase.analytics.FirebaseAnalytics.getInstance(context);
    }

    public void trackPage(String page) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, page);
            analytics.logEvent(com.google.firebase.analytics.FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }
    }

    public void trackStory(String page, Story story) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_ID, story.getId().toString());
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_CATEGORY, story.getType());
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, page);
            analytics.logEvent(com.google.firebase.analytics.FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        }
    }

    public void trackEvent(String action) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, action);
            analytics.logEvent(com.google.firebase.analytics.FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        }
    }

    public void trackLogin(String user) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, user);
            analytics.logEvent(com.google.firebase.analytics.FirebaseAnalytics.Event.LOGIN, bundle);
        }
    }

    public void trackNavigateEvent(String action, Story story) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_ID, story.getId().toString());
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, action);
            analytics.logEvent("Navigate", bundle);
        }
    }

    public void trackShareEvent(String action, Story story) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_ID, story.getId().toString());
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, action);
            analytics.logEvent(com.google.firebase.analytics.FirebaseAnalytics.Event.SHARE, bundle);
        }
    }

    public void trackVoteEvent(String action, Story story) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_ID, story.getId().toString());
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, action);
            analytics.logEvent("Vote", bundle);
        }
    }

    public void trackBookmarkEvent(String action, Story story) {
        if (isActive() && analytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_ID, story.getId().toString());
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, action);
            analytics.logEvent("Bookmark", bundle);
        }
    }

}
