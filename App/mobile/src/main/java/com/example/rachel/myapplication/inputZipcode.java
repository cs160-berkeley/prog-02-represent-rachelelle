package com.example.rachel.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inputZipcode extends AppCompatActivity {

    private Button zipcodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_zipcode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        zipcodeButton = (Button) findViewById(R.id.zipcode_button);

        zipcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("getting in");
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("CAT_NAME", "Fred");
                startService(sendIntent);
                System.out.println("intent sent");

                EditText zip_code = (EditText) findViewById(R.id.zip_code);
                String zipcode = zip_code.getText().toString();
                Intent intent = new Intent(getBaseContext(), MainRepActivity.class);
                intent.putExtra("zipcode", zipcode);
                // Do something in response to button
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /** Called when the user clicks the Send button */


//    zipcodeButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//            sendIntent.putExtra("Obama_text", "Obama");
//            sendIntent.putExtra("Obama_text", "Obama");
//            startService(sendIntent);
//        }
//    });

}
