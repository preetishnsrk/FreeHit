package com.debut.ellipsis.freehit.Matches.UpcomingMatches;

//import android.app.LoaderManager;

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

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;


public class UpcomingMatchesActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<UpcomingMatchCardItem>> {


    ArrayList<UpcomingMatchCardItem> upcomingMatches;
    public UpcomingMatchesListAdapter MatchListAdapter;
    private final static int UPCOMING_LOADER_ID=5;
    private RecyclerView rv;
    private static final String URL =
            "https://freehit-api.herokuapp.com/upcoming?max=100";

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_match_list );
        // Lookup the recyclerview in activity layout
        rv = (RecyclerView) findViewById(R.id.match_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        // Create adapter passing in the sample user data
//        MatchListAdapter = new UpcomingMatchesListAdapter(this, upcomingMatches);
//
//        // Attach the adapter to the recyclerview to populate items
//       rv.setAdapter(MatchListAdapter);
//        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getSupportLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(UPCOMING_LOADER_ID, null, this);


        }
    }

    @Override
    public Loader<List<UpcomingMatchCardItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new UpcomingMatchCardLoader(getApplicationContext(), URL);

    }

    @Override
    public void onLoadFinished(Loader<List<UpcomingMatchCardItem>> loader, List<UpcomingMatchCardItem> data) {
//        Log.d(this,"Dead");
//        loadingIndicator.setVisibility(View.GONE);
//         Set empty state text to display "No News found."
        //ABOVE LINES IF UNCOMMENTED GIVE NULLPOINTER EXCEPTION ERROR  . PLEASE CHECK

        mProgressBar.setVisibility(View.GONE);

        // If there is a valid list of {@link UpcomingMatches}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {

            MatchListAdapter = new UpcomingMatchesListAdapter(getApplicationContext(), data);
            // This is the inner viewPager so commenting it out for now
            rv.setAdapter(MatchListAdapter);
        }

    }


//GITPUSH
    @Override
    public void onLoaderReset(Loader<List<UpcomingMatchCardItem>> loader) {

        // Loader reset, so we can clear out our existing data.

    }

    }
