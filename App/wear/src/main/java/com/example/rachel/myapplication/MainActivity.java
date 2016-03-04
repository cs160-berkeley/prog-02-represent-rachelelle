package com.example.rachel.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {


    //    private BoxInsetLayout mContainerView;
//    private TextView mTextView;
//    private TextView mClockView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("wear, main activity", "1");
        setContentView(R.layout.activity_main);
//        setAmbientEnabled();
//        TextView repName;
//        repName = (TextView) findViewById(R.id.rep_name);
        Log.v("wear, main activity", "2");


//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//
//        if (extras != null) {
//            String name = extras.getString("CAT_NAME");
//        }
//        Log.v("wear, main activity", "1");



//        WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
//        mTextView = (TextView) findViewById(R.id.text);
////        mClockView = (TextView) findViewById(R.id.clock);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override public void onLayoutInflated(WatchViewStub stub) {
//        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
//        pager.setAdapter(new SampleGridPagerAdapter(MainActivity.this, getFragmentManager()));
//
//        DotsPageIndicator indicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
//        indicator.setPager(pager);
//        Log.v("wear, main activity", "2");

//            }
//        });


    }

//    @Override
//    public void onEnterAmbient(Bundle ambientDetails) {
//        super.onEnterAmbient(ambientDetails);
//        updateDisplay();
//    }
//
//    @Override
//    public void onUpdateAmbient() {
//        super.onUpdateAmbient();
//        updateDisplay();
//    }
//
//    @Override
//    public void onExitAmbient() {
//        updateDisplay();
//        super.onExitAmbient();
//    }
//
//    private void updateDisplay() {
//        if (isAmbient()) {
//            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
//            mTextView.setTextColor(getResources().getColor(android.R.color.white));
//            mClockView.setVisibility(View.VISIBLE);
//
//            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
//        } else {
//            mContainerView.setBackground(null);
//            mTextView.setTextColor(getResources().getColor(android.R.color.black));
//            mClockView.setVisibility(View.GONE);
//        }
//    }
}
