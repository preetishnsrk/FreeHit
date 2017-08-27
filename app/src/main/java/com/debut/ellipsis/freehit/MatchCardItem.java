package com.debut.ellipsis.freehit;



public class MatchCardItem {

    private String mMatchName;

    private String mSeriesName;

    private String mTeam1LogoURL;

    private String mTeam1Score;

    private String mTeam1Overs;

    private String mTeam2LogoURL;

    private String mTeam2Score;

    private String mTeam2Overs;

    private String mMatchStatusResult;

    private String mTargetLeadBy;

    private int mtempTeamLogo1;

    private int mtempTeamLogo2;

    //WHEN MATCH IS IN PROGRESS
    public MatchCardItem(String MatchName,String SeriesName,String Team1LogoURL,String Team1Score,String Team1Overs,String Team2LogoURL,String Team2Score,String Team2Overs,String MatchStatusResult,String TargetLeadBy)
    {
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mTeam1LogoURL=Team1LogoURL;
        mTeam1Score=Team1Score;
        mTeam1Overs=Team1Overs;
        mTeam2LogoURL=Team2LogoURL;
        mTeam2Overs=Team2Overs;
        mTeam2Score=Team2Score;
        mMatchStatusResult=MatchStatusResult;
        mTargetLeadBy=TargetLeadBy;
    }

    //WHEN MATCH IS OVER AND NO TARGET OR LEADY BY FIELD
    public MatchCardItem(String MatchName,String SeriesName,String Team1LogoURL,String Team1Score,String Team1Overs,String Team2LogoURL,String Team2Score,String Team2Overs,String MatchStatusResult)
    {
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mTeam1LogoURL=Team1LogoURL;
        mTeam1Score=Team1Score;
        mTeam1Overs=Team1Overs;
        mTeam2LogoURL=Team2LogoURL;
        mTeam2Overs=Team2Overs;
        mTeam2Score=Team2Score;
        mMatchStatusResult=MatchStatusResult;

    }

    //TEMPORARY METHOD
    public MatchCardItem(String MatchName,String SeriesName,int TempTeamLogo1,String Team1Score,String Team1Overs,int TempTeamLogo2,String Team2Score,String Team2Overs,String MatchStatusResult,String TargetLeadBy)
    {
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mtempTeamLogo1=TempTeamLogo1;
        mTeam1Score=Team1Score;
        mTeam1Overs=Team1Overs;
        mtempTeamLogo2=TempTeamLogo2;
        mTeam2Overs=Team2Overs;
        mTeam2Score=Team2Score;
        mMatchStatusResult=MatchStatusResult;
        mTargetLeadBy=TargetLeadBy;
    }


    public String getmMatchName() {
        return mMatchName;
    }

    public String getmSeriesName() {
        return mSeriesName;
    }

    public String getmTeam1LogoURL() {
        return mTeam1LogoURL;
    }

    public String getmTeam1Score() {
        return mTeam1Score;
    }

    public String getmTeam1Overs() {
        return mTeam1Overs;
    }

    public String getmTeam2LogoURL() {
        return mTeam2LogoURL;
    }

    public String getmTeam2Score() {
        return mTeam2Score;
    }

    public String getmTeam2Overs() {
        return mTeam2Overs;
    }

    public String getmMatchStatusResult() {
        return mMatchStatusResult;
    }

    public String getmTargetLeadBy() {
        return mTargetLeadBy;
    }

    public int getMtempTeamLogo1() {
        return mtempTeamLogo1;
    }

    public int getMtempTeamLogo2() {
        return mtempTeamLogo2;
    }
}
