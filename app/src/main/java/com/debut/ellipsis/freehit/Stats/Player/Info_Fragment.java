package com.debut.ellipsis.freehit.Stats.Player;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class Info_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_stats_info, container, false);


        return rootView;
    }
}
