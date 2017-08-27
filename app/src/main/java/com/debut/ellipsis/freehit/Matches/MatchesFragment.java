package com.debut.ellipsis.freehit.Matches;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.News.NewsItemAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<MatchCardItem>> {

    private static ArrayList<MatchCardItem> data;

    private static final String URL =
            "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.scorecard.live.summary&format=json&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0&callback=";

    private static final int NEWS_LOADER_ID = 1;

    private MatchesItemAdapter mAdapter;
    public TextView mEmptyStateTextView;

    public static final String LOG_TAG = MatchesFragment.class.getSimpleName();

    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        View fragView = inflater.inflate(R.layout.match_cards, container, false);



        data = new ArrayList<MatchCardItem>();
        for (int i = 0; i < MyData.MatchNameArray.size()-1; i++) {
            data.add(new MatchCardItem(
                    MyData.MatchNameArray.toString(),
                    MyData.SeriesNameArray.toString(),
                    MyData.TempTeam1LogoArray[i],
                    MyData.Team1ScoreArray.toString(),
                    MyData.Team1OversArray.toString(),
                    MyData.TempTeam2LogoArray[i],
                    MyData.Team2ScoreArray.toString(),
                    MyData.Team2OversArray.toString(),
                    MyData.MatchStatusResultArray.toString(),
                    MyData.TargetLeadBysArray.toString(),
                    MyData.MatchSummaryPreview[i]
            ));
        }
//        data.add(new MatchCardItem("a","a","A","a","a","a","a","a","a","a"));



        for (int i=0;i<MyData.MatchNameArray.size()-1;i++)
        {
            System.out.print(MyData.MatchNameArray);
            Log.i(LOG_TAG,MyData.MatchNameArray.toString());
            Log.i(LOG_TAG,MyData.SeriesNameArray.toString());
            Log.i(LOG_TAG,MyData.Team1ScoreArray.toString());
            Log.i(LOG_TAG,MyData.Team1OversArray.toString());
            Log.i(LOG_TAG,MyData.Team2ScoreArray.toString());
            Log.i(LOG_TAG,MyData.Team2OversArray.toString());
            Log.i(LOG_TAG,MyData.MatchStatusResultArray.toString());
            Log.i(LOG_TAG,MyData.TargetLeadBysArray.toString());
        }

        ViewPager viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);
        MatchesItemAdapter mCustomPagerAdapter = new MatchesItemAdapter(getActivity(),data);
        viewPager.setAdapter(mCustomPagerAdapter);
        CircleIndicator indicator = (CircleIndicator)rootView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        return rootView;
    }
    @Override
    public Loader<List<MatchCardItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new MatchCardLoader(getActivity(), URL);

    }

    @Override
    public void onLoadFinished(Loader<List<MatchCardItem>> loader, List<MatchCardItem> Matchcard) {
//        Log.d(this,"Dead");
//        loadingIndicator.setVisibility(View.GONE);
//         Set empty state text to display "No News found."
        //ABOVE LINES IF UNCOMMENTED GIVE NULLPOINTER EXCEPTION ERROR  . PLEASE CHECK

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (Matchcard != null && !Matchcard.isEmpty() && mAdapter.getCount()<=1) {

        }

    }



    @Override
    public void onLoaderReset(Loader<List<MatchCardItem>> loader) {
        loader=null;
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}
