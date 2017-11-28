package com.example.abdelgawad.MillionInJava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
private Button java,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        java= (Button) findViewById(R.id.java);
        java.setOnClickListener(this);
        exit= (Button) findViewById(R.id.exit);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
if (v==java){
    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);
}else {
    finishAffinity();
}
        }
    }

