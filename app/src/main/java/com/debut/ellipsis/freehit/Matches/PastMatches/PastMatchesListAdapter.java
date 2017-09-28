package com.debut.ellipsis.freehit.Matches.PastMatches;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class PastMatchesListAdapter extends RecyclerView.Adapter<PastMatchesListAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView textViewMatchName;
        public TextView textViewSeriesName;
        public TextView textViewStadiumName;
        public ImageView imageViewTeam1Logo;
        public ImageView imageViewTeam2Logo;
        public TextView shortName1;
        public TextView shortName2;
        public TextView innings1Team1;
        public TextView innings2Team1;
        public TextView innings1Team2;
        public TextView innings2Team2;
        public TextView matchResult;
        public TextView MatchDate;
        public RelativeLayout rlcontainer;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textViewMatchName = (TextView) itemView.findViewById(R.id.match_name_past);
            textViewSeriesName = (TextView) itemView.findViewById(R.id.series_name_past);
            textViewStadiumName = (TextView) itemView.findViewById(R.id.stadium_past);
            imageViewTeam1Logo = (ImageView) itemView.findViewById(R.id.team_logo_1_past);
            imageViewTeam2Logo = (ImageView) itemView.findViewById(R.id.team_logo_2_past);
            shortName1 = (TextView) itemView.findViewById(R.id.sn_team_1_past);
            shortName2 = (TextView) itemView.findViewById(R.id.sn_team_2_past);
            innings1Team1 = (TextView) itemView.findViewById(R.id.innings1_team1_past);
            innings2Team1 = (TextView) itemView.findViewById(R.id.innings2_team1_past);
            innings1Team2 = (TextView) itemView.findViewById(R.id.innings1_team2_past);
            innings2Team2 = (TextView) itemView.findViewById(R.id.innings2_team2_past);
            matchResult = (TextView) itemView.findViewById(R.id.match_result_past);
            MatchDate = (TextView) itemView.findViewById(R.id.match_date_past);
            rlcontainer = (RelativeLayout) itemView.findViewById(R.id.rlcontainer);

        }


    }

    // Store a member variable for the contacts
    private List<PastMatchCardItem> mPastMatchCards;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public PastMatchesListAdapter(Context context, List<PastMatchCardItem> PastMatchCards) {
        System.out.println(PastMatchCards);
        mPastMatchCards = PastMatchCards;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PastMatchesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View MatchView = inflater.inflate(R.layout.item_past_match_list, parent, false);

        // Return a new holder instance
        PastMatchesListAdapter.ViewHolder viewHolder = new PastMatchesListAdapter.ViewHolder(MatchView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PastMatchesListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final PastMatchCardItem pastMatchCard = mPastMatchCards.get(position);

        // Set item views based on your views and data model

        TextView textViewMatchName = viewHolder.textViewMatchName;
        textViewMatchName.setText(pastMatchCard.getmMatchName());

        TextView textViewSeriesName = viewHolder.textViewSeriesName;
        textViewSeriesName.setText(pastMatchCard.getmSeriesName());

        TextView textViewStadiumName = viewHolder.textViewStadiumName;
        textViewStadiumName.setText(pastMatchCard.getmStadiumName());

        String logo_string1 = pastMatchCard.getmTeam1LogoURL();

        String logo_string2 = pastMatchCard.getmTeam2LogoURL();

        ImageView imageViewTeam1Logo = viewHolder.imageViewTeam1Logo;

        ImageView imageViewTeam2Logo = viewHolder.imageViewTeam2Logo;

        TextView shortName1 = viewHolder.shortName1;
        shortName1.setText(pastMatchCard.getmTeam1SN());

        TextView shortName2 = viewHolder.shortName2;
        shortName2.setText(pastMatchCard.getmTeam2SN());

        TextView innings1Team1 = viewHolder.innings1Team1;
        innings1Team1.setText(pastMatchCard.getmTeam1Innings1());

        TextView innings2Team1 = viewHolder.innings2Team1;
        innings2Team1.setText(pastMatchCard.getmTeam1Innings2());

        TextView innings1Team2 = viewHolder.innings1Team2;
        innings1Team2.setText(pastMatchCard.getmTeam2Innings1());

        TextView innings2Team2 = viewHolder.innings2Team2;
        innings2Team2.setText(pastMatchCard.getmTeam2Innings2());

        TextView matchResult = viewHolder.matchResult;
        matchResult.setText(pastMatchCard.getmMatchResult());

        TextView MatchDate = viewHolder.MatchDate;
        MatchDate.setText(pastMatchCard.getmMatchDate());

        RelativeLayout RLcontainer = viewHolder.rlcontainer;


        setImage(logo_string1, imageViewTeam1Logo);
        setImage(logo_string2, imageViewTeam2Logo);

        View.OnClickListener mClickListener;


        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent PastMatchScoreCardIntent = new Intent(getContext(), PastMatchScoreCard.class);
                PastMatchScoreCardIntent.putExtra("match_id", pastMatchCard.getmMatchID());
                PastMatchScoreCardIntent.putExtra("match_name", pastMatchCard.getmMatchName());

                    /*ActivityOptions.makeCustomAnimation(context,R.anim.animation_entry,R.anim.animation_exit);*/
                getContext().startActivity(PastMatchScoreCardIntent);

            }
        };
        RLcontainer.setOnClickListener(mClickListener);

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
        return mPastMatchCards.size() - 1;
    }
}
