package com.debut.ellipsis.freehit.Social.Polls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.List;


public class PollItemAdapter extends ArrayAdapter {

    private Context context;
    private List<PollCardItem> dataObjectList;
    private LayoutInflater layoutInflater;

    public PollItemAdapter(Context context, List<PollCardItem> polls) {
        super(context, 0, polls);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.social_polls_list_item, parent, false);
        }


        PollCardItem currentPoll = (PollCardItem) getItem(position);

        TextView pollTitle = (TextView) listItemView.findViewById(R.id.poll_title);
        pollTitle.setText(currentPoll.getpTitle());

        RadioButton option1 = (RadioButton) listItemView.findViewById(R.id.option_1);
        option1.setText(currentPoll.getpOption(0));
        RadioButton option2 = (RadioButton) listItemView.findViewById(R.id.option_2);
        option2.setText(currentPoll.getpOption(1));
        RadioButton option3 = (RadioButton) listItemView.findViewById(R.id.option_3);
        option3.setText(currentPoll.getpOption(2));
        RadioButton option4 = (RadioButton) listItemView.findViewById(R.id.option_4);
        option4.setText(currentPoll.getpOption(3));
//        RadioGroup rGroup = (RadioGroup) view.findViewById(R.id.poll_group);

    return listItemView;
    }
}

