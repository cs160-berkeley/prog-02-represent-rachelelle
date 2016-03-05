package com.example.rachel.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button zipcodeButton;
    private Button currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLocation = (Button) findViewById(R.id.currentlocBttn);

        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("WHICH_REP", "reps2");
                startService(sendIntent);

                Intent intent = new Intent(getBaseContext(), MainRepActivity.class);
                // Do something in response to button
                startActivity(intent);
            }
        });

    }
//        Button buttonConvert = (Button) findViewById(R.id.buttonConvert);


    public void inputZipcode(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, inputZipcode.class);
        startActivity(intent);
    }


}
