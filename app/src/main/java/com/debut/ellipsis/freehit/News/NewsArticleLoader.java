package com.debut.ellipsis.freehit.News;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

public class NewsArticleLoader extends AsyncTaskLoader<NewsArticleItem> {
    /*Tag for log messages*/
    private static final String LOG_TAG = NewsArticleLoader.class.getName();

    public static NewsArticleItem news;

    /**
     * Query URL
     */
    private String mUrl;

    /*** Constructs a new {@link NewsLoader}.
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        Log.i(LOG_TAG, "TEST: onStartLoading() called");
        forceLoad();
    }

    /* This is on a background thread.*/
    @Override
    public NewsArticleItem loadInBackground() {

        Log.i(LOG_TAG, "TEST: loadInBackground() called");
        if (mUrl == null) {
            Log.i(LOG_TAG, "NULL");
            return null;
        }
        // Perform the network request, parse the response, and extract a list of NewsArticles.
        news = QueryUtilNewsArticle.fetchNewsData(mUrl);
        Log.e(LOG_TAG, news.getMheadline());
        return news;
    }
}