package com.debut.ellipsis.freehit.Stats.Player;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Batting_Fragment extends Fragment{
    String[] gridViewString = {
            "Alram", "Android", "Mobile", "Website", "Profile", "WordPress",
            "Alram", "Android", "Mobile", "Website", "Profile", "WordPress",
            "Alram", "Android", "Mobile", "Website", "Profile", "WordPress",

    } ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stats_batting, container, false);
        GridView androidGridView;
        Batting_Item_adapter adapterViewAndroid = new Batting_Item_adapter(getContext(), gridViewString );
        androidGridView=(GridView)rootView.findViewById(R.id.grid_view_player_batting);
        androidGridView.setBackgroundColor(Color.WHITE);
        androidGridView.setVerticalSpacing(1);
        androidGridView.setHorizontalSpacing(1);

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
