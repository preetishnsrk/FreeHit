package com.debut.ellipsis.freehit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment {

    private static ArrayList<MatchCardItem> data;

    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);

        data = new ArrayList<MatchCardItem>();
        for (int i = 0; i < MyData.MatchNameArray.length-1; i++) {
            data.add(new MatchCardItem(
                    MyData.MatchNameArray[i],
                    MyData.SeriesNameArray[i],
                    MyData.TempTeam1LogoArray[i],
                    MyData.Team1ScoreArray[i],
                    MyData.Team1OversArray[i],
                    MyData.TempTeam2LogoArray[i],
                    MyData.Team2ScoreArray[i],
                    MyData.Team2OversArray[i],
                    MyData.MatchStatusResultArray[i],
                    MyData.TargetLeadBysArray[i],
                    MyData.MatchSummaryPreview[i]
            ));
        }

        ViewPager viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);
        MatchesItemAdapter mCustomPagerAdapter = new MatchesItemAdapter(getActivity(),data);
        viewPager.setAdapter(mCustomPagerAdapter);
        CircleIndicator indicator = (CircleIndicator)rootView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        return rootView;
    }

}
