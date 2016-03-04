package com.example.rachel.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rachel on 3/1/16.
 */

public class RepArrayAdapter extends ArrayAdapter<Representative> {

    public RepArrayAdapter(Context context, ArrayList<Representative> reps) {
        super(context, 0, reps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Representative rep = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rep, parent, false);
        }

        // Lookup view for data population
        TextView positionView = (TextView) convertView.findViewById(R.id.rep_position);
        ImageView pictureView = (ImageView) convertView.findViewById(R.id.rep_photo);
        TextView nameView = (TextView) convertView.findViewById(R.id.rep_name);
        TextView partyView = (TextView) convertView.findViewById(R.id.rep_party);
        TextView emailView = (TextView) convertView.findViewById(R.id.rep_email);
        TextView websiteView = (TextView) convertView.findViewById(R.id.rep_website);
        TextView tweetView = (TextView) convertView.findViewById(R.id.rep_tweet);

        // Populate the data into the template view using the data object
        positionView.setText(rep.position);
        pictureView.setImageResource(rep.getRepPhoto());
        nameView.setText(rep.name);
        partyView.setText(rep.party);
        emailView.setText(rep.repEmail);
        websiteView.setText(rep.repWebsite);
        tweetView.setText(rep.repTweet);
        return convertView;

    }

}