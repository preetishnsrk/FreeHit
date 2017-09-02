package com.debut.ellipsis.freehit.Matches.PastMatches;


public class PastMatchCardItem {
    private String mMatchName;

    private String mMatchID;

    private String mSeriesName;

    private String mStadiumName;

    private String mTeam1LogoURL;

    private String mTeam1SN;

    private String mTeam1Innings1;

    private String mTeam1Innings2 = "";

    private String mTeam2LogoURL;

    private String mTeam2SN;

    private String mTeam2Innings1;

    private String mTeam2Innings2 = "";

    private String mMatchDate;

    private String mViewMore;

    private String mMatchResult;

    public PastMatchCardItem(String MatchName, String MatchID, String SeriesName, String StadiumName, String Team1LogoURL, String Team1SN, String Team1Innings1, String Team1Innings2, String Team2LogoURL, String Team2SN, String Team2Innings1, String Team2Innings2, String MatchDate, String MatchResult) {
        mMatchName = MatchName;
        mMatchID = MatchID;
        mSeriesName = SeriesName;
        mStadiumName = StadiumName;
        mTeam1LogoURL = Team1LogoURL;
        mTeam1SN = Team1SN;
        mTeam1Innings1 = Team1Innings1;
        mTeam1Innings2 = Team1Innings2;
        mTeam2LogoURL = Team2LogoURL;
        mTeam2SN = Team2SN;
        mTeam2Innings1 = Team2Innings1;
        mTeam2Innings2 = Team2Innings2;
        mMatchDate = MatchDate;
        mMatchResult = MatchResult;
    }


    public PastMatchCardItem(String ViewMore) {
        mViewMore = ViewMore;
    }

    public String getmMatchName() {
        return mMatchName;
    }

    public String getmMatchID() {
        return mMatchID;
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

    public String getmTeam1Innings1() {
        return mTeam1Innings1;
    }

    public String getmTeam1Innings2() {
        return mTeam1Innings2;
    }

    public String getmTeam2LogoURL() {
        return mTeam2LogoURL;
    }

    public String getmTeam2SN() {
        return mTeam2SN;
    }

    public String getmTeam2Innings1() {
        return mTeam2Innings1;
    }

    public String getmTeam2Innings2() {
        return mTeam2Innings2;
    }

    public String getmMatchDate() {
        return mMatchDate;
    }

    public String getmViewMore() {
        return mViewMore;
    }


    public String getmMatchResult() {
        return mMatchResult;
    }
}
