package com.example.rachel.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rachel on 3/4/16.
 */
public class VoteResultsPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.vote_results, container, false);

        TextView repName = (TextView) rootView.findViewById(R.id.obama_vote);
        repName.setText((getArguments().getString("obamaResults")));

        TextView repParty = (TextView) rootView.findViewById(R.id.romney_vote);
        repParty.setText((getArguments().getString("romneyResults")));



//        ImageView repPhoto = (ImageView) rootView.findViewById(R.id.rep_photo);
//        repPhoto.setImageResource();


        return rootView;
    }
}