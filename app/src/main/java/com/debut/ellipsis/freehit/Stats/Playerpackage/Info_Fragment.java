package com.debut.ellipsis.freehit.Stats.Playerpackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.debut.ellipsis.freehit.R;

/**
 * Created by Jayanth on 10-09-2017.
 */

public class Info_Fragment extends Fragment {GridView androidGridView;

    String[] gridViewString = {
            "   ","TEST","ODI","T20I","Bat","1","1","1",
            "2","2","2",
            "1","1","1",

    } ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stats_info, container, false);
        CustomGridView adapterViewAndroid = new CustomGridView(getContext(), gridViewString );
        androidGridView=(GridView)rootView.findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);

        return rootView;
    }
}
