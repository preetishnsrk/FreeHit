package com.debut.ellipsis.freehit.Matches;


import android.text.TextUtils;
import android.util.Log;

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


    public static String MatchName;
    public static String SeriesName;
    public static String team1Logo;
    public static String ShortTeamName1;
    public static String team2Logo;
    public static String ShortTeamName2;
    public static String Team1score1;
    public static String Team1Overs;
    public static String Team2Overs;
    public static String Team1score2;
    public static String Team2score1;
    public static String Team2score2;


    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = QueryUtilMatchCard.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtilMatchCard} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtilMatchCard() {
    }

    /**
     * Query the MatchesAPI dataset and return an {@link MatchCardItem} object to represent a list of Live/Ongoing Matches.
     */

    public static List<MatchCardItem> fetchLiveMatchData(String requestUrl) {

        Log.i(LOG_TAG, "TEST: fetchNewsData() called");
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link news}s object

        // Return the {@link Matches}
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

        MatchCardItem matchCard = null;

        //if the JSON string is empty or null then return early
        if (TextUtils.isEmpty(MatchCardsJSON)) {
            return null;
        }
//        NewsItem news = null;

        // Create an empty ArrayList that we can start adding News to
        List<MatchCardItem> MatchCards = new ArrayList<>();

        // Try to parse the JSON response string If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the JSON response string and
            // build up a list of News Articles objects with the corresponding data.

            //create a JSONObject from  the JSON response string
            JSONObject basJsonResponse = new JSONObject(MatchCardsJSON);
            /*JSONObject query = basJsonResponse.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject LeadTrailOrTarget = null;
            String Day = null;
            String liveStats=null;
            String MatchResult=null;

            Object intervention = results.get("Scorecard");
          *//*  if (intervention instanceof JSONArray) {
                // It's an array
                JSONArray scorecardsArray = (JSONArray) intervention;


            } else if (intervention instanceof JSONObject) {
                // It's an object
                JSONObject scorecardsObject = (JSONObject) intervention;


                JSONObject Series = scorecardsObject.getJSONObject("series");
                if (scorecardsObject.get("result") instanceof String)
                {
                    MatchResult = scorecardsObject.getString("result");
                } else if (scorecardsObject.get("result") instanceof JSONObject)
                {
                    JSONObject Matchresult = scorecardsObject.getJSONObject("result");
                    if (MatchResult.length() == 0) {
                        String matchWinner = Matchresult.getString("winner");
                        String byRunsOrWickets = Matchresult.getString("by");
                        String DrawOrInningsWin = Matchresult.getString("how");
                    }
                }

                Log.e(LOG_TAG," MATCH RESULT "+MatchResult);
                String SeriesID = Series.getString("series_id");

                SeriesName = Series.getString("series_name");

                MatchName = scorecardsObject.getString("mn");

                JSONArray Teams = scorecardsObject.getJSONArray("teams");


                JSONObject CurrentTeam1 = Teams.getJSONObject(0);
                String team1ID = CurrentTeam1.getString("i");
                ShortTeamName1 = CurrentTeam1.getString("sn");
                JSONObject logo1 = CurrentTeam1.getJSONObject("flag");
                team1Logo = logo1.getString("roundlarge");

                JSONObject CurrentTeam2 = Teams.getJSONObject(1);
                String team2ID = CurrentTeam2.getString("i");
                ShortTeamName2 = CurrentTeam2.getString("sn");
                MatchCardItem.setTeam1SN();
                MatchCardItem.setTeam2SN();

                JSONObject logo2 = CurrentTeam2.getJSONObject("flag");
                team2Logo = logo2.getString("roundlarge");

                JSONArray innings = scorecardsObject.getJSONArray("past_ings");

                String matchStatus = scorecardsObject.getString("ms");

                JSONObject Innings4 = null;
                JSONObject Innings3 = null;
                JSONObject Innings2 = null;
                JSONObject Innings1 = null;


                //For ODI matches or test matches with second innings going on
                if (scorecardsObject.getJSONArray("past_ings").length() <= 2) {
                    if (scorecardsObject.getJSONArray("past_ings").length() == 1) {
                        Innings1 = innings.getJSONObject(0);
                    }
                    if (scorecardsObject.getJSONArray("past_ings").length() == 2) {
                        Innings2 = innings.getJSONObject(0);
                        Innings1 = innings.getJSONObject(1);
                    }

                    if (scorecardsObject.getJSONArray("past_ings").length() <= 2) {
                        if (scorecardsObject.getJSONArray("past_ings").length() == 1) {
                            //1st innings
                            JSONObject CurrentDay = Innings1.getJSONObject("s");
                            if(CurrentDay.has("dm")) {
                                Day = CurrentDay.getString("dm");
                            }
                            LeadTrailOrTarget = CurrentDay.getJSONObject("a");
                            if (LeadTrailOrTarget.getString("i").equals(team1ID)) {
                                Team1score1 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w");
                                MatchCardItem.setTeam1Score2(Team1score1);
                                Team1Overs=LeadTrailOrTarget.getString("o");
                            } else {
                                Team2score1 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w");
                                MatchCardItem.setTeam2Score2(Team2score1);
                                Team2Overs=LeadTrailOrTarget.getString("o");
                            }
                            liveStats=Day+" : "+ CurrentDay.getString("sn");
                            matchCard = new MatchCardItem(MatchName,SeriesName,team1Logo,Team1Overs,Team1Overs,team2Logo,Team2score1,Team2Overs,matchStatus,"HARDCODED",ShortTeamName1,ShortTeamName2,Day);

                        }

                        //2nd innings
                        if (scorecardsObject.getJSONArray("past_ings").length() == 2) {
                            //2nd innings
                            JSONObject CurrentDay = Innings2.getJSONObject("s");
                            if(CurrentDay.has("dm")) {
                                Day = CurrentDay.getString("dm");
                            }
                            LeadTrailOrTarget = CurrentDay.getJSONObject("a");
                            if (LeadTrailOrTarget.getString("i").equals(team1ID)) {
                                Team1score1 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w");
                                MatchCardItem.setTeam1Score2(Team1score1);
                            } else {
                                Team2score1 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w");
                                MatchCardItem.setTeam2Score2(Team2score1);
                            }

                            //1st innings
                            JSONObject CurrentDay1 = Innings1.getJSONObject("s");
                            JSONObject LeadTrailOrTarget1 = CurrentDay1.getJSONObject("a");
                            if (LeadTrailOrTarget1.getString("i").equals(team1ID)) {
                                Team1score1 = LeadTrailOrTarget1.getString("r") + "/" + LeadTrailOrTarget1.getString("w");
                                MatchCardItem.setTeam1Score2(Team1score1);
                            } else {
                                Team2score1 = LeadTrailOrTarget1.getString("r") + "/" + LeadTrailOrTarget1.getString("w");
                                MatchCardItem.setTeam2Score2(Team2score1);
                            }

                            String LTorTarget;
                            if (LeadTrailOrTarget.has("tl")) {
                                LTorTarget = LeadTrailOrTarget.getString("tl");
                            } else {
                                LTorTarget = "-";
                            }

                            String Target;
                            if (LeadTrailOrTarget.has("tg")) {
                                Target = "Target : " + LeadTrailOrTarget.getString("tg");
                            } else {
                                Target = "-";
                            }
                            if (LeadTrailOrTarget.has("tg")) {

                                //Team2Score 2 given cause it was matching with the first constructor
                                matchCard = new MatchCardItem(MatchName, SeriesName, team1Logo, Team1score1,Team1Overs ,team2Logo, Team2score1,Team2Overs, matchStatus, Target, "HARDCODED", ShortTeamName1, ShortTeamName2, Day ,Team2score2);
                            }
                            if (LeadTrailOrTarget.has("tl")) {
                                //Team2Score 2 given cause it was matching with the first constructor
                                matchCard = new MatchCardItem(MatchName, SeriesName, team1Logo, Team1score1,Team1Overs, team2Logo, Team2score1,Team2Overs, matchStatus, LTorTarget, "HARDCODED", ShortTeamName1, ShortTeamName2, Day ,Team2score2);
                            }

                        }

                    }
                }

                //For test matches
                if (scorecardsObject.getJSONArray("past_ings").length() > 2) {
                    if (scorecardsObject.getJSONArray("past_ings").length() == 3) {
                        Innings3 = innings.getJSONObject(0);
                        Innings2 = innings.getJSONObject(1);
                        Innings1 = innings.getJSONObject(2);
                    }
                    if (scorecardsObject.getJSONArray("past_ings").length() == 4) {
                        Innings4 = innings.getJSONObject(0);
                        Innings3 = innings.getJSONObject(1);
                        Innings2 = innings.getJSONObject(2);
                        Innings1 = innings.getJSONObject(3);
                    }

                } else {
                    Innings2 = innings.getJSONObject(0);
                    Innings1 = innings.getJSONObject(1);
                }

                String Result = null;
                *//**//*if(matchStatus==null)
               {
                    Result=matchWinner+byRunsOrWickets+DrawOrInningsWin;
                }*//**//*


                //For Test Match
                if (scorecardsObject.getJSONArray("past_ings").length() > 2) {

                    // If 3rd Innings going on
                    if (scorecardsObject.getJSONArray("past_ings").length() == 3) {
                        //3rd innings
                        JSONObject CurrentDay = Innings3.getJSONObject("s");
                        Day = CurrentDay.getString("dm");
                        LeadTrailOrTarget = CurrentDay.getJSONObject("a");
                        if (LeadTrailOrTarget.getString("i").equals(team1ID)) {
                            Team1score2 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w") + "*";
                            MatchCardItem.setTeam1Score2(Team1score2);
                        } else {
                            Team2score2 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w") + "*";
                            MatchCardItem.setTeam2Score2(Team2score2);
                        }

                        //2nd innings
                        JSONObject CurrentDay1 = Innings2.getJSONObject("s");
                        JSONObject LeadTrailOrTarget1 = CurrentDay1.getJSONObject("a");
                        if (LeadTrailOrTarget1.getString("i").equals(team1ID)) {
                            Team1score1 = LeadTrailOrTarget1.getString("r") + "/" + LeadTrailOrTarget1.getString("w");
                            MatchCardItem.setTeam1Score2(Team1score1);
                        } else {
                            Team2score1 = LeadTrailOrTarget1.getString("r") + "/" + LeadTrailOrTarget1.getString("w");
                            MatchCardItem.setTeam2Score2(Team2score1);
                        }

                        //1st innings
                        JSONObject CurrentDay2 = Innings1.getJSONObject("s");
                        JSONObject LeadTrailOrTarget2 = CurrentDay2.getJSONObject("a");
                        if (LeadTrailOrTarget2.getString("i").equals(team1ID)) {
                            Team1score1 = LeadTrailOrTarget2.getString("r") + "/" + LeadTrailOrTarget2.getString("w");
                            MatchCardItem.setTeam1Score2(Team1score1);
                        } else {
                            Team2score1 = LeadTrailOrTarget2.getString("r") + "/" + LeadTrailOrTarget2.getString("w");
                            MatchCardItem.setTeam2Score2(Team2score1);
                        }
                        String LTorTarget;
                        if (LeadTrailOrTarget.has("tl")) {
                            LTorTarget = LeadTrailOrTarget.getString("tl");
                        } else {
                            LTorTarget = "-";
                        }

                        matchCard = new MatchCardItem(MatchName, SeriesName, team1Logo, Team1score1, Team1score2, team2Logo, Team2score1, Team2score2, matchStatus, LTorTarget, "HARDCODED", ShortTeamName1, ShortTeamName2, Day);


                    }

                    //If 4th Innings going on
                    if (scorecardsObject.getJSONArray("past_ings").length() == 4) {
                        //4th innings
                        JSONObject CurrentDay = Innings4.getJSONObject("s");
                        Day = CurrentDay.getString("dm");
                        LeadTrailOrTarget = CurrentDay.getJSONObject("a");
                        if (LeadTrailOrTarget.getString("i").equals(team1ID)) {
                            Team1score2 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w") + "*";
                            MatchCardItem.setTeam1Score2(Team1score2);
                        } else {
                            Team2score2 = LeadTrailOrTarget.getString("r") + "/" + LeadTrailOrTarget.getString("w") + "*";
                            MatchCardItem.setTeam2Score2(Team2score2);
                        }

                        //3rd innings
                        JSONObject CurrentDay1 = Innings3.getJSONObject("s");
                        JSONObject LeadTrailOrTarget1 = CurrentDay1.getJSONObject("a");
                        if (LeadTrailOrTarget1.getString("i").equals(team1ID)) {
                            Team1score2 = LeadTrailOrTarget1.getString("r") + "/" + LeadTrailOrTarget1.getString("w");
                            MatchCardItem.setTeam1Score2(Team1score2);
                        } else {
                            Team2score2 = LeadTrailOrTarget1.getString("r") + "/" + LeadTrailOrTarget1.getString("w");
                            MatchCardItem.setTeam2Score2(Team2score2);
                        }

                        //2nd innings
                        JSONObject CurrentDay2 = Innings2.getJSONObject("s");
                        JSONObject LeadTrailOrTarget2 = CurrentDay2.getJSONObject("a");
                        if (LeadTrailOrTarget2.getString("i").equals(team1ID)) {
                            Team1score1 = LeadTrailOrTarget2.getString("r") + "/" + LeadTrailOrTarget2.getString("w");
                            MatchCardItem.setTeam1Score2(Team1score1);
                        } else {
                            Team2score1 = LeadTrailOrTarget2.getString("r") + "/" + LeadTrailOrTarget2.getString("w");
                            MatchCardItem.setTeam2Score2(Team2score1);
                        }

                        //1st innings
                        JSONObject CurrentDay3 = Innings1.getJSONObject("s");
                        JSONObject LeadTrailOrTarget3 = CurrentDay3.getJSONObject("a");
                        if (LeadTrailOrTarget3.getString("i").equals(team1ID)) {
                            Team1score1 = LeadTrailOrTarget3.getString("r") + "/" + LeadTrailOrTarget3.getString("w");
                            MatchCardItem.setTeam1Score2(Team1score1);
                        } else {
                            Team2score1 = LeadTrailOrTarget3.getString("r") + "/" + LeadTrailOrTarget3.getString("w");
                            MatchCardItem.setTeam2Score2(Team2score1);
                        }

                        String LTorTarget;
                        if (LeadTrailOrTarget.has("tl")) {
                            LTorTarget = LeadTrailOrTarget.getString("tl");
                        } else {
                            LTorTarget = "-";
                        }

                        String Target;
                        if (LeadTrailOrTarget.has("tg")) {
                            Target = "Target : " + LeadTrailOrTarget.getString("tg");
                        } else {
                            Target = "-";
                        }
                        if (LeadTrailOrTarget.has("tg")) {
                            matchCard = new MatchCardItem(MatchName, SeriesName, team1Logo, Team1score1, Team1score2, team2Logo, Team2score1, Team2score2, matchStatus, Target, "HARDCODED", ShortTeamName1, ShortTeamName2, Day);
                        }
                        if (LeadTrailOrTarget.has("tl")) {
                            matchCard = new MatchCardItem(MatchName, SeriesName, team1Logo, Team1score1, Team1score2, team2Logo, Team2score1, Team2score2, matchStatus, LTorTarget, "HARDCODED", ShortTeamName1, ShortTeamName2, Day);
                        }
                    }


                }

                MatchCards.add(matchCard);
            }*//*
            return MatchCards;*/
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the News JSON results", e);
        }

        // Return the list of OngoingMatches
        return MatchCards;
    }
}