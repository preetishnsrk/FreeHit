package com.debut.ellipsis.freehit.Matches.UpcomingMatches;

import android.content.Context;
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


public class UpcomingMatchesItemAdapter extends PagerAdapter {

    private Context context;
    private List<UpcomingMatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;
    public String logo_string1;
    public String logo_string2;
    public ImageView imageViewTeam1Logo;
    public ImageView imageViewTeam2Logo;

    public UpcomingMatchesItemAdapter(Context context, List<UpcomingMatchCardItem> dataObjectList) {
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
        View view = this.layoutInflater.inflate(R.layout.upcoming_match_card, container, false);

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name_upcoming);
        textViewMatchName.setText(this.dataObjectList.get(position).getmMatchName());

        TextView Separator = (TextView) view.findViewById(R.id.match_series_separator);
        Separator.setText(this.dataObjectList.get(position).getmSeparator());

        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name_upcoming);
        textViewSeriesName.setText(this.dataObjectList.get(position).getmSeriesName());

        TextView textViewStadiumName = (TextView) view.findViewById(R.id.stadium_upcoming);
        textViewStadiumName.setText(this.dataObjectList.get(position).getmStadiumName());

        imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1_upcoming);


        imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2_upcoming);


        TextView shortName1 = (TextView) view.findViewById(R.id.sn_team_1_upcoming);
        shortName1.setText(this.dataObjectList.get(position).getmTeam1SN());


        TextView shortName2 = (TextView) view.findViewById(R.id.sn_team_2_upcoming);
        shortName2.setText(this.dataObjectList.get(position).getmTeam2SN());

        TextView ViewMore = (TextView) view.findViewById(R.id.upcoming_view_more);
        ViewMore.setText(this.dataObjectList.get(position).getmViewMore());

        TextView MatchDate = (TextView) view.findViewById(R.id.match_date);
        MatchDate.setText(this.dataObjectList.get(position).getmMatchDate());

        final CardView cardView = (CardView) view.findViewById(R.id.card_view);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==5){
                    // Intent to move to list view for Click to view more
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
