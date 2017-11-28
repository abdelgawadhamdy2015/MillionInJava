package com.example.abdelgawad.MillionInJava;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Handler handler=new Handler();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(Welcome.this,StartActivity.class);
                Welcome.this.startActivity(in);
                Welcome.this.finish();
            }
        }; handler.postDelayed(task,3000);
    }
}
