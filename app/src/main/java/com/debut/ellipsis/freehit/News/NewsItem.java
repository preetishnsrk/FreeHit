package com.debut.ellipsis.freehit.News;


public class NewsItem {

    private String mheadline;

    private String mdescription;

    private String murlofimage;

    private String murlofwebsite;

    public NewsItem(String headline,String description,String urlofimage,String urlofwebsite)
    {
        mheadline=headline;
        mdescription=description;
        murlofimage=urlofimage;
        murlofwebsite=urlofwebsite;
    }

    public String getMheadline()
    {
        return mheadline;
    }

    public String getMdescription()
    {
        return mdescription;
    }

    public String getMurlofimage()
    {
        return murlofimage;
    }

    public String getMurlofwebsite()
    {
        return  murlofwebsite;
    }
}
