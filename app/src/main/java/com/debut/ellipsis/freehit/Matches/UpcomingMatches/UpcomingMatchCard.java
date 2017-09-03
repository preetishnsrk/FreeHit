package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMatchCard extends Fragment implements LoaderManager.LoaderCallbacks<List<UpcomingMatchCardItem>> {

    public UpcomingMatchCard() {
        // Required empty public constructor
    }

    private static final String URL =
            "https://freehit-api.herokuapp.com/upcoming?max=5";

    private static final int UPCOMING_MATCH_LOADER_ID = 1;
    public ViewPager viewPager;
    public CircleIndicator indicator;
    private UpcomingMatchesItemAdapter mAdapter;
    private ProgressBar mProgressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.match_cards_item, container, false);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(UPCOMING_MATCH_LOADER_ID, null, this);


        }

        mProgressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
        int colorCodeDark = Color.parseColor("#F44336");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(colorCodeDark));
        }

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mAdapter = new UpcomingMatchesItemAdapter(getActivity(), new ArrayList<UpcomingMatchCardItem>());
        viewPager.setAdapter(mAdapter);

        indicator = (CircleIndicator) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        return rootView;
    }

    @Override
    public Loader<List<UpcomingMatchCardItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new UpcomingMatchCardLoader(getActivity(), URL);

    }

    @Override
    public void onLoadFinished(Loader<List<UpcomingMatchCardItem>> loader, List<UpcomingMatchCardItem> data) {

        mProgressBar.setVisibility(View.GONE);

        // If there is a valid list of {@link UpcomingMatches}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty() && mAdapter.getCount() <= 1) {

            mAdapter = new UpcomingMatchesItemAdapter(getContext(), data);
            // This is the inner viewPager so commenting it out for now
            viewPager.setAdapter(mAdapter);
            indicator.setViewPager(viewPager);
        }




    }



    @Override
    public void onLoaderReset(Loader<List<UpcomingMatchCardItem>> loader) {

        // Loader reset, so we can clear out our existing data.

    }

}
