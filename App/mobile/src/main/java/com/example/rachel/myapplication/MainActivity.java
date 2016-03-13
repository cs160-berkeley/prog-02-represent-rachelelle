package com.example.rachel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ioSfxeM6aNtiHQeay1Xt5LXOd";
    private static final String TWITTER_SECRET = "Zd6fTUMY7IJjsDIijox3lextpBfBw8Tg6eRQH7xuWli5vgQAgd";


    private Button zipcodeButton;
    private Button currentLocation;
    private String zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);


        currentLocation = (Button) findViewById(R.id.currentlocBttn);

        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("zipcode","");
//                sendIntent.putExtra("WHICH_REP", "reps2");
//                startService(sendIntent);
                try {
                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
                    List<Address> addresses = null;
                    addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                    zipcode = addresses.get(0).getPostalCode();
                    System.out.println(zipcode);

                    RetrieveFeedTask retrievefeedtask = new RetrieveFeedTask(MainActivity.this, zipcode,String.valueOf(loc.getLatitude()), String.valueOf(loc.getLongitude()));
                    retrievefeedtask.callAPI();
//                    RetrievePictures retrievepictures = new RetrievePictures();
//                    retrievepictures.callAPI();
                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    sendIntent.putExtra("zipcode",zipcode);
                    sendIntent.putExtra("WHICH_REP", "reps1");
                    startService(sendIntent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }




//        }
//    }
//}
//        Button buttonConvert = (Button) findViewById(R.id.buttonConvert);


    public void inputZipcode(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, inputZipcode.class);
        startActivity(intent);
    }



}
