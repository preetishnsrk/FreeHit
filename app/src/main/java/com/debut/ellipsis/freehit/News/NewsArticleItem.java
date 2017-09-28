package com.debut.ellipsis.freehit.News;


public class NewsArticleItem {

    private String mheadline;

    private String mdate;

    private String murlofimage;

    private String mnewsArticle;

    private String mTag1;

    private String mTag2;

    private String mTag3;

    public NewsArticleItem(String headline, String date, String urlofimage, String newsArticle, String Tag1, String Tag2, String Tag3) {
        mheadline = headline;
        mdate = date;
        murlofimage = urlofimage;
        mnewsArticle = newsArticle;
        mTag1 = Tag1;
        mTag2 = Tag2;
        mTag3 = Tag3;
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

    public String getMnewsArticle() {
        return mnewsArticle;
    }

    public String getmTag1() {
        return mTag1;
    }

    public String getmTag2() {
        return mTag2;
    }

    public String getmTag3() {
        return mTag3;
    }

}
