package com.google.employee;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback_Activity extends AppCompatActivity {
    private EditText feedbackEditText;
    private RatingBar ratingBar;
    private Button submitButton;
    MySecondDb dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackEditText = findViewById(R.id.feedbackEditText);
        ratingBar = findViewById(R.id.ratingBar);
        submitButton = findViewById(R.id.submitButton);
        dbHelper = new MySecondDb(this);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedbackText = feedbackEditText.getText().toString();
                float rating = ratingBar.getRating();

                if (feedbackText.equals("")||rating ==(0)) {
                    Toast.makeText(Feedback_Activity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean i =dbHelper.review(feedbackText,rating);
                    if(i==true)
                    {
                        Toast.makeText(Feedback_Activity.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Feedback_Activity.this, "Not Successful", Toast.LENGTH_SHORT).show();

                    }
                }
                feedbackEditText.setText("");
                ratingBar.setRating(0);
            }
        });

    }
}