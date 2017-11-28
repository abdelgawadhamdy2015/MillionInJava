package com.example.abdelgawad.MillionInJava;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

public class publicOPinion extends AppCompatActivity {
    private LinearLayout a, b, c, d;
    LinearLayout layout;
    int height, x, y, z, t;
    String answer;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int oriantation = this.getResources().getConfiguration().orientation;
        if (oriantation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.pblic_opinion_horizintal);
        } else {
            setContentView(R.layout.public_opinion);
        }
        layout = (LinearLayout) findViewById(R.id.text_layout);
        a = (LinearLayout) findViewById(R.id.a);
        b = (LinearLayout) findViewById(R.id.b);
        c = (LinearLayout) findViewById(R.id.c);
        d = (LinearLayout) findViewById(R.id.d);
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        answer = intent.getStringExtra("answer");
        x = intent.getIntExtra("a", 0);
        y = intent.getIntExtra("b", 0);
        z = intent.getIntExtra("c", 0);
        t = intent.getIntExtra("d", 0);
        Log.e(" values ", x + " , " + y + " , " + z + " , " + t + " ,  " + answer);
        ViewTreeObserver vot = layout.getViewTreeObserver();
        vot.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = layout.getMeasuredHeight();
                setparams(x, a);
                setparams(y, b);
                setparams(z, c);
                setparams(t, d);
            }
        });


       /* LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) a.getLayoutParams();
        layoutParams.height=(x/100)* height;
        a.setLayoutParams(layoutParams);*/
        // a.setHeight((x/100)*height);
       /* b.setHeight((y/a100)*height);
        c.setHeight((z/100)*height);
        d.setHeight((t/100)*height);*/
    }

    public void setparams(int l, LinearLayout layout) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        int s = height * l / 100;
        layoutParams.height = s;
        layout.setLayoutParams(layoutParams);
    }
}
