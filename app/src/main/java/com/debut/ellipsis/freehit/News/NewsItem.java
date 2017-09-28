package com.debut.ellipsis.freehit.News;


public class NewsItem {

    private String mheadline;

    private String mdescription;

    private String murlofimage;

    private int mnewsID;

    private String mdate;

    private String mTag;

    public NewsItem(int newsID, String headline, String description, String urlofimage, String Date, String Tag) {
        mnewsID = newsID;
        mheadline = headline;
        mdescription = description;
        murlofimage = urlofimage;
        mdate = Date;
        mTag = Tag;
    }

    //Whem there is no image from server
    public NewsItem(int newsID, String headline, String description, String Date, String Tag) {
        mnewsID = newsID;
        mheadline = headline;
        mdescription = description;
        mdate = Date;
        mTag = Tag;
    }

    // Constructor in case of no connection, passing google so app doens't crash (Alternative is to check every time we pass a item, too much work)
    public NewsItem(String headline, String description) {
        mheadline = headline;
        mdescription = description;
    }

    public String getMheadline() {
        return mheadline;
    }

    public String getMdescription() {
        return mdescription;
    }

    public String getMurlofimage() {
        return murlofimage;
    }

    public int getMnewsID() {
        return mnewsID;
    }

    public String getMdate() {
        return mdate;
    }

    public String getMtag() {
        return mTag;
    }
}
