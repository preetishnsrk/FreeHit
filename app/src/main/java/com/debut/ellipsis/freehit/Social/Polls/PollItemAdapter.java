package com.debut.ellipsis.freehit.Social.Polls;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;


public class PollItemAdapter extends ArrayAdapter {

    private Context context;
    private List<PollCardItem> dataObjectList;
    private LayoutInflater layoutInflater;

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
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        final PollCardItem currentPoll = (PollCardItem) getItem(position);
        final Button submit = (Button) listItemView.findViewById(R.id.poll_submit);
        TextView pollTitle = (TextView) listItemView.findViewById(R.id.poll_title);
        pollTitle.setText(currentPoll.getpTitle());
        editor.putBoolean("has_voted_"+position,currentPoll.gethasVoted());
        final TextView submittedText = (TextView) listItemView.findViewById(R.id.submittedText_poll);
        submittedText.setVisibility(View.GONE);

            final RadioButton option1 = (RadioButton) listItemView.findViewById(R.id.option_1);
            option1.setText(currentPoll.getpOption(0));
        if(currentPoll.getpOption(0).isEmpty())
        {
            option1.setVisibility(View.INVISIBLE);
        }
            final RadioButton option2 = (RadioButton) listItemView.findViewById(R.id.option_2);
            option2.setText(currentPoll.getpOption(1));
        if(currentPoll.getpOption(1).isEmpty())
        {
            option2.setVisibility(View.INVISIBLE);
        }
            final RadioButton option3 = (RadioButton) listItemView.findViewById(R.id.option_3);
            option3.setText(currentPoll.getpOption(2));
        if(currentPoll.getpOption(2).isEmpty())
        {
            option3.setVisibility(View.INVISIBLE);
        }
            final RadioButton option4 = (RadioButton) listItemView.findViewById(R.id.option_4);
            option4.setText(currentPoll.getpOption(3));
        if(currentPoll.getpOption(3).isEmpty())
        {
            option4.setVisibility(View.INVISIBLE);
        }
                final RadioGroup rGroup = (RadioGroup) listItemView.findViewById(R.id.poll_group);
        final View finalListItemView = listItemView;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
                  public void onClick(View v) {
                     int selectedId = rGroup.getCheckedRadioButtonId();
                    RadioButton clicked = (RadioButton) finalListItemView.findViewById(selectedId);
                    int cid = (currentPoll.getcId(clicked.getText().toString()));
                    submittedText.setVisibility(View.VISIBLE);
                    editor.putBoolean("has_voted_"+position,true);
                    option1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);
                    option4.setVisibility(View.GONE);
                    submit.setVisibility(View.GONE);
                final String url = "https://freehit-api.herokuapp.com/polls?id="+currentPoll.getpId()+"&cid="+cid;
                    new Thread() {
                        public void run() {
                            QueryUtilPolls.fetchPollData(url);
                        }
                    }.start();



                    }
               });
        if(prefs.getBoolean("has_voted_"+position,true)){
            submittedText.setVisibility(View.VISIBLE);
            option1.setVisibility(View.GONE);
            option2.setVisibility(View.GONE);
            option3.setVisibility(View.GONE);
            option4.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
        }


    return listItemView;

    }

}

