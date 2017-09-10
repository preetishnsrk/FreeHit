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

import com.debut.ellipsis.freehit.Matches.ScoreCard.CommentaryFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.HeadToHeadFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.HeatMapFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.InfoFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.SpiderFragment;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class PastMatchScoreCard extends AppCompatActivity {

    private String match_id;
    private String match_name;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_match_scorecard);
        overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
        match_id=getIntent().getStringExtra("match_id");
        match_name=getIntent().getStringExtra("match_name");

        setTitle(match_name);

        toolbar = (Toolbar) findViewById(R.id.toolbar_past);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager_past);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_past);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0,R.anim.exit_to_right);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        PastMatchScoreCard.super.onBackPressed();
        overridePendingTransition(0,R.anim.exit_to_right);

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