package com.debut.ellipsis.freehit.Stats.Player;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

/**
 * Created by Jayanth on 29-09-2017.
 */

public class Bowling_item_adapter extends BaseAdapter {
    private Context mContext;
    private final String[] gridViewString;
    //private final int[] gridViewImageId;

    public Bowling_item_adapter(Context context, String[] gridViewString) {
        mContext = context;
        // this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
    }

    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.stats_bowling_adapter, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.bowling_text);
            textViewAndroid.setText(gridViewString[i]);
            textViewAndroid.setBackgroundColor(Color.parseColor("#d9d5dc"));

        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }


}
