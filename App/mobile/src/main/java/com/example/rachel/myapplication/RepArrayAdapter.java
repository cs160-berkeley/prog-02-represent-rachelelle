package com.example.rachel.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * Created by rachel on 3/1/16.
 */

public class RepArrayAdapter extends ArrayAdapter<Representative> {
    Representative rep;
    static ImageView pictureView;
    static String imgURL;

    private static final String TWITTER_KEY = "ioSfxeM6aNtiHQeay1Xt5LXOd";
    private static final String TWITTER_SECRET = "Zd6fTUMY7IJjsDIijox3lextpBfBw8Tg6eRQH7xuWli5vgQAgd";





    public RepArrayAdapter(Context context, ArrayList<Representative> reps) {
        super(context, 0, reps);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        rep = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rep, parent, false);
        }



        // Lookup view for data population
        TextView positionView = (TextView) convertView.findViewById(R.id.rep_position);
        TextView imageText = (TextView) convertView.findViewById(R.id.image_text);
        pictureView = (ImageView) convertView.findViewById(R.id.rep_photo);
        new getImgAPI(pictureView).execute(rep.getRepPhoto());
        TextView nameView = (TextView) convertView.findViewById(R.id.rep_name);
        TextView partyView = (TextView) convertView.findViewById(R.id.rep_party);
        TextView emailView = (TextView) convertView.findViewById(R.id.rep_email);
        ImageView emailIcon = (ImageView) convertView.findViewById(R.id.email_icon);
        TextView websiteView = (TextView) convertView.findViewById(R.id.rep_website);
        TextView termEnd = (TextView) convertView.findViewById(R.id.term_end);
//        final TextView tweetView = (TextView) convertView.findViewById(R.id.rep_tweet);
        final FrameLayout tweetFrame = (FrameLayout) convertView.findViewById(R.id.tweet);

//        tweetView.addView(new CompactTweetView(getContext(),rep.getMostRecentTweet()));


        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(getContext(), new Twitter(authConfig));

//        TwitterCore core = Twitter.getInstance().core;
//        TweetUi tweetUi = Twitter.getInstance().tweetUi;
//        TweetComposer composer = Twitter.getInstance().tweetComposer;

        TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
            @Override
            public void success(Result<AppSession> appSessionResult) {
                AppSession session = appSessionResult.data;
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
                twitterApiClient.getStatusesService().userTimeline(null, rep.getRepTwitterId(), 1, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        for (Tweet tweet : listResult.data) {
//                            tweetView.setText(tweet.text);
                            tweetFrame.addView(new CompactTweetView(getContext(), tweet));
                            Log.d("fabricstuff", "result: " + tweet.text + "  " + tweet.createdAt);
                        }
                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
            }
        });


        // Populate the data into the template view using the data object
        positionView.setText(rep.position);
        termEnd.setText(rep.termEnd);
//        pictureView.setImageResource();
        nameView.setText(rep.name);
        partyView.setText(rep.party);
        emailView.setText(rep.repEmail);
        websiteView.setText(rep.repWebsite);
        imageText.setText(rep.getRepPhoto());
//        tweetView.setText(rep.repTwitterID);
        emailIcon.setImageResource(R.drawable.mail);
        return convertView;

    }




    private class getImgAPI extends AsyncTask<String, Void, Bitmap> {
        ImageView pictureView;

        public getImgAPI(ImageView pictureView){
            this.pictureView = pictureView;
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
            pictureView.setImageBitmap(result);
        }


    }



}