package com.debut.ellipsis.freehit.Social.Polls;


import java.util.Arrays;

public class PollCardItem {

    private String pTitle;
    private int pId;
    private String pOptions[]={"","","",""};
    private int pValues[]={0,0,0,0};
    private boolean hasVoted = false;
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
    public int getcId(String choice) {return Arrays.asList(pOptions).indexOf(choice)+1;}
    public boolean gethasVoted(){return hasVoted;}
    public void sethasVoted(boolean value){hasVoted = value;}
}