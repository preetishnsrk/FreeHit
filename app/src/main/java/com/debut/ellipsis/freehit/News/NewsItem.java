package com.debut.ellipsis.freehit.News;


public class NewsItem {

    private String mheadline;

    private String mdescription;

    private String murlofimage;

    private String murlofwebsite;

    public NewsItem(String headline, String description, String urlofimage, String urlofwebsite) {
        mheadline = headline;
        mdescription = description;
        murlofimage = urlofimage;
        murlofwebsite = urlofwebsite;
    }

    // Constructor in case of no connection, passing google so app doens't crash (Alternative is to check every time we pass a item, too much work)
    public NewsItem(String headline, String description) {
        mheadline = headline;
        mdescription = description;
        murlofwebsite = "http://www.google.com";

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

    public String getMurlofwebsite() {
        return murlofwebsite;
    }
}
