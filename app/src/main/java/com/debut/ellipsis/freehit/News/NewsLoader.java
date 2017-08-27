package com.debut.ellipsis.freehit.News;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {
    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;




    /**
     * Constructs a new {@link NewsLoader}.
     *  @param context of the activity
     * @param url to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        Log.i(LOG_TAG,"TEST: onStartLoading() called");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<NewsItem> loadInBackground() {

        Log.i(LOG_TAG,"TEST: loadInBackground() called");
        if (mUrl == null) {
            Log.i(LOG_TAG,"NULL");
            return null;
        }
        // Perform the network request, parse the response, and extract a list of subjects.
        List<NewsItem> news = QueryUtilNews.fetchNewsData(mUrl);

        return news;
    }
}
