package com.debut.ellipsis.freehit.Matches;

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


public class MatchesItemAdapter extends PagerAdapter {

    private Context context;
    private List<MatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    public String logo_string1;
    public String logo_string2;
    public ImageView imageViewTeam1Logo;
    public ImageView imageViewTeam2Logo;

    public MatchesItemAdapter(Context context, List<MatchCardItem> dataObjectList) {
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
        View view = this.layoutInflater.inflate(R.layout.match_cards, container, false);

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name);
        textViewMatchName.setText(this.dataObjectList.get(position).getmMatchName());
        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name);
        textViewSeriesName.setText(this.dataObjectList.get(position).getmSeriesName());
        imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1);
//        imageViewTeam1Logo.setImageResource(this.dataObjectList.get(position).getMtempTeamLogo1());
        TextView textViewSeriesTeam1Score = (TextView) view.findViewById(R.id.score_team_1);
        textViewSeriesTeam1Score.setText(this.dataObjectList.get(position).getmTeam1Score1());

        TextView textViewSeriesTeam1Score2 = (TextView) view.findViewById(R.id.score2_team_1);
        textViewSeriesTeam1Score2.setText(this.dataObjectList.get(position).getmTeam1Score2());

        TextView textViewSeriesTeam1Overs = (TextView) view.findViewById(R.id.overs_team_1);
        textViewSeriesTeam1Overs.setText(this.dataObjectList.get(position).getmTeam1Overs());
        imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2);
//        imageViewTeam2Logo.setImageResource(this.dataObjectList.get(position).getMtempTeamLogo2());
        TextView textViewSeriesTeam2Score = (TextView) view.findViewById(R.id.score_team_2);
        textViewSeriesTeam2Score.setText(this.dataObjectList.get(position).getmTeam2Score1());

        TextView textViewSeriesTeam2Score2 = (TextView) view.findViewById(R.id.score2_team_2);
        textViewSeriesTeam2Score2.setText(this.dataObjectList.get(position).getmTeam2Score2());

        TextView textViewSeriesTeam2Overs = (TextView) view.findViewById(R.id.overs_team_2);
        textViewSeriesTeam2Overs.setText(this.dataObjectList.get(position).getmTeam2Overs());
        TextView textViewMatchStatusResult = (TextView) view.findViewById(R.id.match_status_result);
        textViewMatchStatusResult.setText(this.dataObjectList.get(position).getmMatchStatusResult());
        TextView textViewSeriesTargetLeadBy = (TextView) view.findViewById(R.id.target_leadby);
        textViewSeriesTargetLeadBy.setText(this.dataObjectList.get(position).getmTargetLeadBy());

        TextView textMatchSummaryPreview = (TextView) view.findViewById(R.id.match_preview_summary);
        textMatchSummaryPreview.setText(this.dataObjectList.get(position).getmMatchSummaryPreview());

        TextView shortName1 = (TextView) view.findViewById(R.id.shortname_team_1);
        shortName1.setText(this.dataObjectList.get(position).getmTeam1SN());
        TextView shortName2 = (TextView) view.findViewById(R.id.shortname_team_2);
        shortName2.setText(this.dataObjectList.get(position).getmTeam2SN());

        TextView Day = (TextView) view.findViewById(R.id.day);
        Day.setText(this.dataObjectList.get(position).getmDay());

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
