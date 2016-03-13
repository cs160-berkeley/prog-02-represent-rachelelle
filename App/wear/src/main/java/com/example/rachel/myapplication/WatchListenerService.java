package com.example.rachel.myapplication;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by rachel on 3/1/16.
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String FRED_FEED = "/reps1";
    private static final String LEXY_FEED = "/reps2";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)

        if( messageEvent.getPath().equalsIgnoreCase( FRED_FEED ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            String first_name = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            String last_name = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            String party = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            String title = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent intent = new Intent(this, ScreenSlidePagerActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("zipcode", value);
//            intent.putExtra("first_name_array", first_name);
//            intent.putExtra("last_name_array", last_name);
//            intent.putExtra("party_array", party);
//            intent.putExtra("title_array", value);
            Log.d("T", "about to start watch MainActivity with CAT_NAME: Fred");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( LEXY_FEED )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, ScreenSlidePagerActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("zipcode", value);
            Log.d("T", "about to start watch MainActivity with CAT_NAME: Lexy");
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}