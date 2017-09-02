package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


public class UpcomingMatchCardItem {

    private String mMatchName;

    private String mSeriesName;

    private String mStadiumName;

    private String mTeam1LogoURL;

    private String mTeam1SN;

    private String mTeam2LogoURL;

    private String mTeam2SN;

    private String mMatchDate;

    private String mViewMore;

    private String mSeparator;

    public UpcomingMatchCardItem(String MatchName,String SeriesName,String StadiumName,String Team1LogoURL,String Team1SN,String Team2LogoURL,String Team2SN,String MatchDate,String Separator){
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mStadiumName=StadiumName;
        mTeam1LogoURL=Team1LogoURL;
        mTeam1SN=Team1SN;
        mTeam2LogoURL=Team2LogoURL;
        mTeam2SN=Team2SN;
        mMatchDate=MatchDate;
        mSeparator=Separator;
    }



    public UpcomingMatchCardItem(String ViewMore){
        mViewMore=ViewMore;
    }

    public String getmMatchName() {
        return mMatchName;
    }

    public String getmSeriesName() {
        return mSeriesName;
    }

    public String getmStadiumName() {
        return mStadiumName;
    }

    public String getmTeam1LogoURL() {
        return mTeam1LogoURL;
    }

    public String getmTeam1SN() {
        return mTeam1SN;
    }

    public String getmTeam2LogoURL() {
        return mTeam2LogoURL;
    }

    public String getmTeam2SN() {
        return mTeam2SN;
    }

    public String getmMatchDate() {
        return mMatchDate;
    }

    public String getmViewMore(){
        return mViewMore;
    }
    public String getmSeparator()
    {
        return mSeparator;
    }
    
}
