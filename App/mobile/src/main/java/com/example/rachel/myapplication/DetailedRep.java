package com.example.rachel.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailedRep extends AppCompatActivity {

    String name;
    String party;
    String position;
    Integer photo;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_rep);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            name = extras.getString("name");
            party = extras.getString("party");
            position = extras.getString("position");

            // and get whatever type user account id is
        }


        TextView repPosition = (TextView) findViewById(R.id.rep_detail_position);
        ImageView repImg = (ImageView) findViewById(R.id.rep_detail_photo);
        TextView repName = (TextView) findViewById(R.id.rep_detail_name);
        TextView repParty = (TextView) findViewById(R.id.rep_detail_party);
        TextView repEndTerm = (TextView) findViewById(R.id.rep_term_end);

        repPosition.setText(position);
        repName.setText(name);
        repParty.setText(party);

        repEndTerm.setText("Term Ends: " + termEnd.get(name));
        repImg.setImageResource(getResources().getIdentifier(repPics.get(name), "drawable", getPackageName()));



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private HashMap<String, String> termEnd = new HashMap<String, String>() {{
        put("Barbara Lee", "January 3, 2017");
        put("Barbara Boxer", "January 3, 2017");
        put("Dianne Feinstein", "January 3, 2019");
        put("Lloyd Doggett", "January 3, 2019");
        put("Ted Cruz", "January 3, 2019");
        put("John Cornyn", "January 3, 2019");


    }};

    private HashMap<String, String> repPics = new HashMap<String, String>() {{
        put("Barbara Lee", "barbara_lee");
        put("Barbara Boxer", "barbara_boxer");
        put("Dianne Feinstein", "dianne_feinstein");
        put("Lloyd Doggett", "lloyd_doggett");
        put("Ted Cruz", "ted_cruz");
        put("John Cornyn", "john_cornyn");



    }};

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
        committees.add("House Committee on Appropriations");
        committees.add("House Committee on The Budget");
        committees.add("House Democratic Steering and Policy Committee");
        committees.add("Subcommittee on Labor, Health and Human Services, Education and Related Agencies");
        committees.add("Subcommittee on Military Construction-Veterans Affairs");
        committees.add("Subcommittee on State, Foreign Operations, and Related Programs");

        List<String> bills_passed = new ArrayList<String>();
        bills_passed.add("2014: Supporting the goals and ideals of Professional Social Work Month and World Social Work Day.");
        bills_passed.add("2013: Food Assistance to Improve Reintegration Act of 2013");
        bills_passed.add("2012: Food Assistance to Improve Reintegration Act of 2011");
        bills_passed.add("2011: Supporting the goals and ideals of National HIV Testing Day.");

        listDataChild.put(listDataHeader.get(0), committees); // Header, Child data
        listDataChild.put(listDataHeader.get(1), bills_passed);
    }





}
