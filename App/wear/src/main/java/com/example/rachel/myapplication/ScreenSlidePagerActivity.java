package com.example.rachel.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

/**
 * Created by rachel on 3/4/16.
 */

public class ScreenSlidePagerActivity extends FragmentActivity {
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private HashMap<Integer, String> Name;
    private HashMap<Integer, String> Party;
    private HashMap<String, String> VoteResults;

    //    private BoxInsetLayout mContainerView;
//    private TextView mTextView;
//    private TextView mClockView;
//    private TextView textView;
    private String zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("wear, main activity", "1");
        setContentView(R.layout.activity_screen_slide_pager);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
//        setAmbientEnabled();
//        TextView repName;
//        repName = (TextView) findViewById(R.id.rep_name);

        Name = new HashMap<>();
        Party = new HashMap<>();
        VoteResults = new HashMap<>();

        String name = getIntent().getExtras().getString("zipcode");
        zipcode = getIntent().getExtras().getString("zipcode");
//            textView = (TextView) findViewById(R.id.rep_name);
//            textView.setText(name);
        if (zipcode.equals("94704")) {
            Name.put(0, "Barbara Lee");
            Name.put(1, "Barbara Boxer");
            Name.put(2, "Dianne Feinstein");
            Party.put(0, "Democrat");
            Party.put(1, "Democrat");
            Party.put(2, "Democrat");
            VoteResults.put("Obama", "59%");
            VoteResults.put("Romney", "38%");
        } else {
            Name.put(0, "Lloyd Doggett");
            Name.put(1, "Ted Cruz");
            Name.put(2,"John Cornyn");
            Party.put(0, "Democrat");
            Party.put(1, "Republican");
            Party.put(2, "Republican");
            VoteResults.put("Obama", "22%");
            VoteResults.put("Romney","78%");
        }

    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment currentFragment;
            String representative;

            if (position == 3){
                Bundle bundle = new Bundle();
                bundle.putString("obamaResults", VoteResults.get("Obama"));
                bundle.putString("romneyResults", VoteResults.get("Romney"));
                currentFragment = new VoteResultsPageFragment();
                currentFragment.setArguments(bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("Name", Name.get(position));
                bundle.putString("Party", Party.get(position));
                bundle.putString("Position", Integer.toString(position));
                currentFragment = new ScreenSlidePageFragment();
                currentFragment.setArguments(bundle);
            }

            return currentFragment;

            }

            @Override
            public int getCount() {
                return NUM_PAGES;
            }
        }

    public void sendToMobile(View view) {
            Button button = (Button)findViewById(R.id.sendToMobile);
            Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
            String position = (String) button.getTag();
            sendIntent.putExtra("position", position);
            startService(sendIntent);
        }

}


