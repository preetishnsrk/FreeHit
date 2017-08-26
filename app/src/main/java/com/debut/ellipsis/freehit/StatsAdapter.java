package com.debut.ellipsis.freehit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class StatsAdapter extends ArrayAdapter<StatsItem> {

    public StatsAdapter(Context context, ArrayList<StatsItem> items) {

        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_stats, parent, false);
        }

        final StatsItem currentItem = getItem(position);

        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.stats_name);
        statsNameTextView.setText(currentItem.getmStatsName());

        ImageView statsIcon = (ImageView) listItemView.findViewById(R.id.stats_icon);
        statsIcon.setImageResource(currentItem.getmStatsIcon());


        return listItemView;
    }
}
