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
    public String repPhoto;
    public String repEmail;
    public String repWebsite;
    public String repTwitterID;
    public String termEnd;

//    public HashMap<String,HashMap<String,String>> reps;

    public Representative(String position, String name, String party, String repPhoto, String repEmail, String repWebsite, String repTwitterID, String termEnd) {
        this.position = position;
        this.name = name;
        this.party = party;
        this.repPhoto = repPhoto;
        this.repEmail = repEmail;
        this.repWebsite = repWebsite;
        this.repTwitterID = repTwitterID;
        this.termEnd = termEnd;
    }

    public String getRepPhoto() {
        return repPhoto;
    }
    public String getRepTwitterId() { return repTwitterID; }

    public static ArrayList<Representative> getReps() {
//        JSONObject result =  new JSONObject(json_object);
//        ArrayList<String> arrays = new ArrayList<String>();
//        JSONObject jsonRootObject = new JSONObject(strJson);
        //Get the instance of JSONArray that contains JSONObjects
//        JSONArray jsonArray = jsonRootObject.optJSONArray("results");

        ArrayList<String> last_name_array = RetrieveFeedTask.last_name_array;
        ArrayList<String> first_name_array = RetrieveFeedTask.first_name_array;
        ArrayList<String> title_array = RetrieveFeedTask.title_array;
        ArrayList<String> party_array = RetrieveFeedTask.party_array;
        ArrayList<String> email_array = RetrieveFeedTask.email_array;
        ArrayList<String> website_array = RetrieveFeedTask.website_array;
        ArrayList<String> twitter_id_array = RetrieveFeedTask.twitter_id_array;
        ArrayList<String> bioguide_array = RetrieveFeedTask.bioguide_id_array;
        ArrayList<String> term_end_array = RetrieveFeedTask.term_end_array;

        int repsCount = first_name_array.size();

//        first_name_array.size();
//        String jsonName = RetrieveFeedTask.arrays.get(4) + RetrieveFeedTask.arrays.get(5);
//        String jsonParty = RetrieveFeedTask.arrays.get(7);

        ArrayList<Representative> reps = new ArrayList<Representative>();
        for (int i = 0; i < repsCount; i++)
            reps.add(new Representative(title_array.get(i), first_name_array.get(i) + " " + last_name_array.get(i), party_array.get(i), bioguide_array.get(i), email_array.get(i), website_array.get(i), twitter_id_array.get(i),term_end_array.get(i)));
    //        reps.add(new Representative("House of Representatives", "Barbara Lee", "Democratic Party", R.drawable.barbara_lee, "barbara@lee.house.gov", "http://lee.house.gov", "The Texas law has nothing to do with protecting women's health & everything to do with restricting women's rights #StopTheSham #TellUSAToday"));
    //        reps.add(new Representative("Senator", "Barbara Boxer", "Democratic Party", R.drawable.barbara_boxer,"barbara@boxer.senate.gov", "http://boxer.senate.gov", "@SenateDems stood united at the Supreme Court today to tell @Senate_GOPs: #DoYourJob"));
    //        reps.add(new Representative("Senator", "Dianne Feinstein", "Democratic Party", R.drawable.dianne_feinstein,"dianne@feinstein.senate.gov","http://feinstein.senate.gov", "The federal government needs authority to aggressively pursue transnational criminal organizations to reduce flow of drugs into our country."));
            return reps;
    }

//    public static ArrayList<Representative> getReps2() {
//        String house_of_rep = "House of Representatives";
//        String senator = "Senate";
//        ArrayList<Representative> reps = new ArrayList<Representative>();
//        reps.add(new Representative("House of Representatives", "Lloyd Doggett", "Democratic Party", R.drawable.lloyd_doggett, "lloyd@http://doggett.house.gov", "http://doggett.house.gov", "Texas eyes on the Supreme Court today. My thoughts are w/ women & families whose lives will be impacted by the Court's ruling #WeWontGoBack"));
//        reps.add(new Representative("Senator", "Ted Cruz", "Republican Party", R.drawable.ted_cruz,"ted@tedcruz.org", "http://tedcruz.org", "Thank you for an amazing #SuperTuesday! Help fund our March to Victory --> http://bit.ly/1QLOE9R  #CruzCrew "));
//        reps.add(new Representative("Senator", "John Cornyn", "Republican Party", R.drawable.john_cornyn,"john@cornyn.senate.gov","http://cornyn.senate.gov", "Space selfies and water ping-pong: 19 highlights from Scott Kelly’s #YearInSpace http://bit.ly/1RnNxO4  "));
//        return reps;
//    }




}
