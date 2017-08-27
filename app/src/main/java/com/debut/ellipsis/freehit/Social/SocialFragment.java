package com.debut.ellipsis.freehit.Social;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.debut.ellipsis.freehit.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<PollCardItem>> {
    public TabLayout socTabs;
    private PollItemAdapter pollAdapter;
    private static final String URL =
            "http://www.strawpoll.me/api/v2/polls/1";
    public SearchTimeline searchTimeline;
    private static final int POLLS_LOADER_ID = 2;

    private String QueryToSearch = "#cricket";
    public TweetTimelineRecyclerViewAdapter adapter;
    public RecyclerView rv;
    ViewPager pollsPager;
    public SocialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View socView = inflater.inflate(R.layout.fragment_social, container, false);
        //--------------------- Polls Initialization------------------------
        pollsPager = (ViewPager) socView.findViewById(R.id.poll_pager);
        pollAdapter = new PollItemAdapter(getContext(), new ArrayList<PollCardItem>());
        pollsPager.setAdapter(pollAdapter);
        //--------------------- End of Polls Initialization------------------------

        //--------------------- Twitter Initialization------------------------
//        final TweetTimelineRecyclerViewAdapter adapter = new TweetTimelineRecyclerViewAdapter.Builder(getContext()).setTimeline(searchTimeline).build();
        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) socView.findViewById(R.id.soc_refresh_layout);
        socTabs = (TabLayout) socView.findViewById(R.id.soc_tabs);
        setupTabs();
        //  Initializing the RecyclerView for Twitter feed
        rv = (RecyclerView) socView.findViewById(R.id.twit_feed);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //  Using a SearchTimeline to search for a given query, can be changed with (UserTimeline, CollectionTimeline, TwitterListTimeline, or FixedTweetTimeline)
        //  Defining a recyclerView adapter for the given Timeline, Twitter builds all the icons and intents and all that shit. I love twitter.
        tabCall("#cricket", SearchTimeline.ResultType.POPULAR);


        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        refLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(getContext(), "Fetching Twitter Feed failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        socTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabCall(QueryToSearch, SearchTimeline.ResultType.POPULAR);
                        break;
                    case 1:
                        tabCall(QueryToSearch, SearchTimeline.ResultType.RECENT);
                        break;
                    case 2:
                        tabCall(QueryToSearch + " ind", SearchTimeline.ResultType.MIXED);
                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            // Refreshes the feed if a tab is reselected (quality of life)
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refLayout.setRefreshing(true);
                        adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                            @Override
                            public void success(Result<TimelineResult<Tweet>> result) {
                                refLayout.setRefreshing(false);
                            }

                            @Override
                            public void failure(TwitterException exception) {
                                Toast.makeText(getContext(), "Fetching Twitter Feed failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        //Checking Conn

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
            loaderManager.initLoader(POLLS_LOADER_ID, null, this);


        }
        return socView;

    }


        // Function to add tabs, maintaining consistancy in program.

    private void setupTabs() {
        socTabs.addTab(socTabs.newTab().setText("TOP"));
        socTabs.addTab(socTabs.newTab().setText("LATEST"));
        socTabs.addTab(socTabs.newTab().setText("REGION"));
    }

    // Simple function to set the adapter to the required search query and ResultType (RECENT,POPULAR,MIXED)
    private void tabCall(String query, SearchTimeline.ResultType type) {
        searchTimeline = new SearchTimeline.Builder().query(query).resultType(type).build();
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(getContext()).setTimeline(searchTimeline).build();
        rv.setAdapter(adapter);
    }



    //--------------------- Polls Loader------------------------
    @Override
    public Loader<List<PollCardItem>> onCreateLoader(int id, Bundle args) {
        return new PollsLoader(getActivity(), URL);
    }

    @Override
    public void onLoadFinished(Loader<List<PollCardItem>> loader, List<PollCardItem> data) {


        if (data != null && !data.isEmpty()) {
            pollAdapter = new PollItemAdapter(getContext(),data);
            pollsPager.setAdapter(pollAdapter);
        }
    }


    @Override
    public void onLoaderReset(Loader<List<PollCardItem>> loader) {

    }

}
