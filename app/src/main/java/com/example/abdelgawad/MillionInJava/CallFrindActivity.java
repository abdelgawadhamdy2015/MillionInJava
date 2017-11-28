package com.example.abdelgawad.MillionInJava;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class CallFrindActivity extends AppCompatActivity {
    TextView text;
    Button bar;
    String answer;
    int status = 0;
    int i = 0;
    ProgressBar progressBar;
    String probability;
    Handler handler = new Handler();
    private boolean saved;
    private int vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_frind);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        bar = (Button) findViewById(R.id.progressBar);
        if (savedInstanceState != null) {
            status = savedInstanceState.getInt("status");
            vi = savedInstanceState.getInt("v");
            i = savedInstanceState.getInt("i");
            saved = true;
            Log.e(" saved  ", " " + i + "  , " + status + "  ,  ");
            create();
            setAnswer(i);
        }else {
            setAnswer(0);
        }

        Random random = new Random();
        int rand = random.nextInt(2);
        probability = Constants.probabilities[rand];
        text = (TextView) findViewById(R.id.friend_answer);
        Intent intent = getIntent();
        answer = intent.getStringExtra("field");

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
                bar.setVisibility(View.INVISIBLE);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("progress", progressBar.getProgress());
        outState.putInt("status", status);
        outState.putInt("v", bar.getVisibility());
        outState.putInt("i", i);
        Log.e(" saved state ", "called " + i + "  , " + status + "  ,  " + progressBar.getProgress());

    }

    public void create() {
        Log.e(" create", "start");

        progressBar.setProgress(status);
        progressBar.setMax(25);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 25) {
                    status = dely();
                    Log.e(" status", "" + status);
                    try {

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(status);
                        }
                    });

                }
                if (status >= 25) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CallFrindActivity.this.finish();
                }
            }
        }).start();
    }

    private int doOperation() {

        while (i < 100000) {
            i++;
            if (i == 10000) return i;
            else if (i == 20000) return i;
            else if (i == 30000) return i;
            else if (i == 40000) return i;
            else if (i == 50000) return i;
            else if (i == 60000) return i;
            else if (i == 70000) return i;
            else if (i == 80000) return i;
            else if (i == 90000) return i;
            else if (i == 100000) return i;
        }
        return 100000;
    }

    public int dely() {
        Runnable task8 = new Runnable() {
            @Override
            public void run() {
                i++;
            }
        };
        handler.postDelayed(task8, 1000);
        return i;
    }

    public void setAnswer(int d) {
        int del=20000-d*1000;
        Handler handler = new Handler();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                text.setText(getResources().getString(R.string.friend_answer) + answer + "\n" + probability);
            }
        };
        handler.postDelayed(task, del);
    }
}
