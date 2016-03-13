package com.example.rachel.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainRepActivity extends AppCompatActivity {

    private Button moreInfoBttn;
    ArrayList<Representative> arrayOfReps;
    String zipcode;
    ImageView pictureView;
    Representative rep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rep);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            zipcode = extras.getString("zipcode");
        }


        setSupportActionBar(toolbar);
        moreInfoBttn = (Button) findViewById(R.id.more_info);





        populateRepsList();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void populateRepsList() {
        // Construct the data source
        if (zipcode != null) {
            arrayOfReps = Representative.getReps();
//        } else {
//            arrayOfReps = Representative.getReps2();
        }


        // Create the adapter to convert the array to views
        RepArrayAdapter adapter = new RepArrayAdapter(this, arrayOfReps);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvReps);
        listView.setAdapter(adapter);
    }


    public void seeMoreInfo(View view) {

//        Bundle extras = new Bundle();
//        ImageView rep_photo = (ImageView) findViewById(R.id.rep_photo);
        TextView rep_name = (TextView) findViewById(R.id.rep_name);
        TextView rep_party = (TextView) findViewById(R.id.rep_party);
        TextView rep_position = (TextView) findViewById(R.id.rep_position);
        TextView rep_photo = (TextView) findViewById(R.id.image_text);
        TextView rep_term_end = (TextView) findViewById(R.id.term_end);
        String repName = rep_name.getText().toString();
        String repParty = rep_party.getText().toString();
        String repPosition = rep_position.getText().toString();
        String repPhoto = rep_photo.getText().toString();
        String termEnd = rep_term_end.getText().toString();
//        ImageView repPhoto = RepArrayAdapter.pictureView;
//        Integer repPhoto = rep_photo.getId();

        Intent intent = new Intent(this, DetailedRep.class);
        intent.putExtra("name", repName);
        intent.putExtra("party", repParty);
        intent.putExtra("position", repPosition);
        intent.putExtra("photo",repPhoto);
        intent.putExtra("termEnd",termEnd);
//        intent.putExtra("photo", repPhoto);
        // Do something in response to button
        startActivity(intent);
    }




}