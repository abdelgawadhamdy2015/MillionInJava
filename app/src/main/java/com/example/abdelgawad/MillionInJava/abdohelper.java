package com.example.abdelgawad.MillionInJava;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by abdelgawad on 8/17/2017.
 */

public class abdohelper extends SQLiteOpenHelper {
    Activity context;

    public abdohelper(Activity context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
        Log.e("constructor", "called");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(" on create", " on create called");
        Toast.makeText(context.getApplicationContext(), "on create called", Toast.LENGTH_LONG).show();
        try {
            db.execSQL(createTable(Constants.ADVANCED_TABLE));
            db.execSQL(createTable(Constants.MEDIUM_TABLE));
            db.execSQL(createTable(Constants.EASY_TABLE));
        } catch (SQLException e) {
            Log.e("create exception", e.toString());

            TextView view = (TextView) context.findViewById(R.id.txt_question);
            view.setText(e.toString() + "");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(dropTable(Constants.EASY_TABLE));
        db.execSQL(dropTable(Constants.MEDIUM_TABLE));
        db.execSQL(createTable(Constants.EASY_TABLE));
        db.execSQL(createTable(Constants.MEDIUM_TABLE));
        Toast.makeText(context.getApplicationContext(), "on upgrade called", Toast.LENGTH_LONG).show();
    }

    private String createTable(String tableName) {
        String query = "CREATE TABLE " + tableName + " ( " + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.QUESTION + " VARCHAR(250), " + Constants.ANSWER_A + " VARCHAR(250), " + Constants.ANSWER_B + " VARCHAR(250), "
                + Constants.ANSWER_C + " VARCHAR(250), " + Constants.ANSWER_D + " VARCHAR(250), " + Constants.ANSWER_True + " VARCHAR(250) );";
        return query;
    }

    public ContentValues insert(String q, String a, String b, String c, String d, String tr) {
        ContentValues values = new ContentValues();
        values.put(Constants.QUESTION, q);
        values.put(Constants.ANSWER_A, a);
        values.put(Constants.ANSWER_B, b);
        values.put(Constants.ANSWER_C, c);
        values.put(Constants.ANSWER_D, d);
        values.put(Constants.ANSWER_True, tr);
        return values;

    }

    public Cursor getAllData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        String COLUMNS[] = {Constants.ID, Constants.QUESTION, Constants.ANSWER_A, Constants.ANSWER_B,
                Constants.ANSWER_C, Constants.ANSWER_D, Constants.ANSWER_True};
        Cursor cursor = db.query(Constants.EASY_TABLE, COLUMNS, null, null, null, null, null);
        int count = 1;
        while (cursor.moveToNext()) {
            values.put(Constants.QUESTION, cursor.getString(cursor.getColumnIndex(Constants.QUESTION)));
            values.put(Constants.ANSWER_A, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_A)));
            values.put(Constants.ANSWER_B, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_B)));
            values.put(Constants.ANSWER_C, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_C)));
            values.put(Constants.ANSWER_D, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_D)));
            values.put(Constants.ANSWER_True, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_True)));
            count++;
        }
        Toast.makeText(context.getApplicationContext(), "values : " + count + "    :  " + values.size() + "  : " + values.toString(), Toast.LENGTH_LONG).show();
        Log.e("values", values.size() + " : " + values.toString());

        return cursor;
    }

    public ContentValues getData(SQLiteDatabase db, String tableName) {
        ContentValues values = new ContentValues();
        String COLUMNS[] = {Constants.QUESTION, Constants.ANSWER_A, Constants.ANSWER_B,
                Constants.ANSWER_C, Constants.ANSWER_D, Constants.ANSWER_True};
        Random r = new Random();
        int a;
        if (tableName.equals(Constants.EASY_TABLE)) {
            a = r.nextInt(5) + 1;
        } else if (tableName.equals(Constants.MEDIUM_TABLE)) {
            a = r.nextInt(13) + 1;
        } else {
            a = r.nextInt(23) + 1;
        }

        Cursor cursor = db.query(tableName, COLUMNS, Constants.ID + " = " + a, null, null, null, null);
        while (cursor.moveToNext()) {
            values.put(Constants.QUESTION, cursor.getString(cursor.getColumnIndex(Constants.QUESTION)));
            values.put(Constants.ANSWER_A, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_A)));
            values.put(Constants.ANSWER_B, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_B)));
            values.put(Constants.ANSWER_C, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_C)));
            values.put(Constants.ANSWER_D, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_D)));
            values.put(Constants.ANSWER_True, cursor.getString(cursor.getColumnIndex(Constants.ANSWER_True)));
            Log.e("getData", values.size() + "   ,    "+ a);
        }

        return values;
    }

    public int deleteRow(SQLiteDatabase db) {
        int d = db.delete(Constants.EASY_TABLE, null, null);
        return d;
    }

    public String dropTable(String table) {
        String drop = " DROP TABLE IF EXISTS " + table;

        return drop;
    }

    public void getIndexes(SQLiteDatabase db) {
        ArrayList<Integer> values = new ArrayList<>();
        String columns[] = {Constants.ID};
        Cursor cursor = db.query(Constants.EASY_TABLE, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            values.add(cursor.getInt(cursor.getColumnIndex(Constants.ID)));
        }
        Toast.makeText(context.getApplicationContext(),
                " list size : " + values.size() + "     " + values.toString(), Toast.LENGTH_LONG).show();
    }
}
