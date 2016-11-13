package com.suhaas.capstonestage2.appwidget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.suhaas.capstonestage2.R;
import com.suhaas.capstonestage2.data.HNewsContract;
import com.suhaas.capstonestage2.model.Story;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WidgetService extends RemoteViewsService {
    Cursor mCursor;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            @Override
            public void onCreate() {
                // Nothing to do
            }

            @Override
            public void onDataSetChanged() {
                if (mCursor != null)
                    mCursor.close();
                    final long identityToken = Binder.clearCallingIdentity();
                    Uri storyNewsUri = HNewsContract.StoryEntry.buildStoriesUri();
                    mCursor = WidgetService.this.getContentResolver().query(storyNewsUri, HNewsContract.StoryEntry.STORY_COLUMNS
                            ,HNewsContract.StoryEntry.TYPE + " = ?"
                            ,new String[]{Story.TYPE.story.name()}
                            ,HNewsContract.StoryEntry.RANK + " ASC" +
                                    ", " + HNewsContract.StoryEntry.TIMESTAMP + " DESC");
                    Binder.restoreCallingIdentity(identityToken);

            }

            @Override
            public void onDestroy() {
                if (mCursor != null)
                    mCursor.close();
            }

            @Override
            public int getCount() {
            return mCursor == null ? 0 : mCursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews views = new RemoteViews(WidgetService.this.getPackageName(), R.layout.widget_collection_item);
                if (position == AdapterView.INVALID_POSITION ||
                        mCursor == null || !mCursor.moveToPosition(position)) {
                    views.setTextViewText(R.id.article_title,
                            mCursor.getString(mCursor.getColumnIndex(getResources().getString(R.string.string_title))));

                    final Intent fillInIntent = new Intent();
                    fillInIntent.putExtra(getResources().getString(R.string.string_title),
                            mCursor.getString(mCursor.getColumnIndex(HNewsContract.StoryEntry.TITLE)));
                    views.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);
                }
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
                if (mCursor != null && mCursor.moveToPosition(position)) {
                    final int QUOTES_ID_COL = 0;
                    return mCursor.getLong(QUOTES_ID_COL);
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
