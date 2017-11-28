package com.example.abdelgawad.MillionInJava;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalScore extends AppCompatActivity {
    private TextView score,NOQ;
    private Button reply,exit;
    private Toolbar toolbar;
private ArrayList<String> ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int oriantation = this.getResources().getConfiguration().orientation;
        if (oriantation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.final_score_horizintal);
        } else {
            setContentView(R.layout.activity_final_score);        }

        toolbar= (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i= getIntent();
        int s=i.getIntExtra("score",0);
        int c= i.getIntExtra("count",0);
        ids=i.getStringArrayListExtra("ids");
        score= (TextView) findViewById(R.id.score);
        NOQ= (TextView) findViewById(R.id.number_of_questions);
        reply= (Button) findViewById(R.id.reply);
        exit= (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishAffinity();
            }
        });
        score.setText(s+"");
        NOQ.setText(c+"");
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FinalScore.this,MainActivity.class);
                intent.putExtra("ids",ids);
                startActivity(intent);
                FinalScore.this.finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menue, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
int id =item.getItemId();
        if (id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }else if (id==R.id.exit){
            finishAffinity();
        }
        return true;
    }
}
