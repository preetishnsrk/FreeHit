package com.debut.ellipsis.freehit.Social.Polls;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.debut.ellipsis.freehit.News.NewsArticle;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;


public class PollItemAdapter extends ArrayAdapter {

    Fragment frg = SocialPolls.myFragment;

    public PollItemAdapter(Context context, List<PollCardItem> polls) {
        super(context, 0, polls);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.social_polls_list_item, parent, false);
        }

        final SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        final PollCardItem currentPoll = (PollCardItem) getItem(position);
        final Button submit = (Button) listItemView.findViewById(R.id.poll_submit);
        TextView pollTitle = (TextView) listItemView.findViewById(R.id.poll_title);
        pollTitle.setText(currentPoll.getpTitle());

        final RadioButton option1 = (RadioButton) listItemView.findViewById(R.id.option_1);
        option1.setText(currentPoll.getpOption(0));
        if (currentPoll.getpOption(0).isEmpty()) {
            option1.setVisibility(View.INVISIBLE);
        }
        final RadioButton option2 = (RadioButton) listItemView.findViewById(R.id.option_2);
        option2.setText(currentPoll.getpOption(1));
        if (currentPoll.getpOption(1).isEmpty()) {
            option2.setVisibility(View.INVISIBLE);
        }
        final RadioButton option3 = (RadioButton) listItemView.findViewById(R.id.option_3);
        option3.setText(currentPoll.getpOption(2));
        if (currentPoll.getpOption(2).isEmpty()) {
            option3.setVisibility(View.INVISIBLE);
        }
        final RadioButton option4 = (RadioButton) listItemView.findViewById(R.id.option_4);
        option4.setText(currentPoll.getpOption(3));
        if (currentPoll.getpOption(3).isEmpty()) {
            option4.setVisibility(View.INVISIBLE);
        }
        final RadioGroup rGroup = (RadioGroup) listItemView.findViewById(R.id.poll_group);
        final View finalListItemView = listItemView;
        final View finalListItemView1 = listItemView;


        // POLL RESULT
        final View pollresult = listItemView.findViewById(R.id.pollItem_result);
        final TextView option_1 = (TextView) pollresult.findViewById(R.id.option_1);
        option_1.setText(currentPoll.getpOption(0));
        if (currentPoll.getpOption(0).isEmpty()) {
            option_1.setVisibility(View.INVISIBLE);
        }

        final TextView option_2 = (TextView) pollresult.findViewById(R.id.option_2);
        option_2.setText(currentPoll.getpOption(1));
        if (currentPoll.getpOption(1).isEmpty()) {
            option_2.setVisibility(View.INVISIBLE);
        }

        final TextView option_3 = (TextView) pollresult.findViewById(R.id.option_3);
        option_3.setText(currentPoll.getpOption(2));
        if (currentPoll.getpOption(2).isEmpty()) {
            option_3.setVisibility(View.INVISIBLE);
        }

        final TextView option_4 = (TextView) pollresult.findViewById(R.id.option_4);
        option_4.setText(currentPoll.getpOption(3));
        if (currentPoll.getpOption(3).isEmpty()) {
            option_4.setVisibility(View.INVISIBLE);
        }

        int totalVotes = 0;
        for (int i = 0; i < 4; i++) {
            totalVotes += currentPoll.getpValue(i);
        }

        final TextView option_1_percentage = (TextView) pollresult.findViewById(R.id.percentage_option_1);
        if (totalVotes != 0) {
            String percentage1 = String.format("%.2f",(currentPoll.getpValue(0) / (float) totalVotes) * 100);
            option_1_percentage.setText(percentage1 + "%");
            if (currentPoll.getpOption(0).isEmpty()) {
                option_1_percentage.setVisibility(View.INVISIBLE);
            }
        }

        final TextView option_2_percentage = (TextView) pollresult.findViewById(R.id.percentage_option_2);
        if (totalVotes != 0) {
            String percentage2 = String.format("%.2f", (currentPoll.getpValue(1) / (float) totalVotes) * 100);
            option_2_percentage.setText(percentage2 + "%");
            if (currentPoll.getpOption(1).isEmpty()) {
                option_2_percentage.setVisibility(View.INVISIBLE);
            }
        }

        final TextView option_3_percentage = (TextView) pollresult.findViewById(R.id.percentage_option_3);
        if (totalVotes != 0) {
            String percentage3 = String.format("%.2f",(currentPoll.getpValue(2) / (float) totalVotes) * 100);
            option_3_percentage.setText(percentage3 + "%");
            if (currentPoll.getpOption(2).isEmpty()) {
                option_3_percentage.setVisibility(View.INVISIBLE);
            }
        }

        final TextView option_4_percentage = (TextView) pollresult.findViewById(R.id.percentage_option_4);
        if (totalVotes != 0) {
            String percentage4 = String.format("%.2f",(currentPoll.getpValue(3) / (float) totalVotes) * 100);
            option_4_percentage.setText(percentage4 + "%");
            if (currentPoll.getpOption(3).isEmpty()) {
                option_4_percentage.setVisibility(View.INVISIBLE);
            }
        }

        final RoundCornerProgressBar option_1_progressBar = (RoundCornerProgressBar) pollresult.findViewById(R.id.progress_bar_option1);
        option_1_progressBar.setProgressColor(Color.parseColor("#00796b"));
        option_1_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
        if (currentPoll.getpOption(0).isEmpty()) {
            option_1_progressBar.setVisibility(View.INVISIBLE);
        }

        final RoundCornerProgressBar option_2_progressBar = (RoundCornerProgressBar) pollresult.findViewById(R.id.progress_bar_option2);
        option_2_progressBar.setProgressColor(Color.parseColor("#00796b"));
        option_2_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
        if (currentPoll.getpOption(1).isEmpty()) {
            option_2_progressBar.setVisibility(View.INVISIBLE);
        }


        final RoundCornerProgressBar option_3_progressBar = (RoundCornerProgressBar) pollresult.findViewById(R.id.progress_bar_option3);
        option_3_progressBar.setProgressColor(Color.parseColor("#00796b"));
        option_3_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
        if (currentPoll.getpOption(2).isEmpty()) {
            option_3_progressBar.setVisibility(View.INVISIBLE);
        }


        final RoundCornerProgressBar option_4_progressBar = (RoundCornerProgressBar) pollresult.findViewById(R.id.progress_bar_option4);
        option_4_progressBar.setProgressColor(Color.parseColor("#00796b"));
        option_4_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
        if (currentPoll.getpOption(3).isEmpty()) {
            option_4_progressBar.setVisibility(View.INVISIBLE);
        }


        final int finalTotalVotes = totalVotes;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cid = 0;
                if (option1.isChecked() || option2.isChecked() || option3.isChecked() || option4.isChecked()) {
                    int selectedId = rGroup.getCheckedRadioButtonId();
                    RadioButton clicked = (RadioButton) finalListItemView.findViewById(selectedId);
                    cid = (currentPoll.getcId(clicked.getText().toString()));
                    editor.putBoolean("has_voted_" + position, true);
                    editor.apply();

                    final String url = "https://freehit-api.herokuapp.com/polls?id=" + currentPoll.getpId() + "&cid=" + cid;
                    new Thread() {
                        public void run() {
                            QueryUtilPolls.fetchPollData(url);
                        }
                    }.start();
                    
                    option1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);
                    option4.setVisibility(View.GONE);
                    submit.setVisibility(View.GONE);
                    pollresult.setVisibility(View.VISIBLE);
                    option_1.setVisibility(View.VISIBLE);
                    option_2.setVisibility(View.VISIBLE);
                    option_3.setVisibility(View.VISIBLE);
                    option_4.setVisibility(View.VISIBLE);
                    if (!currentPoll.getpOption(0).isEmpty()) {
                        option_1_percentage.setVisibility(View.VISIBLE);
                        option_1_progressBar.setVisibility(View.VISIBLE);
                        option_1_progressBar.setProgressColor(Color.parseColor("#00796b"));
                        option_1_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                        if (finalTotalVotes != 0) {

                            option_2_progressBar.setMax(100);
                            option_2_progressBar.setProgress((currentPoll.getpValue(1) / (float) finalTotalVotes) * 100);
                        }
                    }
                    if (!currentPoll.getpOption(1).isEmpty()) {
                        option_2_percentage.setVisibility(View.VISIBLE);
                        option_2_progressBar.setVisibility(View.VISIBLE);
                        option_2_progressBar.setProgressColor(Color.parseColor("#00796b"));
                        option_2_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                        if (finalTotalVotes != 0) {

                            option_2_progressBar.setMax(100);
                            option_2_progressBar.setProgress((currentPoll.getpValue(1) / (float) finalTotalVotes) * 100);
                        }
                    }
                    if (!currentPoll.getpOption(2).isEmpty()) {
                        option_3_percentage.setVisibility(View.VISIBLE);
                        option_3_progressBar.setVisibility(View.VISIBLE);
                        option_3_progressBar.setProgressColor(Color.parseColor("#00796b"));
                        option_3_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                        if (finalTotalVotes != 0) {

                            option_2_progressBar.setMax(100);
                            option_2_progressBar.setProgress((currentPoll.getpValue(2) / (float) finalTotalVotes) * 100);
                        }
                    }
                    if (!currentPoll.getpOption(3).isEmpty()) {
                        option_4_percentage.setVisibility(View.VISIBLE);
                        option_4_progressBar.setVisibility(View.VISIBLE);
                        option_4_progressBar.setProgressColor(Color.parseColor("#00796b"));
                        option_4_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                        if (finalTotalVotes != 0) {

                            option_4_progressBar.setMax(100);
                            option_4_progressBar.setProgress((currentPoll.getpValue(3) / (float) finalTotalVotes) * 100);
                        }
                    }

                } else {
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
                    View layouttoast = inflater.inflate(R.layout.custom_toast, (ViewGroup) finalListItemView1.findViewById(R.id.toastcustom));
                    ((TextView) layouttoast.findViewById(R.id.texttoast)).setText("PLEASE SELECT AN OPTION FOR POLL " + (position + 1));

                    Toast mytoast = new Toast(getContext());
                    mytoast.setView(layouttoast);
                    mytoast.setDuration(Toast.LENGTH_SHORT);
                    mytoast.show();
                }



            }
        });

        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean name = prefs.getBoolean("has_voted_" + position, false);
        if (name) {
            final String url = "https://freehit-api.herokuapp.com/polls?id=" + currentPoll.getpId();
            new Thread() {
                public void run() {
                    QueryUtilPolls.fetchPollData(url);
                }
            }.start();
            System.out.println(url);
            option1.setVisibility(View.GONE);
            option2.setVisibility(View.GONE);
            option3.setVisibility(View.GONE);
            option4.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
            pollresult.setVisibility(View.VISIBLE);
            option_1.setVisibility(View.VISIBLE);
            option_2.setVisibility(View.VISIBLE);
            option_3.setVisibility(View.VISIBLE);
            option_4.setVisibility(View.VISIBLE);
            if (!currentPoll.getpOption(0).isEmpty()) {
                option_1_percentage.setVisibility(View.VISIBLE);
                option_1_progressBar.setVisibility(View.VISIBLE);
                option_1_progressBar.setProgressColor(Color.parseColor("#00796b"));
                option_1_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                if (totalVotes != 0) {
                    option_1_progressBar.setMax(100);
                    option_1_progressBar.setProgress((currentPoll.getpValue(0) / (float) totalVotes) * 100);
                    System.out.println("Votes value =" + currentPoll.getpValue(0));
                    System.out.println("Total votes =" + totalVotes);
                    System.out.println("percentage value =" + (currentPoll.getpValue(0) / (float) totalVotes) * 100);
                }
            }
            if (!currentPoll.getpOption(1).isEmpty()) {
                option_2_percentage.setVisibility(View.VISIBLE);
                option_2_progressBar.setVisibility(View.VISIBLE);
                option_2_progressBar.setProgressColor(Color.parseColor("#00796b"));
                option_2_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                if (totalVotes != 0) {

                    option_2_progressBar.setMax(100);
                    option_2_progressBar.setProgress((currentPoll.getpValue(1) / (float) totalVotes) * 100);
                }
            }
            if (!currentPoll.getpOption(2).isEmpty()) {
                option_3_percentage.setVisibility(View.VISIBLE);
                option_3_progressBar.setVisibility(View.VISIBLE);
                option_3_progressBar.setProgressColor(Color.parseColor("#00796b"));
                option_3_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                if (totalVotes != 0) {
                    option_3_progressBar.setMax(100);
                    option_3_progressBar.setProgress((currentPoll.getpValue(2) / (float) totalVotes) * 100);
                }
            }
            if (!currentPoll.getpOption(3).isEmpty()) {
                option_4_percentage.setVisibility(View.VISIBLE);
                option_4_progressBar.setVisibility(View.VISIBLE);
                option_4_progressBar.setProgressColor(Color.parseColor("#00796b"));
                option_4_progressBar.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));
                if (totalVotes != 0) {
                    option_4_progressBar.setMax(100);
                    option_4_progressBar.setProgress((currentPoll.getpValue(3) / (float) totalVotes) * 100);
                }
            }

        }


        return listItemView;

    }

}

