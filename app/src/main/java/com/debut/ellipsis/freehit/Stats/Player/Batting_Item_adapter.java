package com.debut.ellipsis.freehit.Stats.Player;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
/**
 * Created by Jayanth on 27-09-2017.
 */

public class Batting_Item_adapter extends  BaseAdapter {
    private Context mContext;
    private final String[] gridViewString;
    //private final int[] gridViewImageId;

    public Batting_Item_adapter(Context context, String[] gridViewString) {
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
            gridViewAndroid = inflater.inflate(R.layout.stats_batting_adapter, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            textViewAndroid.setText(gridViewString[i]);

        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }

}
