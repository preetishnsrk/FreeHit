package com.debut.ellipsis.freehit.Stats.Player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.debut.ellipsis.freehit.R;



public class Bowling_Fragment extends Fragment {
    String[] gridViewString = {
            "Bowling", "Test", "Odi", "T20", "IPL",
            "Innings", "10" ,  "10",  "10",  "10",
            "Over", "10" ,  "10",  "10",  "10",
            "Matches" , "10" ,  "10",  "10",  "10",
            "Runs", "10" ,  "10",  "10",  "10",
            "Wickets","10" ,  "10",  "10",  "10",
            "Best","10" ,  "10",  "10",  "10",
            "3w","10" ,  "10",  "10",  "10",
            "5w","10" ,  "10",  "10",  "10",
            "Avg","10" ,  "10",  "10",  "10",
            "Economy","10" ,  "10",  "10",  "10",
            "Strike Rate","10" ,  "10",  "10",  "10",} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bowling, container, false);
        GridView androidGridView;
        Bowling_item_adapter adapterViewAndroid = new Bowling_item_adapter(getContext(), gridViewString );
        androidGridView=(GridView)rootView.findViewById(R.id.grid_view_bowling);



        androidGridView.setAdapter(adapterViewAndroid);
        setGridViewHeightBasedOnChildren(androidGridView,3);

        return rootView;
    }
    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);


    }
}
