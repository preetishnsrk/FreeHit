package com.debut.ellipsis.freehit.Matches;



public class MatchCardItem {

    private String mMatchName;

    private String mSeriesName;

    private String mTeam1LogoURL;

    private String mTeam1SN;

    private String mTeam2SN;

    private String mTeam1Score1;

    private String mTeam1Overs;

    private String mTeam2LogoURL;

    private String mTeam2Score1;

    private String mTeam2Overs;

    private String mMatchStatusResult;

    private String mTargetLeadBy;

    private int mtempTeamLogo1;

    private int mtempTeamLogo2;

    private String mMatchSummary;

    private String mTeam1Score2;

    private String mTeam2Score2;


    //WHEN MATCH IS IN PROGRESS
    public MatchCardItem(String MatchName,String SeriesName,String Team1LogoURL,String Team1Score1,String Team1Overs,String Team2LogoURL,String Team2Score1,String Team2Overs,String MatchStatusResult,String TargetLeadBy,String MatchSummary,String Team1SN,String Team2SN)
    {
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mTeam1LogoURL=Team1LogoURL;
        mTeam1Score1=Team1Score1;
        mTeam1Overs=Team1Overs;
        mTeam2LogoURL=Team2LogoURL;
        mTeam2Overs=Team2Overs;
        mTeam2Score1=Team2Score1;
        mTeam1SN = Team1SN;
        mTeam2SN = Team2SN;
        mMatchStatusResult=MatchStatusResult;
        mTargetLeadBy=TargetLeadBy;
        mMatchSummary=MatchSummary;
    }

    //WHEN MATCH IS OVER AND NO TARGET OR LEADY BY FIELD
    public MatchCardItem(String MatchName,String SeriesName,String Team1LogoURL,String Team1Score1,String Team1Overs,String Team2LogoURL,String Team2Score1,String Team2Overs,String MatchStatusResult,String MatchSummary,String Team1SN,String Team2SN)
    {
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mTeam1LogoURL=Team1LogoURL;
        mTeam1Score1=Team1Score1;
        mTeam1Overs=Team1Overs;
        mTeam2LogoURL=Team2LogoURL;
        mTeam2Overs=Team2Overs;
        mTeam1SN = Team1SN;
        mTeam2SN = Team2SN;
        mTeam2Score1=Team2Score1;
        mMatchStatusResult=MatchStatusResult;
        mMatchSummary=MatchSummary;

    }

    //For 2nd innings
    public MatchCardItem(String MatchName,String SeriesName,String TempTeamLogo1,String Team1Score2,String TempTeamLogo2,String Team2Score2,String MatchStatusResult,String TargetLeadBy,String MatchSummary)
    {
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mTeam1LogoURL=TempTeamLogo1;
        mTeam1Score2=Team1Score2;
        mTeam2LogoURL=TempTeamLogo2;
        mTeam2Score2=Team2Score2;
        mMatchStatusResult=MatchStatusResult;
        mTargetLeadBy=TargetLeadBy;
        mMatchSummary=MatchSummary;
    }


    public MatchCardItem(String MatchName,String SeriesName,String Team1LogoURL,String Team1Score1,String Team2LogoURL,String Team2Score1,String MatchStatusResult,String TargetLeadBy,String MatchSummary,String Team1SN,String Team2SN)
    {
        mMatchName=MatchName;
        mSeriesName=SeriesName;
        mTeam1LogoURL=Team1LogoURL;
        mTeam1Score1=Team1Score1;
        mTeam2LogoURL=Team2LogoURL;
        mTeam1SN = Team1SN;
        mTeam2SN = Team2SN;
        mTeam2Score1=Team2Score1;
        mTargetLeadBy=TargetLeadBy;
        mMatchStatusResult=MatchStatusResult;
        mMatchSummary=MatchSummary;

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

    public String getmTeam1Score1() {
        return mTeam1Score1;
    }

    public String getmTeam1Score2() {
        return mTeam1Score2;
    }

    public String getmTeam2Score2() {
        return mTeam1Score2;
    }

    public String getmTeam1Overs() {
        return mTeam1Overs;
    }

    public String getmTeam2LogoURL() {
        return mTeam2LogoURL;
    }

    public String getmTeam2Score1() {
        return mTeam2Score1;
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

    public String getmMatchSummaryPreview() {
        return mMatchSummary;
    }

    public String getmTeam1SN() {
        return mTeam1SN;
    }

    public String getmTeam2SN() {
        return mTeam2SN;
    }

}
