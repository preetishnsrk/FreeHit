package com.debut.ellipsis.freehit.Social.Polls;


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
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.News.NewsItem;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialPolls extends Fragment implements LoaderManager.LoaderCallbacks<List<PollCardItem>> {

    public static Fragment myFragment;

    public static final String LOG_TAG = SocialPolls.class.getSimpleName();

    private static final String URL =
            "https://freehit-api.herokuapp.com/polls";

    private static final int NEWS_LOADER_ID = 1;
    public TextView mEmptyStateTextView;
    private PollItemAdapter mAdapter;
    private ProgressBar mProgressBar;
    View fragView;

    public SocialPolls() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragment=this;
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.item, container, false);
        ListView PollsListView = (ListView) rootView.findViewById(R.id.list);
        fragView = inflater.inflate(R.layout.social_polls_list_item, container, false);
        final RadioGroup rGroup =(RadioGroup) fragView.findViewById(R.id.poll_group);
        mEmptyStateTextView = (TextView) (fragView.findViewById(R.id.empty_view));
        PollsListView.setEmptyView(mEmptyStateTextView);
//
//        PollsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                PollCardItem currentItem = (PollCardItem) mAdapter.getItem(position);
//                int selectedRadio =rGroup.getCheckedRadioButtonId();
//                RadioButton rButton = (RadioButton)fragView.findViewById(selectedRadio);
//                int pId = currentItem.getpId();
//                int cId = currentItem.getcId(rButton.getText().toString());
//                String tempURL = URL;
//                URL.concat("?id=" + pId + "&cid=" + cId);
//                System.out.println(URL);
//                getLoaderManager().restartLoader(NEWS_LOADER_ID, null, SocialPolls.this);
//            }
//        });

        // Create a new adapter that takes an empty list of subjects as input
        mAdapter = new PollItemAdapter(getActivity(), new ArrayList<PollCardItem>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        PollsListView.setAdapter(mAdapter);


        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        int colorCodeDark = Color.parseColor("#F44336");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(colorCodeDark));
        }

        // Get a reference to the ConnectivityManager to check state of network connectivity
        final ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mAdapter.add(new NewsItem("No connection", "Looks like you have no connection, switch on your internet connection and try refreshing to see the latest news."));
            return rootView;

        }

        // Finding a reference to the refresh layout
        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        refLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);
                                               NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                               if (networkInfo != null && networkInfo.isConnected()) {
//                LoaderManager loaderManager = getLoaderManager();
                                                   mAdapter.clear();
//                mAdapter.clear();
                                                   getLoaderManager().restartLoader(NEWS_LOADER_ID, null, SocialPolls.this);
                                                   mAdapter.notifyDataSetChanged();
                                                   new Handler().postDelayed(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           refLayout.setRefreshing(false);
                                                       }
                                                   }, 500);

                                               } else {
                                                   // Otherwise, display error
                                                   // First, hide loading indicator so error message will be visible
                                                   mAdapter.clear();
                                                   mAdapter.add(new NewsItem("No connection", "Looks like you have no connection, switch on your internet connection and try refreshing to see the latest news."));
                                                   mAdapter.notifyDataSetChanged();

                                               }
                                               refLayout.setRefreshing(false);
                                           }
                                       }
        );


        return rootView;
    }



    @Override
    public Loader<List<PollCardItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new PollsLoader(getActivity(), URL);

    }

    @Override
    public void onLoadFinished(Loader<List<PollCardItem>> loader, List<PollCardItem> Polls) {

        mProgressBar.setVisibility(View.GONE);
        if (mEmptyStateTextView.getText() == null) {
            mEmptyStateTextView.setText(R.string.EmptyNews);
        }

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (Polls != null && !Polls.isEmpty() && mAdapter.getCount() < 1) {

            mAdapter.addAll(Polls);
        }


    }


    @Override
    public void onLoaderReset(Loader<List<PollCardItem>> loader) {
        loader = null;
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


}



