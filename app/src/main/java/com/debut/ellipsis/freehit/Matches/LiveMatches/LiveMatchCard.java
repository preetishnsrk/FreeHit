package com.debut.ellipsis.freehit.Matches.LiveMatches;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.debut.ellipsis.freehit.R;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMatchCard extends Fragment implements LoaderManager.LoaderCallbacks<List<LiveMatchCardItem>> {


    public LiveMatchCard() {
        // Required empty public constructor
    }

    private static final String URL =
            "https://freehit-api.herokuapp.com/live";

    private static final int LIVE_MATCH_LOADER_ID = 1;
    public ViewPager viewPager;
    public PageIndicatorView indicator;
    private LiveMatchCardAdapter mAdapter;
    private ProgressBar mProgressBar;
    public ImageView mEmptyStateTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.match_cards_item, container, false);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        final ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LIVE_MATCH_LOADER_ID, null, this).forceLoad();


        }
        else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View progressBar = rootView.findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);

            mEmptyStateTextView = (ImageView)rootView.findViewById(R.id.empty_view);
            // Update empty state with no connection error message
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
        final PullRefreshLayout layout = (PullRefreshLayout)rootView.findViewById(R.id.swipeRefreshLayout);

// listen refresh event


// refresh complete
        layout.setRefreshing(false);


        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        int colorCodeDark = Color.parseColor("#F44336");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(colorCodeDark));
        }

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mAdapter = new LiveMatchCardAdapter(getActivity(), new ArrayList<LiveMatchCardItem>());
        viewPager.setAdapter(mAdapter);



        indicator = (PageIndicatorView) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh

                layout.setRefreshing(true);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
//                LoaderManager loaderManager = getLoaderManager();
//                mAdapter.clear();
                    getLoaderManager().restartLoader(LIVE_MATCH_LOADER_ID, null, LiveMatchCard.this);
                    mAdapter.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            layout.setRefreshing(false);
                        }
                    }, 1000);

                } else {
                    // Otherwise, display error
                    // First, hide loading indicator so error message will be visible

//                    mAdapter.add(new NewsItem("No connection", "Looks like you have no connection, switch on your internet connection and try refreshing to see the latest news."));
                    mAdapter.notifyDataSetChanged();

                }
                layout.setRefreshing(false);
            }
        });
        return rootView;

    }

    @Override
    public Loader<List<LiveMatchCardItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new LiveMatchCardLoader(getActivity(), URL);

    }

    @Override
    public void onLoadFinished(Loader<List<LiveMatchCardItem>> loader, List<LiveMatchCardItem> data) {

        mProgressBar.setVisibility(View.GONE);

        //If there is a valid list of {@link LiveMatch}es, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {

            mAdapter = new LiveMatchCardAdapter(getContext(), data);
            // This is the inner viewPager so commenting it out for now
            viewPager.setAdapter(mAdapter);
            indicator.setViewPager(viewPager);
            indicator.setVisibility(View.INVISIBLE);
            IndicatorConfig();


        }


    }
    private void IndicatorConfig(){
        indicator.setVisibility(View.VISIBLE);
        indicator.setAnimationType(AnimationType.DROP);
        indicator.setUnselectedColor(R.color.dot_light_screen1);
        indicator.setSelectedColor(R.color.dot_dark_screen1);
        indicator.setInteractiveAnimation(true);
        indicator.setAnimationDuration(500);

    }


    @Override
    public void onLoaderReset(Loader<List<LiveMatchCardItem>> loader) {

        // Loader reset, so we can clear out our existing data.

    }

}
