package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
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

public class UpcomingMatchesListAdapter extends RecyclerView.Adapter<UpcomingMatchesListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView textViewMatchName;
        public TextView textViewSeriesName;
        public TextView textViewStadiumName;
        public String logo_string1;
        public String logo_string2;
        public ImageView imageViewTeam1Logo;
        public ImageView imageViewTeam2Logo;
        public TextView shortName1;
        public TextView shortName2;
        public TextView MatchDate;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textViewMatchName = (TextView) itemView.findViewById(R.id.match_name_upcoming);
            textViewSeriesName = (TextView) itemView.findViewById(R.id.series_name_upcoming);
            textViewStadiumName = (TextView) itemView.findViewById(R.id.stadium_upcoming);
            imageViewTeam1Logo = (ImageView) itemView.findViewById(R.id.team_logo_1_upcoming);
            imageViewTeam2Logo = (ImageView) itemView.findViewById(R.id.team_logo_2_upcoming);
            shortName1 = (TextView) itemView.findViewById(R.id.sn_team_1_upcoming);
            shortName2 = (TextView) itemView.findViewById(R.id.sn_team_2_upcoming);
            MatchDate = (TextView) itemView.findViewById(R.id.match_date_upcoming);


        }
    }

    // Store a member variable for the contacts
    private List<UpcomingMatchCardItem> mUpcomingMatchCards;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public UpcomingMatchesListAdapter(Context context, List<UpcomingMatchCardItem> UpcomingMatchCards) {
        System.out.println(UpcomingMatchCards);
        mUpcomingMatchCards = UpcomingMatchCards;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public UpcomingMatchesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_upcoming_match_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(UpcomingMatchesListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        UpcomingMatchCardItem upcomingMatchCards = mUpcomingMatchCards.get(position);

        // Set item views based on your views and data model

        TextView textViewMatchName = viewHolder.textViewMatchName;
        textViewMatchName.setText(upcomingMatchCards.getmMatchName());

        TextView textViewSeriesName = viewHolder.textViewSeriesName;
        textViewSeriesName.setText(upcomingMatchCards.getmSeriesName());

        TextView textViewStadiumName = viewHolder.textViewStadiumName;
        textViewStadiumName.setText(upcomingMatchCards.getmStadiumName());

        String logo_string1 = upcomingMatchCards.getmTeam1LogoURL();

        String logo_string2 = upcomingMatchCards.getmTeam2LogoURL();

        ImageView imageViewTeam1Logo = viewHolder.imageViewTeam1Logo;

        ImageView imageViewTeam2Logo = viewHolder.imageViewTeam2Logo;

        TextView shortName1 = viewHolder.shortName1;
        shortName1.setText(upcomingMatchCards.getmTeam1SN());

        TextView shortName2 = viewHolder.shortName2;
        shortName2.setText(upcomingMatchCards.getmTeam2SN());

        TextView MatchDate = viewHolder.MatchDate;
        MatchDate.setText(upcomingMatchCards.getmMatchDate());

        setImage(logo_string1, imageViewTeam1Logo);
        setImage(logo_string2, imageViewTeam2Logo);

    }

    private void setImage(String url, ImageView imageview) {
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

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mUpcomingMatchCards.size()-1;
    }
}
