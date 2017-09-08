package com.debut.ellipsis.freehit.Matches.PastMatches;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
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

public class PastMatchCardItemAdapter extends PagerAdapter {

    private static final String DATE_SEPARATOR = "T";

    private Context context;
    private List<PastMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    public String logo_string1;
    public String logo_string2;
    public ImageView imageViewTeam1Logo;
    public ImageView imageViewTeam2Logo;

    public PastMatchCardItemAdapter(Context context, List<PastMatchCardItem> dataObjectList) {
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
    public Object instantiateItem(ViewGroup container,final int position) {
        View view = this.layoutInflater.inflate(R.layout.past_match_card, container, false);

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name_past);
        textViewMatchName.setText(this.dataObjectList.get(position).getmMatchName());


        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name_past);
        textViewSeriesName.setText(this.dataObjectList.get(position).getmSeriesName());

        TextView textViewStadiumName = (TextView) view.findViewById(R.id.stadium_past);
        textViewStadiumName.setText(this.dataObjectList.get(position).getmStadiumName());

        imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1_past);


        imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2_past);


        TextView shortName1 = (TextView) view.findViewById(R.id.sn_team_1_past);
        shortName1.setText(this.dataObjectList.get(position).getmTeam1SN());

        TextView team1Innings1 = (TextView) view.findViewById(R.id.innings1_team1_past);
        team1Innings1.setText(this.dataObjectList.get(position).getmTeam1Innings1());

        TextView team1Innings2 = (TextView) view.findViewById(R.id.innings2_team1_past);
        team1Innings2.setText(this.dataObjectList.get(position).getmTeam1Innings2());


        TextView shortName2 = (TextView) view.findViewById(R.id.sn_team_2_past);
        shortName2.setText(this.dataObjectList.get(position).getmTeam2SN());

        TextView team2Innings1 = (TextView) view.findViewById(R.id.innings1_team2_past);
        team2Innings1.setText(this.dataObjectList.get(position).getmTeam2Innings1());

        TextView team2Innings2 = (TextView) view.findViewById(R.id.innings2_team2_past);
        team2Innings2.setText(this.dataObjectList.get(position).getmTeam2Innings2());


        TextView MatchResult = (TextView) view.findViewById(R.id.match_result_past);
        MatchResult.setText(this.dataObjectList.get(position).getmMatchResult());

        String originalMatchDate = this.dataObjectList.get(position).getmMatchDate();

//        // Check whether the originalLocation string contains the " of " text
//        if (originalMatchDate.contains(DATE_SEPARATOR)) {
//            // Split the string into different parts (as an array of Strings)
//            // based on the "T" text. We expect an array of 2 Strings, where
//            // the first String will be "2017-09-04" and the second String will be "04:00:00.000Z".
//            String[] parts = originalMatchDate.split(DATE_SEPARATOR);
//            // originalMatchDate should be "2017-09-04"--> "04 Sep 2017"
//            originalMatchDate = parts[0];
//
//        }





        TextView ViewMore = (TextView) view.findViewById(R.id.past_view_more);
        ViewMore.setText(this.dataObjectList.get(position).getmViewMore());

        TextView MatchDate = (TextView) view.findViewById(R.id.match_date_past);
        MatchDate.setText(this.dataObjectList.get(position).getmMatchDate());

        final CardView cardView = (CardView) view.findViewById(R.id.card_view);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==5){
                    // Intent to move to list view for Click to view more
                    // Create a new intent to open the {@link UpcomingMatchesActivity}
                    Intent PastIntent = new Intent(context, PastMatchesActivity.class);

                    // Start the new activity
                    context.startActivity(PastIntent);
                }
                else {
                    Intent PastMatchScoreCardIntent = new Intent(context, PastMatchScoreCard.class);
                    PastMatchScoreCardIntent.putExtra("match_id", dataObjectList.get(position).getmMatchID());
                    PastMatchScoreCardIntent.putExtra("match_name", dataObjectList.get(position).getmMatchName());
                    /*ActivityOptions.makeCustomAnimation(context,R.anim.animation_entry,R.anim.animation_exit);*/
                    context.startActivity(PastMatchScoreCardIntent);
                }
            }
        });

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