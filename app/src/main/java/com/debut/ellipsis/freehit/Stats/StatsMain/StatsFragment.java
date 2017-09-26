package com.debut.ellipsis.freehit.Stats.StatsMain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Stats.Player.PlayerActivity;
import com.debut.ellipsis.freehit.Stats.Rankings.RankingActivity;
import com.debut.ellipsis.freehit.Stats.Records.RecordsActivity;
import com.debut.ellipsis.freehit.Stats.Series.SeriesActivity;
import com.debut.ellipsis.freehit.Stats.Team.TeamActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class
StatsFragment extends Fragment {


    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.stats_item, container, false);

        final ArrayList<StatsItem> statsItem = new ArrayList<StatsItem>();

        statsItem.add(new StatsItem(R.drawable.player, R.string.search_player));
        statsItem.add(new StatsItem(R.drawable.team, R.string.search_team));
        statsItem.add(new StatsItem(R.drawable.shield, R.string.search_series));
        statsItem.add(new StatsItem(R.drawable.star, R.string.rankings));
        statsItem.add(new StatsItem(R.drawable.records, R.string.records));


        StatsAdapter adapter = new StatsAdapter(getActivity(), statsItem);
        final ListView listView = (ListView) rootView.findViewById(R.id.stats_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent PlayerIntent = new Intent(getActivity(), PlayerActivity.class);
                    startActivity(PlayerIntent);
                    getActivity().overridePendingTransition(0,0);

                }
                else if (position==1){
                    Intent TeamIntent = new Intent(getActivity(), TeamActivity.class);
                    startActivity(TeamIntent);
                    getActivity().overridePendingTransition(0,0);

                }
                else if (position==2){
                    Intent SeriesIntent = new Intent(getActivity(), SeriesActivity.class);
                    startActivity(SeriesIntent);
                    getActivity().overridePendingTransition(0,0);

                }
                else if (position==3){
                    Intent RankingIntent = new Intent(getActivity(), RankingActivity.class);
                    startActivity(RankingIntent);
                    getActivity().overridePendingTransition(0,0);

                }
                else if (position==4){
                    Intent RecordsIntent = new Intent(getActivity(), RecordsActivity.class);
                    startActivity(RecordsIntent);
                    getActivity().overridePendingTransition(0,0);

                }
            }
        });


        return rootView;

    }

}