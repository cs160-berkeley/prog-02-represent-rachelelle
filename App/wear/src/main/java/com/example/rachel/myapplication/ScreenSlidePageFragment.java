package com.example.rachel.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rachel on 3/4/16.
 */
public class ScreenSlidePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        Button button = (Button) rootView.findViewById(R.id.sendToMobile);
        button.setTag(getArguments().getString("Index"));

        TextView repPosition = (TextView) rootView.findViewById(R.id.rep_position);
        repPosition.setText((getArguments().getString("Position")));

        TextView repName = (TextView) rootView.findViewById(R.id.rep_name);
        repName.setText((getArguments().getString("Name")));

        TextView repParty = (TextView) rootView.findViewById(R.id.rep_party);
        repParty.setText((getArguments().getString("Party")));

//        ImageView repPhoto = (ImageView) rootView.findViewById(R.id.rep_photo);
//        repPhoto.setImageResource();


        return rootView;
    }
}