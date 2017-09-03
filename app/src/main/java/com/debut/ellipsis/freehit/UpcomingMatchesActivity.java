package com.debut.ellipsis.freehit;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardLoader;
import com.debut.ellipsis.freehit.Matches.UpcomingMatchesListAdapter;

import java.util.ArrayList;
import java.util.List;


public class UpcomingMatchesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<UpcomingMatchCardItem>> {


    ArrayList<UpcomingMatchCardItem> upcomingMatches;
    public UpcomingMatchesListAdapter MatchListAdapter;
    private RecyclerView rv;
    private static final String URL =
            "https://freehit-api.herokuapp.com/upcoming";

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lookup the recyclerview in activity layout
        rv = (RecyclerView) findViewById(R.id.match_list);

        // Create adapter passing in the sample user data
        MatchListAdapter = new UpcomingMatchesListAdapter(this, upcomingMatches);

        // Attach the adapter to the recyclerview to populate items
        rv.setAdapter(MatchListAdapter);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
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
        if (data != null && !data.isEmpty() && MatchListAdapter.getItemCount() <= 1) {

            MatchListAdapter = new UpcomingMatchesListAdapter(getApplicationContext(), data);
            // This is the inner viewPager so commenting it out for now
            rv.setAdapter(MatchListAdapter);
        }

    }



    @Override
    public void onLoaderReset(Loader<List<UpcomingMatchCardItem>> loader) {

        // Loader reset, so we can clear out our existing data.

    }

    }
