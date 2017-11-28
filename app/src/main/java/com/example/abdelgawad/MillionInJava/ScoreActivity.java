package com.example.abdelgawad.MillionInJava;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {
    private TextView q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int oriantation = this.getResources().getConfiguration().orientation;
        if (oriantation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.score_horizintal);
        } else {
            setContentView(R.layout.score_activity);
        }

        q1 = (TextView) findViewById(R.id.q1);
        q2 = (TextView) findViewById(R.id.q2);
        q3 = (TextView) findViewById(R.id.q3);
        q4 = (TextView) findViewById(R.id.q4);
        q5 = (TextView) findViewById(R.id.q5);
        q6 = (TextView) findViewById(R.id.q6);
        q7 = (TextView) findViewById(R.id.q7);
        q8 = (TextView) findViewById(R.id.q8);
        q9 = (TextView) findViewById(R.id.q9);
        q10 = (TextView) findViewById(R.id.q10);
        q11 = (TextView) findViewById(R.id.q11);
        q12 = (TextView) findViewById(R.id.q12);
        q13 = (TextView) findViewById(R.id.q13);
        q14 = (TextView) findViewById(R.id.q14);
        q15 = (TextView) findViewById(R.id.q15);
        q16 = (TextView) findViewById(R.id.q16);

        Toast.makeText(getApplicationContext(), " the score activity", Toast.LENGTH_SHORT).show();
        // get information from other activity
        Intent i = getIntent();
        final int score = i.getIntExtra("score", 0);
        final int count = i.getIntExtra("count", 0);
        search(score);
// create handler to handle finish activity
        final Handler handler = new Handler();
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                if (score == 1000000) {
                    Intent intent = new Intent(ScoreActivity.this, FinalScore.class);
                    intent.putExtra("score", score);
                    intent.putExtra("count", count);
                    startActivity(intent);
                    ScoreActivity.this.finish();
                    MainActivity activity = new MainActivity();
                    activity.finish();
                } else {
                    ScoreActivity.this.finish();
                }
            }
        };
        // delay 5 second to finish this activity
        handler.postDelayed(task, 5000);
    }

    // search for  the view that matches the score
    public void search(int score) {
        if (score <= 8000) {
            if (score <= 1000) {
                if (score <= 300) {
                    switch (score) {
                        case 100:
                            q16.setBackgroundColor(Color.GREEN);
                            break;
                        case 200:
                            q15.setBackgroundColor(Color.GREEN);
                            break;
                        case 300:
                            q14.setBackgroundColor(Color.GREEN);
                            break;
                    }
                } else {
                    switch (score) {
                        case 400:
                            q13.setBackgroundColor(Color.GREEN);
                            break;
                        case 500:
                            q12.setBackgroundColor(Color.GREEN);
                            break;
                        case 1000:
                            q11.setBackgroundColor(Color.GREEN);
                            break;
                    }
                }
            } else {
                switch (score) {
                    case 2000:
                        q10.setBackgroundColor(Color.GREEN);
                        break;
                    case 4000:
                        q9.setBackgroundColor(Color.GREEN);
                        break;
                    case 8000:
                        q8.setBackgroundColor(Color.GREEN);
                        break;
                }
            }
        } else {
            if (score <= 125000) {
                if (score <= 32000) {
                    switch (score) {
                        case 16000:
                            q7.setBackgroundColor(Color.GREEN);
                            break;
                        case 32000:
                            q6.setBackgroundColor(Color.GREEN);
                            break;
                    }
                } else {
                    switch (score) {
                        case 64000:
                            q5.setBackgroundColor(Color.GREEN);
                            break;
                        case 125000:
                            q4.setBackgroundColor(Color.GREEN);
                            break;
                    }
                }
            } else {
                switch (score) {
                    case 250000:
                        q3.setBackgroundColor(Color.GREEN);
                        break;
                    case 500000:
                        q2.setBackgroundColor(Color.GREEN);
                        break;
                    case 1000000:
                        q1.setBackgroundColor(Color.GREEN);
                        break;
                }
            }
        }
    }
}
