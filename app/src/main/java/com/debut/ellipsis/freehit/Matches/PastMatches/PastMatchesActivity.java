package com.debut.ellipsis.freehit.Matches.PastMatches;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.List;


public class PastMatchesActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<PastMatchCardItem>> {


    public PastMatchesListAdapter MatchListAdapter;
    private final static int PAST_LOADER_ID = 5;
    private RecyclerView rv;
    private static final String URL =
            "https://freehit-api.herokuapp.com/past?max=100";

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_match_list);

        TextView PageHeader = (TextView) findViewById(R.id.page_heading);
        PageHeader.setText(R.string.past_list);

        // Lookup the recyclerview in activity layout
        rv = (RecyclerView) findViewById(R.id.match_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getSupportLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(PAST_LOADER_ID, null, this);


        }
    }

    @Override
    public Loader<List<PastMatchCardItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new PastMatchCardItemLoader(getApplicationContext(), URL);

    }

    @Override
    public void onLoadFinished(Loader<List<PastMatchCardItem>> loader, List<PastMatchCardItem> data) {

        mProgressBar.setVisibility(View.GONE);

        // If there is a valid list of {@link PastMatch}es, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {

            MatchListAdapter = new PastMatchesListAdapter(getApplicationContext(), data);
            // This is the inner viewPager so commenting it out for now
            rv.setAdapter(MatchListAdapter);
        }

    }


    @Override
    public void onLoaderReset(Loader<List<PastMatchCardItem>> loader) {

        // Loader reset, so we can clear out our existing data.

    }

}
