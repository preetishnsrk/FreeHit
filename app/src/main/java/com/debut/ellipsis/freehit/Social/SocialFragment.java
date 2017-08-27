package com.debut.ellipsis.freehit.Social;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFragment extends Fragment {
    public TabLayout socTabs;
    public SearchTimeline searchTimeline;
    private String QueryToSearch = "#cricket";
    public TweetTimelineRecyclerViewAdapter adapter;
    public RecyclerView rv;
    public SocialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View socView = inflater.inflate(R.layout.fragment_social, container, false);
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
                        Toast.makeText(getContext(),"Fetching Twitter Feed failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        socTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabCall(QueryToSearch,SearchTimeline.ResultType.POPULAR);
                        break;
                    case 1:
                        tabCall(QueryToSearch,SearchTimeline.ResultType.RECENT);
                        break;
                    case 2:
                        tabCall(QueryToSearch+" ind",SearchTimeline.ResultType.MIXED);
                        break;
                    default:

                        break;
                }
            }
                @Override
                public void onTabUnselected (TabLayout.Tab tab){

                }
                // Refreshes the feed if a tab is reselected (quality of life)
                @Override
                public void onTabReselected (TabLayout.Tab tab){
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
                                    Toast.makeText(getContext(),"Fetching Twitter Feed failed.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
        });

        return socView;
    }
    // Function to add tabs, maintaining consistancy in program.
    private void setupTabs()
    {
        socTabs.addTab(socTabs.newTab().setText("TOP"));
        socTabs.addTab(socTabs.newTab().setText("LATEST"));
        socTabs.addTab(socTabs.newTab().setText("REGION"));
    }
    // Simple function to set the adapter to the required search query and ResultType (RECENT,POPULAR,MIXED)
    private void tabCall(String query,SearchTimeline.ResultType type){
        searchTimeline = new SearchTimeline.Builder().query(query).resultType(type).build();
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(getContext()).setTimeline(searchTimeline).build();
        rv.setAdapter(adapter);
    }
}
