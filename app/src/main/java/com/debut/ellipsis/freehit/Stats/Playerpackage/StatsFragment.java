package com.debut.ellipsis.freehit.Stats.Playerpackage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Stats.StatsAdapter;
import com.debut.ellipsis.freehit.Stats.StatsItem;

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
                    Intent i = new Intent(getActivity(), PlayerActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);

                }
                else if (position==1){
                    Intent i = new Intent(getActivity(), TeamActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);

                }
                //github
                else if (position==2){
                    Intent i = new Intent(getActivity(), SeriesActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);

                }
                else if (position==3){
                    Intent i = new Intent(getActivity(), RankingActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);

                }
                else if (position==4){
                    Intent i = new Intent(getActivity(), RecordsActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);

                }
            }
        });

        //ABOVE LINE CREATES NULL POINTER EXCEPTION listView.setAdapter(adapter)


        return rootView;

    }

}
