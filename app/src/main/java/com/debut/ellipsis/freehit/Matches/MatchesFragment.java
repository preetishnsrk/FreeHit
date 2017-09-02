package com.debut.ellipsis.freehit.Matches;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCard;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCard;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCard;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment {

    private static ArrayList<MatchCardItem> data;

    private static final String URL =
            "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.scorecard.live.summary&format=json&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0&callback=";

    private static final int MATCH_LOADER_ID = 3;

    private MatchesItemAdapter mAdapter;
    public TextView mEmptyStateTextView;
    public ViewPager viewPager;
    public CircleIndicator indicator;
    private TabLayout tabLayout;

    public static final String LOG_TAG = MatchesFragment.class.getSimpleName();

    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);


//  // Get a reference to the ConnectivityManager to check state of network connectivity
        /*ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(MATCH_LOADER_ID, null, this);


        }*/

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
//        mAdapter = new MatchesItemAdapter(getActivity(), new ArrayList<MatchCardItem>());
//        viewPager.setAdapter(mAdapter);

        tabLayout = (TabLayout)rootView.findViewById(R.id.match_card_tabs);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        indicator = (CircleIndicator) rootView.findViewById(R.id.indicator);
//        indicator.setViewPager(viewPager);

        return rootView;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new LiveMatchCard(), "LIVE",0);
        adapter.addFrag(new UpcomingMatchCard(), "UPCOMING",1);
        adapter.addFrag(new PastMatchCard(), "PAST",2);
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title,int i) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(i,title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


 /*   @Override
    public Loader<List<MatchCardItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new MatchCardLoader(getActivity(), URL);

    }

    @Override
    public void onLoadFinished(Loader<List<MatchCardItem>> loader, List<MatchCardItem> data) {
//        Log.d(this,"Dead");
//        loadingIndicator.setVisibility(View.GONE);
//         Set empty state text to display "No News found."
        //ABOVE LINES IF UNCOMMENTED GIVE NULLPOINTER EXCEPTION ERROR  . PLEASE CHECK

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty() && mAdapter.getCount() <= 1) {

            mAdapter = new MatchesItemAdapter(getContext(), data);
            // This is the inner viewPager so commenting it out for now
//            viewPager.setAdapter(mAdapter);
//            indicator.setViewPager(viewPager);
        }
    }


    @Override
    public void onLoaderReset(Loader<List<MatchCardItem>> loader) {

        // Loader reset, so we can clear out our existing data.

    }*/


}
