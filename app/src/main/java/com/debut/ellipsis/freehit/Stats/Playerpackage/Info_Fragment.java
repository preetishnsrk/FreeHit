package com.debut.ellipsis.freehit.Stats.Playerpackage;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class Info_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_stats_info, container, false);
        /*final ArrayList<PlayerRankingItem> playerRankingItems = new ArrayList<PlayerRankingItem>();
        playerRankingItems.add(new PlayerRankingItem("", "TEST", "ODI", "T20I"));
        playerRankingItems.add(new PlayerRankingItem("Bat", "1", "1", "1"));
        playerRankingItems.add(new PlayerRankingItem("Bowl", "2", "2", "2"));
        playerRankingItems.add(new PlayerRankingItem("All Round", "3", "3", "3"));


        PlayerRankingAdapter adapter = new PlayerRankingAdapter(getContext(), playerRankingItems);
        final ListView listView = (ListView) rootView.findViewById(R.id.player_ranking_list);


        listView.setAdapter(adapter);*/

        return rootView;
    }
}
