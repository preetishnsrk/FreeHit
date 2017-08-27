package com.debut.ellipsis.freehit;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MatchesItemAdapter extends PagerAdapter {

    private Context context;
    private List<MatchCardItem> dataObjectList;
    private LayoutInflater layoutInflater;

    public MatchesItemAdapter(Context context, List<MatchCardItem> dataObjectList) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.dataObjectList = dataObjectList;
    }

    @Override
    public int getCount() {
        return dataObjectList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.match_cards, container, false);

        TextView textViewMatchName = (TextView) view.findViewById(R.id.match_name);
        textViewMatchName.setText(this.dataObjectList.get(position).getmMatchName());
        TextView textViewSeriesName = (TextView) view.findViewById(R.id.series_name);
        textViewSeriesName.setText(this.dataObjectList.get(position).getmSeriesName());
        ImageView imageViewTeam1Logo = (ImageView) view.findViewById(R.id.team_logo_1);
        imageViewTeam1Logo.setImageResource(this.dataObjectList.get(position).getMtempTeamLogo1());
        TextView textViewSeriesTeam1Score = (TextView) view.findViewById(R.id.score_team_1);
        textViewSeriesTeam1Score.setText(this.dataObjectList.get(position).getmTeam1Score());
        TextView textViewSeriesTeam1Overs = (TextView) view.findViewById(R.id.overs_team_1);
        textViewSeriesTeam1Overs.setText(this.dataObjectList.get(position).getmTeam1Overs());
        ImageView imageViewTeam2Logo = (ImageView) view.findViewById(R.id.team_logo_2);
        imageViewTeam2Logo.setImageResource(this.dataObjectList.get(position).getMtempTeamLogo2());
        TextView textViewSeriesTeam2Score = (TextView) view.findViewById(R.id.score_team_2);
        textViewSeriesTeam2Score.setText(this.dataObjectList.get(position).getmTeam2Score());
        TextView textViewSeriesTeam2Overs = (TextView) view.findViewById(R.id.overs_team_2);
        textViewSeriesTeam2Overs.setText(this.dataObjectList.get(position).getmTeam2Overs());
        TextView textViewMatchStatusResult = (TextView) view.findViewById(R.id.match_status_result);
        textViewMatchStatusResult.setText(this.dataObjectList.get(position).getmMatchStatusResult());
        TextView textViewSeriesTargetLeadBy = (TextView) view.findViewById(R.id.target_leadby);
        textViewSeriesTargetLeadBy.setText(this.dataObjectList.get(position).getmTargetLeadBy());

        TextView textMatchSummaryPreview = (TextView) view.findViewById(R.id.match_preview_summary);
        textMatchSummaryPreview.setText(this.dataObjectList.get(position).getmMatchSummaryPreview());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
