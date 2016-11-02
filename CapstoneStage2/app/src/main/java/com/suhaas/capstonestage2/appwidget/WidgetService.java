package com.suhaas.capstonestage2.appwidget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.support.v4.content.CursorLoader;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.suhaas.capstonestage2.R;
import com.suhaas.capstonestage2.data.HNewsContract;
import com.suhaas.capstonestage2.data.HNewsProvider;
import com.suhaas.capstonestage2.model.Story;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WidgetService extends RemoteViewsService {
    private Story story;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;
            @Override
            public void onCreate() {
                // Nothing to do
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }
                final long identityToken = Binder.clearCallingIdentity();
                Uri storyNewsUri = HNewsContract.StoryEntry.buildStoriesUri();

                runOnUiThread(() -> data = (Cursor) new CursorLoader(
                        getApplication(),
                        storyNewsUri,
                        HNewsContract.StoryEntry.STORY_COLUMNS,
                        HNewsContract.StoryEntry.TYPE + " = ?",
                        new String[]{Story.TYPE.story.name()},
                        HNewsContract.StoryEntry.RANK + " ASC" +
                                ", " + HNewsContract.StoryEntry.TIMESTAMP + " DESC"));


                Binder.restoreCallingIdentity(identityToken);

            }

            private void runOnUiThread(Runnable runnable) {
            }

            @Override
            public void onDestroy() {

            }

            @Override
            public int getCount() {
            return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_collection_item);
                views.setTextViewText(R.id.news_title, data.getString(data.getColumnIndex("title")));

                final Intent fillInIntent = new Intent();
                fillInIntent.putExtra(getResources().getString(Integer.parseInt("title")), data.getString(data.getColumnIndex(HNewsContract.StoryEntry.TITLE)));
                views.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null; // use the default loading view
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data != null && data.moveToPosition(position)) {
                    final int QUOTES_ID_COL = 0;
                    return data.getLong(QUOTES_ID_COL);
                }
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
