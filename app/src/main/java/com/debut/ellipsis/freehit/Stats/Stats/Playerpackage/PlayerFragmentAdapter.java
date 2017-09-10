package com.debut.ellipsis.freehit.Stats.Stats.Playerpackage;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Jayanth on 10-09-2017.
 */

public class PlayerFragmentAdapter extends FragmentPagerAdapter {


    public PlayerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new Info_Fragment();
            case 1:
                // Games fragment activity
                return new Batting_Fragment();
            case 2:
                // Movies fragment activity
                return new Bowling_Fragment();
            case 3:
                return new CareerFragment();

        }

        return null;
    }



    @Override
    public int getCount() {
        return 5;
    }
}
