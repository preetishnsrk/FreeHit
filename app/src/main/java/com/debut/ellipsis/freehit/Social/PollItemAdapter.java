package com.debut.ellipsis.freehit.Social;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.List;

/**
 * Created by nikhil on 27/8/17.
 */

public class PollItemAdapter extends PagerAdapter {

    private Context context;
    private List<PollCardItem> dataObjectList;
    private LayoutInflater layoutInflater;

    PollItemAdapter(Context context, List<PollCardItem> dataObjectList) {
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
        View view = this.layoutInflater.inflate(R.layout.poll_cards, container, false);
        TextView pollTitle = (TextView) view.findViewById(R.id.poll_title);
        pollTitle.setText(this.dataObjectList.get(position).getpTitle());

        RadioButton option1 = (RadioButton) view.findViewById(R.id.option_1);
        option1.setText(this.dataObjectList.get(position).getpOption(0));
        RadioButton option2 = (RadioButton) view.findViewById(R.id.option_2);
        option2.setText(this.dataObjectList.get(position).getpOption(1));
        RadioButton option3 = (RadioButton) view.findViewById(R.id.option_3);
        option3.setText(this.dataObjectList.get(position).getpOption(2));
        RadioButton option4 = (RadioButton) view.findViewById(R.id.option_4);
        option4.setText(this.dataObjectList.get(position).getpOption(3));

        Button submit = (Button) view.findViewById(R.id.poll_submit);
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

