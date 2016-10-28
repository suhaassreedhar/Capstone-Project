package com.suhaas.capstonestage2.appwidget;


import android.support.annotation.Nullable;

public interface ResponseListener<T> {
    /**
     * Fired when request is successful
     * @param response result
     */
    void onResponse(@Nullable T response);

    /**
     * Fired when request is failed
     * @param errorMessage error message or null
     */
    void onError(String errorMessage);
}