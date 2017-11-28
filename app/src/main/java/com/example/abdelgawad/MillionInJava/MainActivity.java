package com.example.abdelgawad.MillionInJava;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayout;
    private abdohelper helper;
    private SQLiteDatabase db;
    private ContentValues values, answeredValues;
    private TextView question, skiptext, a, b, c, d;
    private Button skip, publicOpinion, callFriend, delete2Answer;
    private String answer_true;
    private int count = 0;
    private int skipedQuestions = 0;
    int x, y, z, t, x1, x2, x3;
    int bVisibility, cVisibility, dVisibility, aVisibility,
            skipVisibility, skipTextVisibility, callVisibility, deleteVisibility, peopleVisibility;
    private ArrayList<String> ids = new ArrayList<>();
    private boolean resetQuestion, newValue, savedstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int oriantation = this.getResources().getConfiguration().orientation;
        if (oriantation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.main_horizintal);
        } else {
            setContentView(R.layout.activity_main);
        }
        init();
        Intent intent = getIntent();
        answeredValues = new ContentValues();
        helper = new abdohelper(this);
        db = helper.getWritableDatabase();
        insertValues();
        ArrayList<String> idsFfinalScore = intent.getStringArrayListExtra("ids");
        if (idsFfinalScore != null) {
            ids = idsFfinalScore;
            Log.e(" idsfinalScore : ", idsFfinalScore.toString());
        }
        values = new ContentValues();
        if (savedInstanceState != null) {
            values.put(Constants.QUESTION, savedInstanceState.getString("question"));
            values.put(Constants.ANSWER_A, savedInstanceState.getString("a"));
            values.put(Constants.ANSWER_B, savedInstanceState.getString("b"));
            values.put(Constants.ANSWER_C, savedInstanceState.getString("c"));
            values.put(Constants.ANSWER_D, savedInstanceState.getString("d"));
            values.put(Constants.ANSWER_True, savedInstanceState.getString("true"));
            count = savedInstanceState.getInt("count");
            skipedQuestions = savedInstanceState.getInt("skippedQuestion");
            ids = savedInstanceState.getStringArrayList("ids");
            bVisibility = savedInstanceState.getInt("bconf");
            aVisibility = savedInstanceState.getInt("aconf");
            cVisibility = savedInstanceState.getInt("cconf");
            dVisibility = savedInstanceState.getInt("dconf");
            skipVisibility = savedInstanceState.getInt("sconf");
            deleteVisibility = savedInstanceState.getInt("deleteconf");
            skipTextVisibility = savedInstanceState.getInt("skipTconf");
            callVisibility = savedInstanceState.getInt("callconf");
            peopleVisibility = savedInstanceState.getInt("pconf");
            if (ids != null) {
                if (ids.size() != 0) {
                    ids.remove(ids.size() - 1);
                }
            }
            savedstate = true;
            resetQuestion = false;
        } else {
            resetQuestion = true;
            savedstate = false;

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("a", a.getText().toString().trim());
        outState.putString("b", b.getText().toString().trim());
        outState.putString("c", c.getText().toString().trim());
        outState.putString("d", d.getText().toString().trim());
        outState.putString("question", question.getText().toString().trim());
        outState.putString("true", answer_true);
        outState.putStringArrayList("ids", ids);
        outState.putInt("skippedQuestion", skipedQuestions);
        outState.putInt("count", count);
        outState.putInt("aconf", a.getVisibility());
        outState.putInt("bconf", b.getVisibility());
        outState.putInt("cconf", c.getVisibility());
        outState.putInt("dconf", d.getVisibility());
        outState.putInt("sconf", skip.getVisibility());
        outState.putInt("skipTconf", skiptext.getVisibility());
        outState.putString("skipT", skiptext.getText().toString());
        outState.putInt("deleteconf", delete2Answer.getVisibility());
        outState.putInt("callconf", callFriend.getVisibility());
        outState.putInt("pconf", publicOpinion.getVisibility());
        Log.e(" savedState ", "onSaveInstanceState() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        newValue = false;
        if (savedstate) {
            resetVisibility();
        }
        Log.e(" call on resume : ", "on resume " + resetQuestion);
        Random r = new Random();
        int rand = r.nextInt(Constants.images.length - 1);
        linearLayout.setBackgroundResource(Constants.images[rand]);
        if (resetQuestion) {
            if (count < 5) {
                values = helper.getData(db, Constants.EASY_TABLE);
                Log.e("<5 ", count + " Easy");
            } else if (count < 10) {
                values = helper.getData(db, Constants.MEDIUM_TABLE);
                Log.e("<10 ", count + " Medium");
            } else {
                values = helper.getData(db, Constants.ADVANCED_TABLE);
                Log.e(">10 ", count + " Advanced");
            }
            newValue = true;
        }

        if (values != null) {
            setQuestion(values);
            if (!savedstate) {
                resetChanges();
            }

            Log.e("values  ", "! null");
        }
    }

    public void setQuestion(ContentValues values) {
        if (!(ids.contains(values.get(Constants.QUESTION).toString().trim()))) {
            question.setText(values.get(Constants.QUESTION).toString().trim());
            Log.e(" question : ", values.get(Constants.QUESTION).toString().trim());
            ArrayList<String> answers = new ArrayList<>();
            answers.add(values.get(Constants.ANSWER_A).toString().trim());
            answers.add(values.get(Constants.ANSWER_B).toString().trim());
            answers.add(values.get(Constants.ANSWER_C).toString().trim());
            answers.add(values.get(Constants.ANSWER_D).toString().trim());
            if (newValue) {
                setAnswersRandomly(answers);
            } else {
                setAnswerOrdered(answers);
            }
            resetQuestion = false;
            answer_true = values.get(Constants.ANSWER_True).toString().trim();
            answeredValues = values;
            Log.e(" list : ", ids.toString());
        } else {
            onResume();
            Log.e(" call resume : ", "call");
        }
    }

    private void setAnswerOrdered(ArrayList<String> answers) {
        a.setText(answers.get(0));
        b.setText(answers.get(1));
        c.setText(answers.get(2));
        d.setText(answers.get(3));
    }

    private void setAnswersRandomly(ArrayList<String> answers) {
        ArrayList<Integer> used = new ArrayList<>();
        int g = 0;
        do {

            Random random = new Random();
            int r = random.nextInt(4);
            if (!used.contains(r)) {
                used.add(r);
                Log.e(" if ", r + "");
                switch (g) {
                    case 0:
                        a.setText(answers.get(r));
                        break;
                    case 1:
                        b.setText(answers.get(r));
                        break;
                    case 2:
                        c.setText(answers.get(r));
                        break;
                    case 3:
                        d.setText(answers.get(r));
                        break;
                }
                g++;
            } else Log.e(" else ", r + "");
        } while (g < 4);

    }

    @Override
    public void onClick(View v) {
        TextView ans ;
        boolean isCorrect ;
        String string, str;
        if (v == skip) {

            if (skipedQuestions >= 1) {
                skip.setClickable(false);
                skip.setVisibility(View.INVISIBLE);
                skiptext.setVisibility(View.INVISIBLE);
            }
            skipedQuestions++;
            resetQuestion = true;
            savedstate=false;
            onResume();
        } else if (v == publicOpinion) {
            resetQuestion = false;
            if (answer_true.equals(a.getText().toString().trim())) {
                str = "a";
            } else if (answer_true.equals(b.getText().toString().trim())) {
                str = "b";
            } else if (answer_true.equals(c.getText().toString().trim())) {
                str = "c";
            } else {
                str = "d";
            }
            createRandomNumbers(str);
            resetValue();
            publicOpinion.setVisibility(View.INVISIBLE);
        } else if (v == delete2Answer) {
            Random answ = new Random();
            int ans1 = answ.nextInt(4) + 1;
            int ans2 = answ.nextInt(4) + 1;
            if (answer_true.equals(a.getText().toString().trim())) {

                while (true) {
                    if (ans1 == 1 || ans2 == 1 || ans1 == ans2) {
                        ans1 = answ.nextInt(4) + 1;
                        ans2 = answ.nextInt(4) + 1;
                    } else {
                        break;
                    }
                }

            } else if (answer_true.equals(b.getText().toString().trim())) {

                while (true) {
                    if (ans1 == 2 || ans2 == 2 || ans1 == ans2) {
                        ans1 = answ.nextInt(4) + 1;
                        ans2 = answ.nextInt(4) + 1;
                    } else {
                        break;
                    }
                }
            } else if (answer_true.equals(c.getText().toString().trim())) {

                while (true) {
                    if (ans1 == 3 || ans2 == 3 || ans1 == ans2) {
                        ans1 = answ.nextInt(4) + 1;
                        ans2 = answ.nextInt(4) + 1;
                    } else {
                        break;
                    }
                }
            } else if (answer_true.equals(d.getText().toString().trim())) {

                while (true) {
                    if (ans1 == 4 || ans2 == 4 || ans1 == ans2) {
                        ans1 = answ.nextInt(4) + 1;
                        ans2 = answ.nextInt(4) + 1;
                    } else {
                        break;
                    }
                }

            }
            Log.e("ans1", " ans1  " + ans1 + "" + "  ans2  " + ans2);
            changeVisibility(ans1);
            changeVisibility(ans2);
            delete2Answer.setVisibility(View.INVISIBLE);
        } else if (v == callFriend) {
            resetQuestion = false;
            Intent intent = new Intent(MainActivity.this, CallFrindActivity.class);
            intent.putExtra("field", getAnswerTrue());
            startActivity(intent);
            callFriend.setClickable(false);
            resetValue();
            callFriend.setVisibility(View.INVISIBLE);
        } else {
            resetQuestion = true;
            if (v.getId() == a.getId()) {
                ans = a;
                if (a.getText().toString().trim().equals(answer_true)) {
                    string = a.getText().toString();
                    isCorrect = true;
                    count++;
                    Log.e("string a : ", string + "");
                } else {
                    isCorrect = false;
                    Log.e("  string a : ", "not ");
                }
                changeClickable(false);
            } else if (v.getId() == b.getId()) {
                ans = b;
                if (b.getText().toString().trim().equals(answer_true)) {
                    string = b.getText().toString();
                    count++;
                    isCorrect = true;
                    Log.e("string b : ", string + "");
                } else {
                    isCorrect = false;
                    Log.e("  string b : ", "not ");
                }
                changeClickable(false);
            } else if (v.getId() == c.getId()) {
                ans = c;
                if (c.getText().toString().trim().equals(answer_true)) {
                    string = c.getText().toString();
                    count++;
                    isCorrect = true;
                    Log.e("string c : ", string + "");
                } else {
                    isCorrect = false;
                    Log.e("  string c : ", "not ");
                }
                changeClickable(false);
            } else  {
                ans = d;
                if (d.getText().toString().trim().equals(answer_true)) {
                    string = d.getText().toString();
                    count++;
                    isCorrect = true;
                    Log.e("string d : ", string + "");
                } else {
                    isCorrect = false;
                    Log.e("  string d : ", "not ");
                }
                changeClickable(false);
            }

            assert ans != null;
            ans.setBackgroundColor(Color.YELLOW);
            final boolean correct = isCorrect;
            final TextView answer = ans;

            final Handler handler = new Handler();

            if (correct) {
                Runnable task1 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.GRAY);
                    }};
                Runnable task2 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.YELLOW);
                    }};
                Runnable task3 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.GRAY);
                    }};
                Runnable task4 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.GREEN);}};
                Runnable task5 = new Runnable() {
                    @Override
                    public void run() {
                        ids.add(answeredValues.get(Constants.QUESTION).toString().trim());
                        int score = score();
                        Intent i = new Intent(MainActivity.this, ScoreActivity.class);
                        i.putExtra("score", score);
                        i.putExtra("count", count);
                        Log.e(" 6 : ", "correct");
                        startActivity(i);
                        resetChanges();

                        savedstate=false;
                    }
                };
                handler.postDelayed(task1,1000);
                handler.postDelayed(task2,2000);
                handler.postDelayed(task3,3000);
                handler.postDelayed(task4,4000);
                handler.postDelayed(task5,5000);
                Log.e(" 1 : ", "green");

            } else {
                Runnable task1 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.GRAY);
                    }};
                Runnable task2 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.YELLOW);
                    }};
                Runnable task3 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.GRAY);
                    }};
                Runnable task4 = new Runnable() {
                    @Override
                    public void run() {
                        answer.setBackgroundColor(Color.RED);}};
                Runnable task5 = new Runnable() {
                    @Override
                    public void run() {
                        int score = score();
                        Intent i = new Intent(MainActivity.this, FinalScore.class);
                        i.putExtra("score", score);
                        i.putExtra("count", count);
                        i.putExtra("ids", ids);
                        Log.e(" 6 : ", "correct");
                        startActivity(i);
                        resetChanges();

                        MainActivity.this.finish();
                    }
                };
                handler.postDelayed(task1,1000);
                handler.postDelayed(task2,2000);
                handler.postDelayed(task3,3000);
                handler.postDelayed(task4,4000);
                handler.postDelayed(task5,5000);

            }
        }


    }


    public void changeClickable(boolean s) {
        a.setClickable(s);
        b.setClickable(s);
        c.setClickable(s);
        d.setClickable(s);
    }

    public void resetChanges() {
        changeClickable(true);
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        a.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        b.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        c.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        d.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public int score() {
        int score = 0;
        if (count != 0) {
            score = Constants.scores[count - 1];
        }
        Toast.makeText(getApplicationContext(), " your score is : " + score, Toast.LENGTH_LONG).show();
        return score;
    }

    public void insertValues() {
        for (int i = 0; i < 22; i++) {
            ContentValues values = helper.insert(Constants.questions[0][i], Constants.questions[1][i],
                    Constants.questions[2][i], Constants.questions[3][i], Constants.questions[4][i], Constants.questions[5][i]);
            Long position = db.insert(Constants.ADVANCED_TABLE, null, values);
            Log.w("position ", position + " ");
        }
        for (int i = 0; i < 5; i++) {
            ContentValues values1 = helper.insert(Constants.q_a_java[0][i], Constants.q_a_java[1][i], Constants.q_a_java[2][i], Constants.q_a_java[3][i], Constants.q_a_java[4][i], Constants.q_a_java[5][i]);
            Long position1 = db.insert(Constants.EASY_TABLE, null, values1);
            Log.w("position1 ", position1 + " ");

        }
        for (int i = 0; i < 12; i++) {
            ContentValues values1 = helper.insert(Constants.midiumQuestion[i][0], Constants.midiumQuestion[i][1], Constants.midiumQuestion[i][2], Constants.midiumQuestion[i][3], Constants.midiumQuestion[i][4], Constants.midiumQuestion[i][5]);
            Long position1 = db.insert(Constants.MEDIUM_TABLE, null, values1);
            Log.w("position1 ", position1 + " ");

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sitting) {
            Toast.makeText(this, " you just clicked the action sitting", Toast.LENGTH_SHORT).show();
        } else if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.navigate) {
            StartActivity activity = new StartActivity();
            finishAffinity();
        }
        return true;
    }

    public void createRandomNumbers(String answer) {
        Random r = new Random();
        do {
            x = r.nextInt(7) + 1;
            x1 = 10 - x;
            y = r.nextInt(x1) + 1;
            x2 = x1 - y;
            if (x2 == 0 || x2 == 1) {
                Log.e("round 1  ", x2 + " continue");

            } else {
                z = r.nextInt(x2) + 1;
                x3 = x2 - z;
                if (x3 == 0) {
                    Log.e("round 2  ", x3 + " continue");

                } else {
                    t = x3;
                    break;
                }
            }
        } while (true);
        Log.e(" probabilities  ", x + " , " + y + " , " + z + " , " + t);
        swap(answer, x, y, z, t);
        x = x * 10;
        z = z * 10;
        y = y * 10;
        t = t * 10;
        Intent intent = new Intent(MainActivity.this, publicOPinion.class);
        intent.putExtra("answer", answer);
        intent.putExtra("a", x);
        intent.putExtra("b", y);
        intent.putExtra("c", z);
        intent.putExtra("d", t);
        startActivity(intent);
        publicOpinion.setClickable(false);
    }

    public void swap(String answer, int a, int b, int c, int d) {
        int s, m, f;
        switch (answer) {
            case "a":
                if (a < b) {
                    m = a;
                    a = b;
                    b = m;
                } else if (a == b) {
                    b--;
                    a++;
                }
                f = Math.max(c, d);
                if (a < f) {
                    if (c == f) {
                        m = a;
                        a = c;
                        c = m;
                    } else if (d == f) {
                        m = a;
                        a = d;
                        d = m;
                    }
                } else if (a == f) {
                    if (f == c) {
                        c--;
                        a++;

                    } else if (f == d) {
                        d = d - 1;
                    }
                }

                break;
            case "b":
                if (b < a) {
                    m = b;
                    b = a;
                    a = m;
                } else if (b == a) {
                    a--;
                    b++;
                }
                f = Math.max(c, d);
                if (b < f) {
                    if (c == f) {
                        m = b;
                        b = c;
                        c = m;
                    } else if (d == f) {
                        m = b;
                        b = d;
                        d = m;
                    }
                } else if (b == f) {
                    if (f == c) {
                        c--;
                        b++;
                    } else if (f == d) {
                        d = d - 1;
                    }
                }

                break;
            case "c":
                if (c < d) {
                    m = c;
                    c = d;
                    d = m;
                } else if (c == d) {
                    d--;
                    c++;
                }
                f = Math.max(a, b);
                if (c < f) {
                    if (a == f) {
                        m = c;
                        c = a;
                        a = m;
                    } else if (b == f) {
                        m = c;
                        c = b;
                        b = m;
                    }
                } else if (c == f) {
                    if (f == a) {
                        a--;
                        c++;
                    } else if (f == b) {
                        b--;
                        c++;
                    }
                }

                break;
            case "d":
                if (d < c) {
                    m = d;
                    d = c;
                    c = m;
                } else if (d == c) {
                    c--;
                    d++;
                }
                f = Math.max(a, b);
                if (d < f) {
                    if (a == f) {
                        m = d;
                        d = a;
                        a = m;
                    } else if (b == f) {
                        m = d;
                        d = b;
                        b = m;
                    }

                } else if (d == f) {
                    if (f == a) {
                        a--;
                        d++;
                    } else if (f == b) {
                        b--;
                        d++;
                    }
                }

                break;
        }


        x = a;
        y = b;
        z = c;
        t = d;
    }

    public void changeVisibility(int q) {
        switch (q) {
            case 1:
                a.setVisibility(View.INVISIBLE);
                break;
            case 2:
                b.setVisibility(View.INVISIBLE);
                break;
            case 3:
                c.setVisibility(View.INVISIBLE);
                break;
            case 4:
                d.setVisibility(View.INVISIBLE);
                break;
        }
        delete2Answer.setClickable(false);
    }

    public String getAnswerTrue() {
        String field;
        if (a.getText().toString().trim().equals(answer_true)) {
            field = "a";

        } else if (b.getText().toString().trim().equals(answer_true)) {
            field = "b";
        } else if (c.getText().toString().trim().equals(answer_true)) {
            field = "c";
        } else {
            field = "d";
        }
        return field;
    }

    public void init() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        question = (TextView) findViewById(R.id.txt_question);
        Log.e(" ques ", question.getParent().toString());
        a = (TextView) findViewById(R.id.txt_answer_a);
        a.setOnClickListener(this);
        b = (TextView) findViewById(R.id.txt_answer_b);
        b.setOnClickListener(this);
        c = (TextView) findViewById(R.id.txt_answer_c);
        c.setOnClickListener(this);
        d = (TextView) findViewById(R.id.txt_answer_d);
        d.setOnClickListener(this);
        skiptext = (TextView) findViewById(R.id.skip_text);
        skip = (Button) findViewById(R.id.skip_button);
        skip.setOnClickListener(this);
        publicOpinion = (Button) findViewById(R.id.people);
        publicOpinion.setOnClickListener(this);
        callFriend = (Button) findViewById(R.id.friend);
        callFriend.setOnClickListener(this);
        delete2Answer = (Button) findViewById(R.id.delete_2_answer);
        delete2Answer.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void resetVisibility() {

        skip.setVisibility(skipVisibility);
        skiptext.setVisibility(skipTextVisibility);
        callFriend.setVisibility(callVisibility);
        delete2Answer.setVisibility(deleteVisibility);
        publicOpinion.setVisibility(peopleVisibility);
        a.setVisibility(aVisibility);
        b.setVisibility(bVisibility);
        c.setVisibility(cVisibility);
        d.setVisibility(dVisibility);

    }
    public void resetValue(){
        values=new ContentValues();
        values.put(Constants.QUESTION,question.getText().toString().trim());
        values.put(Constants.ANSWER_A,a.getText().toString().trim());
        values.put(Constants.ANSWER_B,b.getText().toString().trim());
        values.put(Constants.ANSWER_C,c.getText().toString().trim());
        values.put(Constants.ANSWER_D,d.getText().toString().trim());
        values.put(Constants.ANSWER_True,answer_true);
    }

}
