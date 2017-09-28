package com.debut.ellipsis.freehit.Stats.Rankings;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;


public class RankingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        setContentView(R.layout.rankings_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar_rankings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager_rankings);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_rankings);
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
        RankingActivity.super.onBackPressed();
        overridePendingTransition(0,R.anim.exit_to_right);
    }

    private void setupViewPager(ViewPager viewPager) {
        RankingActivity.ViewPagerAdapter adapter = new RankingActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TeamRankingFragment(), "TEAMS");
        adapter.addFrag(new BattingRankingFragment(), "BATSMEN");
        adapter.addFrag(new BowlingRankingFragment(), "BOLWER");
        adapter.addFrag(new AllrounderRankingFragment(), "ALL ROUNDER");
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
