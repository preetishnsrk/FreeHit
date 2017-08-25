package com.debut.ellipsis.freehit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        View rootView= inflater.inflate(R.layout.fragment_stats, container, false);

        final ArrayList<StatsItem> statsItem=new ArrayList<StatsItem>();

        statsItem.add(new StatsItem(R.drawable.player,R.string.search_player));
        statsItem.add(new StatsItem(R.drawable.team,R.string.search_team));
        statsItem.add(new StatsItem(R.drawable.player,R.string.search_team));
        statsItem.add(new StatsItem(R.drawable.player,R.string.search_player));
        statsItem.add(new StatsItem(R.drawable.player,R.string.search_team));


        StatsAdapter adapter = new StatsAdapter(getActivity(), statsItem);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);


        return rootView;

    }

}
