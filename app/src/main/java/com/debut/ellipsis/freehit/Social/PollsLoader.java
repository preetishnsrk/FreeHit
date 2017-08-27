package com.debut.ellipsis.freehit.Social;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;


public class PollsLoader extends AsyncTaskLoader<List<PollCardItem>> {
    private static final String LOG_TAG = PollsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public PollsLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        Log.i(LOG_TAG,"TEST: onStartLoading() called");
        forceLoad();
    }


    @Override
    public List<PollCardItem> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called");
        if (mUrl == null) {
            Log.i(LOG_TAG,"NULL");
            return null;
        }
        List<PollCardItem> polls = QueryUtilPolls.fetchPollData(mUrl);
        return polls;
    }
}
