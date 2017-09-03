package com.debut.ellipsis.freehit.Matches.LiveMatches;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class LiveMatchCardAdapter  extends PagerAdapter {

    private Context context;
    private List<LiveMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    public String logo_string1;
    public String logo_string2;
    public ImageView imageViewTeam1Logo;
    public ImageView imageViewTeam2Logo;

    public LiveMatchCardAdapter(Context context, List<LiveMatchCardItem> dataObjectList) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataObjectList = dataObjectList;
    }

    @Override
    public int getCount() {
        return dataObjectList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.live_match_card, container, false);

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name_live);
        textViewMatchName.setText(this.dataObjectList.get(position).getmMatchName());

        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name_live);
        textViewSeriesName.setText(this.dataObjectList.get(position).getmSeriesName());

        TextView Separator = (TextView) view.findViewById(R.id.match_series_separator_live);
        Separator.setText(this.dataObjectList.get(position).getmSeparator());

        TextView textViewStadiumName = (TextView) view.findViewById(R.id.stadium_live);
        textViewStadiumName.setText(this.dataObjectList.get(position).getmStadiumName());

        imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1_live);


        imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2_live);


        TextView shortName1 = (TextView) view.findViewById(R.id.sn_team_1_live);
        shortName1.setText(this.dataObjectList.get(position).getmTeam1SN());

        TextView team1Innings1 = (TextView) view.findViewById(R.id.innings1_team1_live);
        team1Innings1.setText(this.dataObjectList.get(position).getmTeam1Innings1());

        TextView team1Innings2 = (TextView) view.findViewById(R.id.innings2_team1_live);
        team1Innings2.setText(this.dataObjectList.get(position).getmTeam1Innings2());


        TextView shortName2 = (TextView) view.findViewById(R.id.sn_team_2_live);
        shortName2.setText(this.dataObjectList.get(position).getmTeam2SN());

        TextView team2Innings1 = (TextView) view.findViewById(R.id.innings1_team2_live);
        team2Innings1.setText(this.dataObjectList.get(position).getmTeam2Innings1());

        TextView team2Innings2 = (TextView) view.findViewById(R.id.innings2_team2_live);
        team2Innings2.setText(this.dataObjectList.get(position).getmTeam2Innings2());

        TextView ViewMore = (TextView) view.findViewById(R.id.live_view_more);
        ViewMore.setText(this.dataObjectList.get(position).getmViewMore());

        TextView MatchResult = (TextView) view.findViewById(R.id.match_target_trail_leadBy_live);
        MatchResult.setText(this.dataObjectList.get(position).getmResultOrTargetOrTrailByOrLeadBy());

        TextView MatchDate = (TextView) view.findViewById(R.id.match_date_live);
        MatchDate.setText(this.dataObjectList.get(position).getmMatchDate());

        TextView MatchDay = (TextView) view.findViewById(R.id.Day_for_test_match_live);
        MatchDay.setText(this.dataObjectList.get(position).getmDayForTestMatch());

        // Initializing Logo URLS
        logo_string1 = this.dataObjectList.get(position).getmTeam1LogoURL();
        logo_string2 = this.dataObjectList.get(position).getmTeam2LogoURL();

        setImage(logo_string1, imageViewTeam1Logo);
        setImage(logo_string2, imageViewTeam2Logo);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setImage(String url, ImageView imageview) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        imageLoader.displayImage(url, imageview, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

    }
}
