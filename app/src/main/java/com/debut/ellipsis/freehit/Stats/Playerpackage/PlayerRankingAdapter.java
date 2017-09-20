package com.debut.ellipsis.freehit.Stats.Playerpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

public class PlayerRankingAdapter extends ArrayAdapter<PlayerRankingItem> {

    public PlayerRankingAdapter(Context context, ArrayList<PlayerRankingItem> items) {

        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.player_individual_ranking, parent, false);
        }

        final PlayerRankingItem currentItem = getItem(position);


        TextView first_column = (TextView) listItemView.findViewById(R.id.first_column);
        first_column.setText(currentItem.getMfirst_column());


        TextView second_column = (TextView) listItemView.findViewById(R.id.second_column);
        second_column.setText(currentItem.getMsecond_column());


        TextView third_column = (TextView) listItemView.findViewById(R.id.third_column);
        third_column.setText(currentItem.getMthird_column());


        TextView fourth_column = (TextView) listItemView.findViewById(R.id.fourth_column);
        fourth_column.setText(currentItem.getMfourth_column());




        return listItemView;
    }
}
