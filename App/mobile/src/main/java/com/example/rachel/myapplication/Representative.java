package com.example.rachel.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rachel on 3/1/16.
 */
public class Representative implements Serializable {
    public String position;
    public String name;
    public String party;
    public int repPhoto;
    public String repEmail;
    public String repWebsite;
    public String repTweet;

    public Representative(String position, String name, String party, int repPhoto, String repEmail, String repWebsite, String repTweet) {
        this.position = position;
        this.name = name;
        this.party = party;
        this.repPhoto = repPhoto;
        this.repEmail = repEmail;
        this.repWebsite = repWebsite;
        this.repTweet = repTweet;
    }

    public int getRepPhoto() {
        return repPhoto;
    }

    public static ArrayList<Representative> getReps() {
        String house_of_rep = "House of Representatives";
        String senator = "Senate";
        ArrayList<Representative> reps = new ArrayList<Representative>();
        reps.add(new Representative("House of Representatives", "Barbara Lee", "Democratic Party", R.drawable.barbara_lee, "barbara@lee.house.gov", "http://lee.house.gov", "The Texas law has nothing to do with protecting women's health & everything to do with restricting women's rights #StopTheSham #TellUSAToday"));
        reps.add(new Representative("Senator", "Barbara Boxer", "Democratic Party", R.drawable.barbara_boxer,"barbara@boxer.senate.gov", "http://boxer.senate.gov", "@SenateDems stood united at the Supreme Court today to tell @Senate_GOPs: #DoYourJob"));
        reps.add(new Representative("Senator", "Dianne Feinstein", "Democratic Party", R.drawable.dianne_feinstein,"dianne@feinstein.senate.gov","http://feinstein.senate.gov", "The federal government needs authority to aggressively pursue transnational criminal organizations to reduce flow of drugs into our country."));
        return reps;
    }

    public static ArrayList<Representative> getReps2() {
        String house_of_rep = "House of Representatives";
        String senator = "Senate";
        ArrayList<Representative> reps = new ArrayList<Representative>();
        reps.add(new Representative("House of Representatives", "Lloyd Doggett", "Democratic Party", R.drawable.lloyd_doggett, "lloyd@http://doggett.house.gov", "http://doggett.house.gov", "Texas eyes on the Supreme Court today. My thoughts are w/ women & families whose lives will be impacted by the Court's ruling #WeWontGoBack"));
        reps.add(new Representative("Senator", "Ted Cruz", "Republican Party", R.drawable.ted_cruz,"ted@tedcruz.org", "http://tedcruz.org", "Thank you for an amazing #SuperTuesday! Help fund our March to Victory --> http://bit.ly/1QLOE9R  #CruzCrew "));
        reps.add(new Representative("Senator", "John Cornyn", "Republican Party", R.drawable.john_cornyn,"john@cornyn.senate.gov","http://cornyn.senate.gov", "Space selfies and water ping-pong: 19 highlights from Scott Kelly’s #YearInSpace http://bit.ly/1RnNxO4  "));
        return reps;
    }




}
