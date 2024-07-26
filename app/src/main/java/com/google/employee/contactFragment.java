package com.google.employee;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class contactFragment extends Fragment {



    public contactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_contact, container, false);
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        Button Feedback = view.findViewById(R.id.feed);
        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Feedback_Activity.class);
                startActivity(intent);

            }
        });
        return view;

    }
}