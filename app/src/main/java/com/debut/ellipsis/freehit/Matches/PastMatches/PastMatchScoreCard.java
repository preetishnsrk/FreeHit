package com.debut.ellipsis.freehit.Matches.PastMatches;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.debut.ellipsis.freehit.Matches.CommentaryFragment;
import com.debut.ellipsis.freehit.Matches.HeadToHeadFragment;
import com.debut.ellipsis.freehit.Matches.HeatMapFragment;
import com.debut.ellipsis.freehit.Matches.InfoFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCardFragment;
import com.debut.ellipsis.freehit.Matches.SpiderFragment;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class PastMatchScoreCard extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_match_scorecard);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        PastMatchScoreCard.super.onBackPressed();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new InfoFragment(), "INFO");
        adapter.addFrag(new ScoreCardFragment(), "SCORE CARD");
        adapter.addFrag(new CommentaryFragment(), "COMMENTARY");
        adapter.addFrag(new HeadToHeadFragment(), "HEAD-TO-HEAD");
        adapter.addFrag(new SpiderFragment(), "SPIDER");
        adapter.addFrag(new HeatMapFragment(), "HEAT MAP");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}