package com.debut.ellipsis.freehit.Matches.LiveMatches;


import android.text.TextUtils;
import android.util.Log;

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

public class QueryUtilLiveMatchCard {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = com.debut.ellipsis.freehit.Matches.LiveMatches.QueryUtilLiveMatchCard.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link com.debut.ellipsis.freehit.Matches.LiveMatches.QueryUtilLiveMatchCard} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtilLiveMatchCard() {
    }

    /**
     * Query the LiveMatchesAPI and return an {@link LiveMatchCardItem} object to represent a list of Live Matches.
     */

    public static List<LiveMatchCardItem> fetchLiveMatchData(String requestUrl) {


        Log.i(LOG_TAG, "TEST: fetchLiveMatchData() called");
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link LiveMatch}es object

        // Return the {@link LiveMatch}es
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
            urlConnection.setReadTimeout(60000 /* milliseconds */);
            urlConnection.setConnectTimeout(60000  /* milliseconds */);
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
     * Return a list of {@link LiveMatchCardItem} objects that has been built up from
     * parsing the given JSON response.
     */
    public static List<LiveMatchCardItem> extractFeatureFromJson(String UpcomingMatchesJSON) {

        //if the JSON string is empty or null then return early
        if (TextUtils.isEmpty(UpcomingMatchesJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding LiveMatches to
        List<LiveMatchCardItem> LiveMatches = new ArrayList<>();

        // Try to parse the JSON response string If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the JSON response string and
            // build up a list of News Articles objects with the corresponding data.

            //create a JSONObject from  the JSON response string
            JSONObject basJsonResponse = new JSONObject(UpcomingMatchesJSON);
            JSONArray result=basJsonResponse.getJSONArray("result");

            for (int i = 0; i < 5; i++) {

                JSONObject currentLiveMatch=result.getJSONObject(i);

                // Extract the value for the key called "ndid"
                String match_id = currentLiveMatch.getString("ndid");

                // Extract the value for the key called "tour"
                String series_name = currentLiveMatch.getString("tour");

                // Extract the value for the key called "match"
                String match_name = currentLiveMatch.getString("title");

                // Extract the value for the key called "stadium"
                String stadium_name = currentLiveMatch.getString("stadium");

//                String match_date=currentLiveMatch.getString("date");


                JSONObject team1info=currentLiveMatch.getJSONObject("team1info");

                // Extract the value for the key called "sn"
                String team1_short_name = team1info.getString("sn");

                // Extract the value for the key called "image"
                String team1_logo_URL = team1info.getString("image");

                // Extract the value for the key called "inn1"
                String team1_innings1 = team1info.getString("inn1");

                // Extract the value for the key called "inn2"
                String team1_innings2 = team1info.getString("inn2");

                JSONObject team2info=currentLiveMatch.getJSONObject("team2info");

                // Extract the value for the key called "sn" (
                String team2_short_name = team2info.getString("sn");

                // Extract the value for the key called "image"
                String team2_logo_URL = team2info.getString("image");

                // Extract the value for the key called "inn1"
                String team2_innings1 = team1info.getString("inn1");

                // Extract the value for the key called "inn2"
                String team2_innings2 = team1info.getString("inn2");

                // Extract the value for the key called "mresult"
                String match_result_target_trailBy_leadBy = currentLiveMatch.getString("mresult");

                // Extract the value for the key called "day"
//                String match_day = currentLiveMatch.getString("day");
                String match_day = "";
                String match_date = "";



                // Create a new {@link UpcomingMatches} object
                // and url from the JSON response.
                LiveMatchCardItem live_match = new LiveMatchCardItem(match_name,match_id,series_name,stadium_name,team1_logo_URL,team1_short_name,team1_innings1,team1_innings2,team2_logo_URL,team2_short_name,team2_innings1,team2_innings2,match_date,match_result_target_trailBy_leadBy,match_day,"-");
                LiveMatches.add(live_match);

            }
            LiveMatches.add(new LiveMatchCardItem("Click to view more"));
//                Log.e(LOG_TAG, String.valueOf(j));
            return LiveMatches;
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the UpcomingMatches JSON results", e);
        }
        return LiveMatches;
    }
}