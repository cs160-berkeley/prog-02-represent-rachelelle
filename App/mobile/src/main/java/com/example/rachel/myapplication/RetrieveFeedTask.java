package com.example.rachel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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


public class RetrieveFeedTask {
    private String zipcode_text;
    private String latitude;
    private String longitude;
    private Context context;
    static ArrayList<String> arrays;
    static ArrayList<String> bioguide_id_array;
    static ArrayList<String> chamber_array;
    static ArrayList<String> contact_array;
    static ArrayList<String> first_name_array;
    static ArrayList<String> last_name_array;
    static ArrayList<String> email_array;
    static ArrayList<String> party_array;
    static ArrayList<String> state_array;
    static ArrayList<String> state_name_array;
    static ArrayList<String> term_end_array;
    static ArrayList<String> title_array;
    static ArrayList<String> twitter_id_array;
    static ArrayList<String> website_array;

    TextView output;

//    TextView output = (TextView) findViewById(R.id.textView1);


    public RetrieveFeedTask(Context context, String zipcode, String lat, String lng){
        this.context = context;
        zipcode_text = zipcode;
        latitude = lat;
        longitude = lng;
    }

    protected void callAPI(){
        new getAPI().execute();
    }

    private class getAPI extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
              //        String email = emailText.getText().toString();
              String API_URL = "https://congress.api.sunlightfoundation.com";
              String PATH = "/legislators/locate";
              String API_KEY = "a25d4eccfb84457f8da735527a6e6576";
              String ZIPCODE = zipcode_text;

              // Do some validation here
              try {
                  URL url = new URL(API_URL + PATH + "?zip=" + ZIPCODE + "&apikey=" + API_KEY);
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
            super.onPostExecute(result);
            System.out.println(result);
            parseProfilesJson(result);

//            RetrievePictures retrievepictures = new RetrievePictures();
//            retrievepictures.callAPI();
            Intent intent = new Intent(context, MainRepActivity.class);
            intent.putExtra("zipcode", zipcode_text);
            // Do something in response to button
            context.startActivity(intent);
        }

        public void parseProfilesJson(String the_json){
            String bioguide_id;
            String chamber;
            String contact_form;
            String first_name;
            String last_name;
            String oc_email;
            String party;
            String state;
            String state_name;
            String term_end;
            String title;
            String twitter_id;
            String website;

            try {
                JSONObject myjson = new JSONObject(the_json);
                JSONArray the_json_array = myjson.getJSONArray("results");
                int size = the_json_array.length();
                bioguide_id_array = new ArrayList<String>();
                chamber_array = new ArrayList<String>();
                contact_array = new ArrayList<String>();
                first_name_array = new ArrayList<String>();
                last_name_array = new ArrayList<String>();
                email_array = new ArrayList<String>();
                party_array = new ArrayList<String>();
                state_array = new ArrayList<String>();
                state_name_array = new ArrayList<String>();
                term_end_array = new ArrayList<String>();
                title_array = new ArrayList<String>();
                twitter_id_array = new ArrayList<String>();
                website_array = new ArrayList<String>();

                for (int i = 0; i < size; i++) {
                    JSONObject rep = the_json_array.getJSONObject(i);
                    bioguide_id = rep.getString("bioguide_id");
                    chamber = rep.getString("chamber");
                    contact_form = rep.getString("contact_form");
                    first_name = rep.getString("first_name");
                    last_name = rep.getString("last_name");
                    oc_email = rep.getString("oc_email");
                    party = rep.getString("party");
                    state = rep.getString("state");
                    state_name = rep.getString("state_name");
                    term_end = rep.getString("term_end");
                    title = rep.getString("title");
                    twitter_id = rep.getString("twitter_id");
                    website = rep.getString("website");

                    //Blah blah blah...
                    bioguide_id_array.add(bioguide_id);
                    chamber_array.add(chamber);
                    contact_array.add(contact_form);
                    first_name_array.add(first_name);
                    last_name_array.add(last_name);
                    email_array.add(oc_email);
                    party_array.add(party);
                    state_array.add(state);
                    state_name_array.add(state_name);
                    term_end_array.add(term_end);
                    title_array.add(title);
                    twitter_id_array.add(twitter_id);
                    website_array.add(website);
                }
//                System.out.println(arrays.get(0));
//                output.setText((CharSequence) arrays);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
