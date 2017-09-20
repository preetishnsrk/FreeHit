package com.debut.ellipsis.freehit.Stats.Playerpackage;


public class PlayerRankingItem {

    private String mfirst_column;
    private String msecond_column;
    private String mthird_column;
    private String mfourth_column;

    public PlayerRankingItem(String first_column, String second_column, String third_column,String fourth_column)
    {
        mfirst_column=first_column;
        msecond_column=second_column;
        mthird_column=third_column;
        mfourth_column=fourth_column;
    }

    public String getMfirst_column() {
        return mfirst_column;
    }

    public String getMsecond_column() {
        return msecond_column;
    }

    public String getMthird_column() {
        return mthird_column;
    }

    public String getMfourth_column() {
        return mfourth_column;
    }
}

