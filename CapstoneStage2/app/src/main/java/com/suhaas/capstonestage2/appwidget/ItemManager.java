package com.suhaas.capstonestage2.appwidget;


import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.support.annotation.WorkerThread;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ItemManager {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            TOP_FETCH_MODE,
            NEW_FETCH_MODE,
            ASK_FETCH_MODE,
            SHOW_FETCH_MODE,
            JOBS_FETCH_MODE,
            BEST_FETCH_MODE
    })
    @interface FetchMode {}
    String TOP_FETCH_MODE = "top";
    String NEW_FETCH_MODE = "new";
    String ASK_FETCH_MODE = "ask";
    String SHOW_FETCH_MODE = "show";
    String JOBS_FETCH_MODE = "jobs";
    String BEST_FETCH_MODE = "best";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            MODE_DEFAULT,
            MODE_CACHE,
            MODE_NETWORK
    })
    @interface CacheMode {}
    int MODE_DEFAULT = 0;
    int MODE_CACHE =1;
    int MODE_NETWORK = 2;

    /**
     * Gets array of top stories
     * @param filter    filter of stories to fetch
     * @param cacheMode cache mode
     * @param listener  callback to be notified on response
     */
    void getStories(String filter, @CacheMode int cacheMode, final ResponseListener<Item[]> listener);

    /**
     * Gets individual item by ID
     * @param itemId        item ID
     * @param cacheMode     cache mode
     * @param listener      callback to be notified on response
     */
    void getItem(String itemId, @CacheMode int cacheMode, ResponseListener<Item> listener);

    /**
     * Gets array of stories
     * @param filter       filter of stories to fetch
     * @param cacheMode    cache mode
     * @return  array of stories
     */
    @WorkerThread
    Item[] getStories(String filter, @CacheMode int cacheMode);

    /**
     * Gets individual item by ID
     * @param itemId       item ID
     * @param cacheMode    cache mode
     * @return  item or null
     */
    @WorkerThread
    Item getItem(String itemId, @CacheMode int cacheMode);
}
