package com.debut.ellipsis.freehit.Social;


import android.text.TextUtils;
import android.util.Log;

import com.debut.ellipsis.freehit.News.NewsItem;

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

public class QueryUtilPolls {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = QueryUtilPolls.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtilPolls} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtilPolls() {
    }

    /**
     * Query the USGS dataset and return an {@link NewsItem} object to represent a list of earthquakes.
     */

    public static List<PollCardItem> fetchPollData(String requestUrl) {
        //Adding delay in fetching the data from the server so that the progressBar is displayed for 0.5 seconds
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Log.i(LOG_TAG,"TEST: fetchPollData() called");
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
     * Return a list of {@link NewsItem} objects that has been built up from
     * parsing the given JSON response.
     */
    public static List<PollCardItem> extractFeatureFromJson(String PollsJSON) {

        //if the JSON string is empty or null then return early
        if (TextUtils.isEmpty(PollsJSON)) {
            return null;
        }
//        NewsItem news = null;

        // Create an empty ArrayList that we can start adding News to
        List<PollCardItem> Polls = new ArrayList<>();

        // Try to parse the JSON response string If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the JSON response string and
            // build up a list of News Articles objects with the corresponding data.

            //create a JSONObject from  the JSON response string
            JSONObject currentArticle = new JSONObject(PollsJSON);

                String title=currentArticle.getString("title");

                int id=currentArticle.getInt("id");

                JSONArray Jsonoptions = currentArticle.getJSONArray("options");
                String [] options = new String[Jsonoptions.length()];
                for(int j=0;j<Jsonoptions.length();j++){ options[j] = Jsonoptions.getString(j);}

                JSONArray Jsonvalues = currentArticle.getJSONArray("votes");
                int[] values = new int[Jsonvalues.length()];
                for(int j=0;j<Jsonvalues.length();j++){ values[j] = Jsonvalues.getInt(j);}

                PollCardItem poll=new PollCardItem(title,id,options,values);
                Polls.add(poll);
            Polls.add(poll);
            Polls.add(poll);


//                Log.e(LOG_TAG, String.valueOf(j));
            return Polls;
        }

            catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the News JSON results", e);
        }
        return Polls;
    }
}
