package com.example.rachel.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rachel on 3/10/16.
 */


public class RetrievePictures {
    private String bioguide_id;
    private String latitude;
    private String longitude;
    static ArrayList<String> arrays;



    public RetrievePictures(){
        ArrayList<String> bioguide_id_array = RetrieveFeedTask.bioguide_id_array;
        int i = bioguide_id_array.size();
        for (int j=0; j < i; j++) {
            bioguide_id = bioguide_id_array.get(j);
        }
    }

    protected void callAPI(){
        new getAPI().execute();
    }

    private class getAPI extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            //        String email = emailText.getText().toString();
            String API_URL = "https://theunitedstates.io";
            String PATH = "/images/congress";
            String SIZE = "/original/";
            String BIOGUIDE = bioguide_id;
            // Do some validation here
            try {
                URL url = new URL(API_URL + PATH + SIZE + BIOGUIDE + ".jpg");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String result) {
            System.out.println(result);
            parseProfilesJson(result);
        }

        public void parseProfilesJson(String the_json){
            String bioguide_id;

            try {
                JSONObject myjson = new JSONObject(the_json);
                JSONArray the_json_array = myjson.getJSONArray("results");
                int size = the_json_array.length();
                arrays = new ArrayList<String>();
                for (int i = 0; i < size; i++) {
                    JSONObject rep = the_json_array.getJSONObject(i);
//                    System.out.println(rep.getString("first_name"));
//                    bioguide_id = data.getString("bioguide_id");
//                    birthday = data.getString("birthday");
//                    chamber = data.getString("chamber");
//                    contact_form = data.getString("contact_form");
//                    first_name = data.getString("first_name");
//                    last_name = data.getString("last_name");
//                    oc_email = data.getString("oc_email");
//                    party = data.getString("party");
//                    state = data.getString("state");
//                    state_name = data.getString("state_name");
//                    term_end = data.getString("term_end");
//                    title = data.getString("title");
//                    twitter_id = data.getString("twitter_id");
//                    website = data.getString("website");

                    //Blah blah blah...
//                    arrays.add(bioguide_id);
//                    arrays.add(birthday);
//                    arrays.add(chamber);
//                    arrays.add(contact_form);
//                    arrays.add(first_name);
//                    arrays.add(last_name);
//                    arrays.add(oc_email);
//                    arrays.add(party);
//                    arrays.add(state);
//                    arrays.add(state_name);
//                    arrays.add(term_end);
//                    arrays.add(title);
//                    arrays.add(twitter_id);
//                    arrays.add(website);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
