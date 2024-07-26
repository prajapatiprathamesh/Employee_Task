package com.google.employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySecondDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "messages.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names
    public static final String TABLE_NAME = "messages";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_DATE = "date";

    // SQL statement to create the table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_MESSAGE + " TEXT," +
                    COLUMN_DATE + " TEXT)";

    public MySecondDb( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_TABLE);
        db.execSQL("create Table review(review text,rating real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS review");
        onCreate(db);
    }

    public boolean ins_msg(String message, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("message", message);
        c.put("date", date);
        long r = db.insert("messages", null, c);
        if (r == -1) return false;
        else
            return true;
    }
    public Cursor getmsginfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from messages", null);
        return cursor;
    }

    public boolean delete_msg(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from messages  where date=?", new String[]{date});
        if (cursor.getCount() > 0) {
            long r = db.delete("messages", "date=?", new String[]{date});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;
    }

    public boolean review(String review, float rating ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("review",review);
        c.put("rating",rating);
        long r = db.insert("review", null, c);
        if (r == -1) return false;
        else
            return true;
    }

}