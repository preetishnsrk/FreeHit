package com.debut.ellipsis.freehit.News;


public class NewsArticleItem {

    private String mheadline;

    private String mdate;

    private String murlofimage;

    private String murlofwebsite;

    private int mnewsID;

    private String mnewsArticle;

    public NewsArticleItem(int newsID, String headline, String date, String urlofimage, String urlofwebsite, String newsArticle) {
        mnewsID = newsID;
        mheadline = headline;
        mdate = date;
        murlofimage = urlofimage;
        murlofwebsite = urlofwebsite;
        mnewsArticle = newsArticle;
    }


    public String getMheadline() {
        return mheadline;
    }

    public String getMdate() {
        return mdate;
    }

    public String getMurlofimage() {
        return murlofimage;
    }

    public String getMurlofwebsite() {
        return murlofwebsite;
    }

    public int getMnewsID() {
        return mnewsID;
    }

    public String getMnewsArticle() {
        return mnewsArticle;
    }

}
