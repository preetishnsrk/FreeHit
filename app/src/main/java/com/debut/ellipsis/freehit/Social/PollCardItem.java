package com.debut.ellipsis.freehit.Social;

/**
 * Created by nikhil on 27/8/17.
 */

public class PollCardItem {

    private String pTitle;
    private int pId;
    private String pOptions[];
    private int pValues[];

    public PollCardItem(String title, int id, String[] options, int[] values) {
        pTitle = title;
        pId = id;
        pOptions = options;
        pValues = values;
    }

    public String getpTitle() {
        return pTitle;
    }

    public int getpId() {
        return pId;
    }

    public String getpOption(int index) {
        return pOptions[index];
    }

    public int getpValue(int index) {
        return pValues[index];
    }

}