package com.debut.ellipsis.freehit;


public class NewsItem {

    private String mheadline;

    private String mdescription;

    private String murl;

//    private Bitmap mimage;
    public NewsItem(String headline,String description,String url)
    {
        mheadline=headline;
        mdescription=description;
        murl=url;
    }

    public String getMheadline()
    {
        return mheadline;
    }

    public String getMdescription()
    {
        return mdescription;
    }

    public String getMurl()
    {
        return murl;
    }
}
