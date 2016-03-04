package com.example.rachel.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rachel on 3/2/16.
 */

public class CustomFragment extends Fragment implements View.OnClickListener {

    private static final float SHAKE_THRESHOLD = 1.1f;
    private static final int SHAKE_WAIT_TIME_MS = 250;
    private static final float ROTATION_THRESHOLD = 2.0f;
    private static final int ROTATION_WAIT_TIME_MS = 100;

    private View mView;
    private ImageView repPhoto;
    private TextView repName;
    private TextView repParty;
    private ImageView bg;
//    private SensorManager mSensorManager;
//    private Sensor mSensor;
//    private int mSensorType;
//    private long mShakeTime = 0;
//    private long mRotationTime = 0;

    public static CustomFragment newInstance(int sensorType) {
        CustomFragment f = new CustomFragment();

        // Supply sensorType as an argument
        Bundle args = new Bundle();
        args.putInt("sensorType", sensorType);
        f.setArguments(args);

        return f;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
//        if(args != null) {
//            mSensorType = args.getInt("sensorType");
//        }
//
//        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(mSensorType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.grid, container, false);
        getUiInitialization();
        return mView;

//        mTextTitle = (TextView) mView.findViewById(R.id.text_title);
//        mTextTitle.setText(mSensor.getStringType());
//        mTextValues = (TextView) mView.findViewById(R.id.text_values);
    }

    public void getUiInitialization(){
        repName=(TextView)mView.findViewById(R.id.rep_name);
        repParty=(TextView)mView.findViewById(R.id.rep_party);
        repPhoto=(ImageView)mView.findViewById(R.id.rep_photo);
        bg = (ImageView)mView.findViewById(R.id.rep_photo);
//        button_submit=(Button)mView.findViewById(R.id.button_submit);
        repPhoto.setOnClickListener(this);
//        button_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.button_submit:
//                edttxt_projectname.setText("Test Submit!#@%!#%");
//                break;
            case R.id.rep_photo:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
//                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                break;

            default:
                break;
        }

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mSensorManager.unregisterListener(this);
//    }



//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        // If sensor is unreliable, then just return
//        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
//        {
//            return;
//        }
//
//        mTextValues.setText(
//                "x = " + Float.toString(event.values[0]) + "\n" +
//                        "y = " + Float.toString(event.values[1]) + "\n" +
//                        "z = " + Float.toString(event.values[2]) + "\n"
//        );
//
//        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            detectShake(event);
//        }
//        else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
//            detectRotation(event);
//        }
//    }

//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }


    // References:
    //  - http://jasonmcreynolds.com/?p=388
    //  - http://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125
//    private void detectShake(SensorEvent event) {
//        long now = System.currentTimeMillis();
//
//        if((now - mShakeTime) > SHAKE_WAIT_TIME_MS) {
//            mShakeTime = now;
//
//            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
//            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
//            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;
//
//            // gForce will be close to 1 when there is no movement
//            float gForce = FloatMath.sqrt(gX * gX + gY * gY + gZ * gZ);
//
//            // Change background color if gForce exceeds threshold;
//            // otherwise, reset the color
//            if(gForce > SHAKE_THRESHOLD) {
//                mView.setBackgroundColor(Color.rgb(0, 100, 0));
//            }
//            else {
//                mView.setBackgroundColor(Color.BLACK);
//            }
//        }
//    }

//    private void detectRotation(SensorEvent event) {
//        long now = System.currentTimeMillis();
//
//        if((now - mRotationTime) > ROTATION_WAIT_TIME_MS) {
//            mRotationTime = now;
//
//            // Change background color if rate of rotation around any
//            // axis and in any direction exceeds threshold;
//            // otherwise, reset the color
//            if(Math.abs(event.values[0]) > ROTATION_THRESHOLD ||
//                    Math.abs(event.values[1]) > ROTATION_THRESHOLD ||
//                    Math.abs(event.values[2]) > ROTATION_THRESHOLD) {
//                mView.setBackgroundColor(Color.rgb(0, 100, 0));
//            }
//            else {
//                mView.setBackgroundColor(Color.BLACK);
//            }
//        }
//    }
}