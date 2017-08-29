package com.debut.ellipsis.freehit.Matches;


import android.text.TextUtils;
import android.util.Log;

import com.debut.ellipsis.freehit.News.QueryUtilNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtilMatchCard {

    private static List<MatchCardItem> MatchCards;
    public static String MatchName;
    public static String SeriesName;
    public static String team1Logo;
    public static String ShortTeamName1;
    public static String team2Logo;
    public static String ShortTeamName2;
    public static String Team1score;
    public static String Team2score;
    public static String Team1Overs;
    public static String Team2Overs;

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = QueryUtilMatchCard.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtilNews} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtilMatchCard() {
    }

    /**
     * Query the USGS dataset and return an {@link MatchCardItem} object to represent a list of earthquakes.
     */

    public static List<MatchCardItem> fetchNewsData(String requestUrl) {
        //Adding delay in fetching the data from the server so that the progressBar is displayed for 0.5 seconds
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Log.i(LOG_TAG,"TEST: fetchNewsData() called");
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link news}s object

        // Return the {@link news}
        return extractFeatureFromJson(jsonResponse);
    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return a list of {@link MatchCardItem} objects that has been built up from
     * parsing the given JSON response.
     */
    public static List<MatchCardItem> extractFeatureFromJson(String MatchCardsJSON) {

        MatchCardItem matchCard=null;
        MatchCardItem matchCard1=null;
        int k=0;

        //if the JSON string is empty or null then return early
        if (TextUtils.isEmpty(MatchCardsJSON)) {
            return null;
        }
//        NewsItem news = null;

        // Create an empty ArrayList that we can start adding News to
       MatchCards = new ArrayList<MatchCardItem>(){{
           add(0,new MatchCardItem("a","a","a","a","a","a","a","a","a","a","a","a"));
       }};

        // Try to parse the JSON response string If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the JSON response string and
            // build up a list of News Articles objects with the corresponding data.

            //create a JSONObject from  the JSON response string
            JSONObject basJsonResponse = new JSONObject(MatchCardsJSON);
            JSONObject query=basJsonResponse.getJSONObject("query");
            JSONObject results=query.getJSONObject("results");
            JSONArray ScoreCards=results.getJSONArray("Scorecard");

            for (int i = 0; i < ScoreCards.length() ; i++) {
                JSONObject currentMatch = ScoreCards.getJSONObject(i);

                JSONObject Series=currentMatch.getJSONObject("series");
                if(currentMatch.get("result") instanceof String) {
                    String MatchResult = currentMatch.getString("result");
                }
                else if(currentMatch.get("result") instanceof JSONObject){
                    JSONObject MatchResult = currentMatch.getJSONObject("result");
                    if(MatchResult.length() == 0) {
                        String matchWinner = MatchResult.getString("winner");
                        String byRunsOrWickets = MatchResult.getString("by");
                        String DrawOrInningsWin = MatchResult.getString("how");
                    }
                }
                String SeriesID=Series.getString("series_id");

                SeriesName=Series.getString("series_name");

                MatchName=currentMatch.getString("mn");

                JSONArray Teams=currentMatch.getJSONArray("teams");


                JSONObject CurrentTeam1=Teams.getJSONObject(0);
                String team1ID=CurrentTeam1.getString("i");
                ShortTeamName1=CurrentTeam1.getString("sn");
                JSONObject logo1=CurrentTeam1.getJSONObject("flag");
                team1Logo=logo1.getString("roundlarge");

                JSONObject CurrentTeam2=Teams.getJSONObject(1);
                String team2ID=CurrentTeam2.getString("i");
                ShortTeamName2=CurrentTeam2.getString("sn");
                MatchCardItem.setTeam1SN();
                MatchCardItem.setTeam2SN();

                JSONObject logo2=CurrentTeam2.getJSONObject("flag");
                team2Logo=logo2.getString("roundlarge");

                JSONArray innings=currentMatch.getJSONArray("past_ings");

                //Never used these but let them be for time being
//                JSONObject Innings1=innings.getJSONObject(0);
//                JSONObject Innings2=innings.getJSONObject(1);
//                JSONObject Innings3=innings.getJSONObject(2);
//                JSONObject Innings4=innings.getJSONObject(3);


                String matchStatus=currentMatch.getString("ms");


                String Result=null;
//                if(matchStatus==null)
//                {
//                    Result=matchWinner+byRunsOrWickets+DrawOrInningsWin;
//                }

                String TempTeam1Score=null,TempTeam1Overs=null;
                for(int j=0;j<innings.length();j++) {
                    JSONObject tempinnings = innings.getJSONObject(j);
                    JSONObject CurrentDayOrInnings = tempinnings.getJSONObject("s");
                    String DayorInnings = CurrentDayOrInnings.getString("dm");
                    JSONObject LeadTrailOrTarget = CurrentDayOrInnings.getJSONObject("a");

                    Team1score=null;
                    String Team1Runs=null, Team1Wickets=null;
                    Team1Overs=null;
                    Team1Runs = LeadTrailOrTarget.getString("r");
                    Team1Wickets = LeadTrailOrTarget.getString("w");
                    Team1Overs = LeadTrailOrTarget.getString("o");
                    Team1score = Team1Runs + "/" + Team1Wickets;

                    String LTorTarget;
                    if(LeadTrailOrTarget.has("tl")) {
                        LTorTarget  = LeadTrailOrTarget.getString("tl");
                    }
                    else{
                        LTorTarget = "-";
                    }

                    String Target=null;

                    if(CurrentDayOrInnings.getInt("i")==1||CurrentDayOrInnings.getInt("i")==2) {

                        MatchCardItem.setTeam1Overs();
                        MatchCardItem.setTeam1LogoURL();
                        MatchCardItem.setTeam1Score1(Team1score);
                        MatchCardItem.setTeam2Score1(TempTeam1Score);

                        MatchCardItem.setTeam2LogoURL();
                        MatchCardItem.setTeam2Overs();
                        matchCard = new MatchCardItem(MatchName, SeriesName, team1Logo, Team1score, TempTeam1Overs, team2Logo, TempTeam1Score, Team1Overs, matchStatus, LTorTarget, "HARCODED FOR NOW CAUSE NO PREVIEW OR DESCRIPTION", ShortTeamName1, ShortTeamName2);
                    }

                    if(CurrentDayOrInnings.getInt("i")==3||CurrentDayOrInnings.getInt("i")==4)
                    {
                        if(CurrentDayOrInnings.getInt("i")==4)
                            Target="Target is"+LeadTrailOrTarget.getString("tg");
                        if (matchStatus == null) {
                            matchCard = new MatchCardItem(Team1score,TempTeam1Score, Result, Target, "HARCODED FOR NOW CAUSE NO PREVIEW OR DESCRIPTION");
                            MatchCardItem.setTeam2Score2(TempTeam1Score);
                            MatchCardItem.setTeam1Score2(Team1score);
                            MatchCardItem.mTeam1Overs =


                        }
                        else {
                            MatchCardItem.setTeam2Score2(TempTeam1Score);
                            MatchCardItem.setTeam1Score2(Team1score);
                            matchCard = new MatchCardItem(Team1score,TempTeam1Score, matchStatus, Target, "HARCODED FOR NOW CAUSE NO PREVIEW OR DESCRIPTION");

                        }


                    }
                    TempTeam1Score = Team1score;
                    TempTeam1Overs = Team1Overs;

                    Log.e(LOG_TAG, String.valueOf(j));
                }

                if(ScoreCards.length()>1)
                {
                    MatchCardItem.mTeam1Overs =null;
                    MatchCardItem.mTeam2Overs =null;
                }

                System.out.println(matchCard.getmTeam1Overs());
                System.out.println(matchCard.getmMatchName());
                System.out.println(matchCard.getmSeriesName());
                System.out.println(matchCard.getmTeam1SN());
                MatchCards.add(matchCard);
                }


//            return MatchCards;
        }
        catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the News JSON results", e);
        }


        // Return the list of news Articles
        return MatchCards;
    }
}