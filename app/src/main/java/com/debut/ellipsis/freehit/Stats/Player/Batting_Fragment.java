package com.debut.ellipsis.freehit.Stats.Playerpackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jayanth on 10-09-2017.
 */

public class Batting_Fragment extends Fragment{
    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";
    public static final String FOURTH_COLUMN="Fourth";
    public static final String FIFTH_COLUMN="Fifth";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stats_batting, container, true);
        ListView listView=(ListView)rootView.findViewById(R.id.listView1);
        populateList();
        Batting_adapter_class adapter=new Batting_adapter_class(get, list);
        listView.setAdapter(adapter);


        return rootView;
    }

    private void populateList() {
        // TODO Auto-generated method stub

        list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> hashmap=new HashMap<String, String>();
        hashmap.put(FIRST_COLUMN, "Batting");
        hashmap.put(SECOND_COLUMN, "Test");
        hashmap.put(THIRD_COLUMN, "ODI");
        hashmap.put(FOURTH_COLUMN, "T20");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap);

        HashMap<String,String> hashmap2=new HashMap<String, String>();
        hashmap2.put(FIRST_COLUMN, "Matches");
        hashmap2.put(SECOND_COLUMN, "10");
        hashmap2.put(THIRD_COLUMN, "10");
        hashmap2.put(FOURTH_COLUMN, "10");
        hashmap.put(FIFTH_COLUMN,"10");
        list.add(hashmap2);

        HashMap<String,String> hashmap3=new HashMap<String, String>();
        hashmap3.put(FIRST_COLUMN, "Allo messaging");
        hashmap3.put(SECOND_COLUMN, "google");
        hashmap3.put(THIRD_COLUMN, "Free");
        hashmap3.put(FOURTH_COLUMN, "App");
        list.add(hashmap3);

        HashMap<String,String> hashmap4=new HashMap<String, String>();
        hashmap4.put(FIRST_COLUMN, "Innings");
        hashmap4.put(SECOND_COLUMN, "5");
        hashmap4.put(THIRD_COLUMN, "5");
        hashmap4.put(FOURTH_COLUMN, "5");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap4);

        HashMap<String,String> hashmap5=new HashMap<String, String>();
        hashmap5.put(FIRST_COLUMN, "Runs");
        hashmap5.put(SECOND_COLUMN, "2");
        hashmap5.put(THIRD_COLUMN, "5");
        hashmap5.put(FOURTH_COLUMN, "55");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap5);

        HashMap<String,String> hashmap6=new HashMap<String, String>();
        hashmap6.put(FIRST_COLUMN, "not outs");
        hashmap6.put(SECOND_COLUMN, "0");
        hashmap6.put(THIRD_COLUMN, "0");
        hashmap6.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");;
        list.add(hashmap6);

        HashMap<String,String> hashmap7=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "highest");
        hashmap7.put(SECOND_COLUMN, "0");
        hashmap7.put(THIRD_COLUMN, "0");
        hashmap7.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap7);
        HashMap<String,String> hashmap8=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "strike rate");
        hashmap7.put(SECOND_COLUMN, "0");
        hashmap7.put(THIRD_COLUMN, "0");
        hashmap7.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap8);
        HashMap<String,String> hashmap9=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "average");
        hashmap7.put(SECOND_COLUMN, "0");
        hashmap7.put(THIRD_COLUMN, "0");
        hashmap7.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap9);
        HashMap<String,String> hashmap10=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "100s");
        hashmap7.put(SECOND_COLUMN, "0");
        hashmap7.put(THIRD_COLUMN, "0");
        hashmap7.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap10);
        HashMap<String,String> hashmap11=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "50s");
        hashmap7.put(SECOND_COLUMN, "0");
        hashmap7.put(THIRD_COLUMN, "0");
        hashmap7.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap11);
        HashMap<String,String> hashmap12=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "4s");
        hashmap7.put(SECOND_COLUMN, "0");
        hashmap7.put(THIRD_COLUMN, "0");
        hashmap7.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap12);
        HashMap<String,String> hashmap13=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "6s");
        hashmap7.put(SECOND_COLUMN, "0");
        hashmap7.put(THIRD_COLUMN, "0");
        hashmap7.put(FOURTH_COLUMN, "0");
        hashmap.put(FIFTH_COLUMN,"IPL");
        list.add(hashmap13);








    }

}
