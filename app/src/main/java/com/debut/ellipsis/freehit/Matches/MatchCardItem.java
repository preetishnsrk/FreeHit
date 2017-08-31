package com.debut.ellipsis.freehit.Matches;


public class MatchCardItem {

    private static String mMatchName;

    private static String mSeriesName;

    private static String mTeam1LogoURL;

    private static String mTeam1SN;

    private static String mTeam2SN;

    private static String mTeam1Score1;

    public static String mTeam1Overs;

    private static String mTeam2LogoURL;

    private static String mTeam2Score1;

    public static String mTeam2Overs;

    private static String mMatchStatusResult;

    private static String mTargetLeadBy;

    private static String mMatchSummary;

    private static String mTeam1Score2;

    private static String mTeam2Score2;

    private static String mDay;

//FOR TEST MATCHES

    //WHEN TEST MATCH IN 4TH INNINGS IS IN PROGRESS
    public MatchCardItem(String MatchName, String SeriesName, String Team1LogoURL, String Team1Score1,String Team1Score2,  String Team2LogoURL, String Team2Score1,String Team2Score2, String MatchStatusResult, String TargetLeadBy, String MatchSummary, String Team1SN, String Team2SN,String Day) {
        mMatchName = MatchName;
        mSeriesName = SeriesName;
        mTeam1LogoURL = Team1LogoURL;
        mTeam1Score1 = Team1Score1;
        mTeam2LogoURL = Team2LogoURL;
        mTeam2Score1 = Team2Score1;
        mTeam1SN = Team1SN;
        mTeam2SN = Team2SN;
        mMatchStatusResult = MatchStatusResult;
        mTargetLeadBy = TargetLeadBy;
        mMatchSummary = MatchSummary;
        mTeam1Score2 = Team1Score2;
        mTeam2Score2 = Team2Score2;
        mDay = Day;
    }

    //WHEN TEST MATCH IN 3rd INNINGS IS IN PROGRESS
    public MatchCardItem(String MatchName, String SeriesName, String Team1LogoURL, String Team1Score1,String Team1Score2, String Team1Overs, String Team2LogoURL, String Team2Score1,String Team2Score2, String Team2Overs, String MatchStatusResult, String TargetLeadBy, String MatchSummary, String Team1SN, String Team2SN,String Day) {
        mMatchName = MatchName;
        mSeriesName = SeriesName;
        mTeam1LogoURL = Team1LogoURL;
        mTeam1Score1 = Team1Score1;
        mTeam2LogoURL = Team2LogoURL;
        mTeam2Score1 = Team2Score1;
        mTeam1SN = Team1SN;
        mTeam2SN = Team2SN;
        mMatchStatusResult = MatchStatusResult;
        mTargetLeadBy = TargetLeadBy;
        mMatchSummary = MatchSummary;
        mTeam1Score2 = Team1Score2;
        mTeam2Score2 = Team2Score2;
        mDay = Day;
    }

//FOR ODI MATCHES OR TEST MATCHES WITH 2 Innings going on or less

    //WHEN ODI MATCH OR TEST MATCH IN 1st INNINGS  IN PROGRESS
    public MatchCardItem(String MatchName, String SeriesName, String Team1LogoURL, String Team1Score1,String Team1Overs,  String Team2LogoURL, String Team2Score1,String Team2Overs, String MatchStatusResult, String MatchSummary, String Team1SN, String Team2SN,String Day) {
        mMatchName = MatchName;
        mSeriesName = SeriesName;
        mTeam1LogoURL = Team1LogoURL;
        mTeam1Score1 = Team1Score1;
        mTeam2LogoURL = Team2LogoURL;
        mTeam2Score1 = Team2Score1;
        mTeam1SN = Team1SN;
        mTeam2SN = Team2SN;
        mMatchStatusResult = MatchStatusResult;
        mTeam1Overs=Team1Overs;
        mTeam2Overs=Team2Overs;
        mMatchSummary = MatchSummary;
        mDay = Day;
    }

    //WHEN 2nd Innings is going on
    //Team2Score 2 given cause it was matching with the first constructor
    public  MatchCardItem(String MatchName,String  SeriesName,String  Team1LogoURL, String Team1Score1,String Team1Overs, String Team2LogoURL,  String Team2Score1,String Team2Overs,  String MatchStatusResult,  String MatchSummary, String TargetLeadBy, String Team1SN, String Team2SN, String Day,String Team2Score2)
    {
        mMatchName = MatchName;
        mSeriesName = SeriesName;
        mTeam1LogoURL = Team1LogoURL;
        mTeam1Score1 = Team1Score1;
        mTeam2LogoURL = Team2LogoURL;
        mTeam2Score1 = Team2Score1;
        mTeam1SN = Team1SN;
        mTeam2SN = Team2SN;
        mMatchStatusResult = MatchStatusResult;
        mTargetLeadBy = TargetLeadBy;
        mMatchSummary = MatchSummary;
        mTeam1Overs=Team1Overs;
        mTeam2Overs=Team2Overs;
        mDay = Day;
        mTeam2Score2=Team2Score2;

    }





    public static void setMatchName() {
        mMatchName = QueryUtilMatchCard.MatchName;

    }

    public static void setSeriesName() {
        mSeriesName = QueryUtilMatchCard.SeriesName;
    }

    public static void setTeam1LogoURL() {
        mTeam1LogoURL = QueryUtilMatchCard.team1Logo;
    }

    public static void setTeam1Score1(String Team1Score1) {
        mTeam1Score1 = Team1Score1;
    }

    public static void setTeam1Score2(String Team1Score2) {
        mTeam1Score2 = Team1Score2;
    }

    public static void setTeam2Score2(String Team2Score2) {
        mTeam2Score2 = Team2Score2;
    }

    public static void setTeam1Overs() {
        mTeam1Overs = QueryUtilMatchCard.Team1Overs;
    }

    public static void setTeam2LogoURL() {
        mTeam2LogoURL = QueryUtilMatchCard.team2Logo;
    }

    public static void setTeam2Score1(String Team2Score1) {
        mTeam2Score1 = Team2Score1;
    }

    public static void setTeam2Overs() {
        mTeam2Overs = QueryUtilMatchCard.Team2Overs;
    }

    public static void setTeam1SN() {
        mTeam1SN = QueryUtilMatchCard.ShortTeamName1;
    }

    public static void setTeam2SN() {
        mTeam2SN = QueryUtilMatchCard.ShortTeamName2;
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
        return mTeam2Score2;
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

    public String getmMatchSummaryPreview() {
        return mMatchSummary;
    }

    public String getmTeam1SN() {
        return mTeam1SN;
    }

    public String getmTeam2SN() {
        return mTeam2SN;
    }

    public String getmDay(){
        return mDay;
    }


}