package com.example.rachel.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailedRep extends AppCompatActivity {

    String name;
    String party;
    String position;
    String nameFromPhone;
    String positionFromPhone;
    String termEnd;
    String partyFromPhone;
    String photoFromPhone;
    Integer photo;
    ArrayList<String> short_title_array;
    ArrayList<String> date_array;
    ArrayList<String> name_array;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_rep);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        photoFromPhone = extras.getString("photo");
        termEnd = extras.getString("termEnd");

        TextView repPosition = (TextView) findViewById(R.id.rep_detail_position);
        ImageView repImg = (ImageView) findViewById(R.id.rep_detail_photo);
        new getImgAPI(repImg).execute(photoFromPhone);
        new getCommittees(photoFromPhone).execute();
        TextView repName = (TextView) findViewById(R.id.rep_detail_name);
        TextView repParty = (TextView) findViewById(R.id.rep_detail_party);
        TextView repEndTerm = (TextView) findViewById(R.id.rep_term_end);



        if (extras.getString("repName") != null) {

            nameFromPhone = extras.getString("repName");
            positionFromPhone = extras.getString("repPosition");
            partyFromPhone = extras.getString("repParty");
            photoFromPhone = extras.getString("photo");
            repName.setText(nameFromPhone);
            repPosition.setText(positionFromPhone);
            repParty.setText(partyFromPhone);
            repEndTerm.setText("Term Ends: " + termEnd);
        } else {
            name = extras.getString("name");
            party = extras.getString("party");
            position = extras.getString("position");
            repName.setText(name);
            repPosition.setText(position);
            repParty.setText(party);
            repEndTerm.setText("Term Ends: " + termEnd);
//            repImg.setImageResource(getResources().getIdentifier(repPics.get(name), "drawable", getPackageName()));
            // and get whatever type user account id is
        }



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


//    private String termEnd = new HashMap<String, String>() {{
//        put("Barbara Lee", "January 3, 2017");
//        put("Barbara Boxer", "January 3, 2017");
//        put("Dianne Feinstein", "January 3, 2019");
//        put("Lloyd Doggett", "January 3, 2019");
//        put("Ted Cruz", "January 3, 2019");
//        put("John Cornyn", "January 3, 2019");
//
//
//    }};

//    private HashMap<String, String> repPics = new HashMap<String, String>() {{
//        put("Barbara Lee", "barbara_lee");
//        put("Barbara Boxer", "barbara_boxer");
//        put("Dianne Feinstein", "dianne_feinstein");
//        put("Lloyd Doggett", "lloyd_doggett");
//        put("Ted Cruz", "ted_cruz");
//        put("John Cornyn", "john_cornyn");
//
//
//
//    }};

    /*
     * Preparing the list data
     */
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Commitees");
        listDataHeader.add("Bills Passed");

        // Adding child data
        List<String> committees = new ArrayList<String>();
        for (int i=0; i < name_array.size(); i++) {
            committees.add(name_array.get(i));
        }

        List<String> bills_passed = new ArrayList<String>();
        for (int i=0; i < short_title_array.size(); i++) {
            bills_passed.add(short_title_array.get(i) + " " + date_array.get(i));
        }

            listDataChild.put(listDataHeader.get(0), committees); // Header, Child data
            listDataChild.put(listDataHeader.get(1), bills_passed);
    }

    private class getImgAPI extends AsyncTask<String, Void, Bitmap> {
        ImageView repImg;

        public getImgAPI(ImageView repImg) {
            this.repImg = repImg;
        }

        protected Bitmap doInBackground(String... urls) {
            //        String email = emailText.getText().toString();
//            ArrayList<String> bioguide_id_array = RetrieveFeedTask.bioguide_id_array;
            String API_URL = "https://theunitedstates.io";
            String PATH = "/images/congress";
            String SIZE = "/450x550/";
            String BIOGUIDE = urls[0];
            Bitmap mIcon = null;
            // Do some validation here
            try {
                String url = API_URL + PATH + SIZE + BIOGUIDE + ".jpg";
                InputStream in = new java.net.URL(url).openStream();
                mIcon = BitmapFactory.decodeStream(in);
//                InputStream is = url.openConnection().getInputStream();
//                Bitmap bitMap = BitmapFactory.decodeStream(is);
//                return bitMap;
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
//            super.onPostExecute(result);
            System.out.println(result);
            repImg.setImageBitmap(result);
        }


    }

    private class getCommittees extends AsyncTask<String, Void, String> {
        String bioguide_id;

        public getCommittees(String bioguide_id){
            this.bioguide_id = bioguide_id;
        }

        protected String doInBackground(String... urls) {
            //        String email = emailText.getText().toString();
            String API_URL = "https://congress.api.sunlightfoundation.com";
            String PATH = "/committees?member_ids=";
            String SPONSOR_ID = photoFromPhone;
            String API_KEY = "a25d4eccfb84457f8da735527a6e6576";
            // Do some validation here
            try {
                URL url = new URL(API_URL + PATH + SPONSOR_ID + "&apikey=" + API_KEY);
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

            new getBills(photoFromPhone).execute();
        }

        public void parseProfilesJson(String the_json){
            String jsonName;

            try {
                JSONObject myjson = new JSONObject(the_json);
                JSONArray the_json_array = myjson.getJSONArray("results");
                int size = the_json_array.length();
                name_array = new ArrayList<String>();

                for (int i = 0; i < size; i++) {
                    JSONObject rep = the_json_array.getJSONObject(i);
                    jsonName = rep.getString("name");
                    //Blah blah blah...
                    name_array.add(jsonName);
                }
//                System.out.println(arrays.get(0));
//                output.setText((CharSequence) arrays);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    private class getBills extends AsyncTask<String, Void, String> {
        String bioguide_id;

        public getBills(String bioguide_id){
            this.bioguide_id = bioguide_id;
        }

            protected String doInBackground(String... urls) {
                //        String email = emailText.getText().toString();
                String API_URL = "https://congress.api.sunlightfoundation.com";
                String PATH = "/bills?sponsor_id=";
                String SPONSOR_ID = photoFromPhone;
                String API_KEY = "a25d4eccfb84457f8da735527a6e6576";
                // Do some validation here
                try {
                    URL url = new URL(API_URL + PATH + SPONSOR_ID + "&apikey=" + API_KEY);
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

                // get the listview
                expListView = (ExpandableListView) findViewById(R.id.lvExp);

                // preparing list data
                prepareListData();

                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);
            }

            public void parseProfilesJson(String the_json){
                String short_title;
                String date;

                try {
                    JSONObject myjson = new JSONObject(the_json);
                    JSONArray the_json_array = myjson.getJSONArray("results");
                    int size = the_json_array.length();
                    short_title_array = new ArrayList<String>();
                    date_array = new ArrayList<String>();

                    for (int i = 0; i < size; i++) {
                        JSONObject rep = the_json_array.getJSONObject(i);
                        short_title = rep.getString("official_title");
                        date = rep.getString("introduced_on");

                        //Blah blah blah...
                        short_title_array.add(short_title);
                        date_array.add(date);
                    }
//                System.out.println(arrays.get(0));
//                output.setText((CharSequence) arrays);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }








}
