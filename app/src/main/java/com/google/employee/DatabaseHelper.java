package com.google.employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Tsk.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table emp(emp_id Integer Primary key AutoIncrement,username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create Table assigntasks(usesrname text,task_name TEXT primary key,Due_date TEXT)");
        sqLiteDatabase.execSQL("create Table submitted_task(username text,task_name Text primary key,Submission_progress Integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop Table if exists emp");

        sqLiteDatabase.execSQL("drop Table if exists assigntasks");

        sqLiteDatabase.execSQL("drop Table if exists submitted_task");



    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("emp", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from emp where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from emp where username = ? and password =?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public boolean insert(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("username", username);
        c.put("password", password);
        long r = db.insert("emp", null, c);
        if (r == -1) return false;
        else
            return true;
    }

    public Cursor getinfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from emp", null);
        return cursor;
    }

    public boolean update_data(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("username", username);
        c.put("password", password);
        Cursor cursor = db.rawQuery("select * from emp where password =?", new String[]{password});
        if (cursor.getCount() > 0) {
            long r = db.update("emp", c, "password=?", new String[]{password});
            if (r == -1) return false;
            else
                return true;

        } else
            return false;
    }


 public boolean updatepass(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("username", username);
        c.put("password", password);
        Cursor cursor = db.rawQuery("select * from emp where username =?", new String[]{username});
        if (cursor.getCount() > 0) {
            long r = db.update("emp", c, "username=?", new String[]{username});
            if (r == -1) return false;
            else
                return true;

        } else
            return false;
    }

    public boolean delete_data(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from emp where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long r = db.delete("emp", "username=?", new String[]{username});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;
    }

    /*Databse for Task Assignment   usernme Text ,task_name TEXT primary key,Due_date TEXT*/
    public boolean insert_task(String usesrname, String task_name, String Due_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("usesrname", usesrname);
        c.put("task_name", task_name);
        c.put("Due_date", Due_date);
        long r = db.insert("assigntasks", null, c);
        if (r == -1) return false;
        else
            return true;
    }

    public Cursor gettaskinfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from assigntasks", null);
        return cursor;
    }

    public boolean update_task(String usesrname, String task_name, String Due_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("usesrname", usesrname);
        c.put("task_name", task_name);
        c.put("Due_date", Due_date);
        Cursor cursor = db.rawQuery("select * from assigntasks where Due_date=?", new String[]{Due_date});
        if (cursor.getCount() > 0) {
            long r = db.update("assigntasks", c, "Due_date=?", new String[]{Due_date});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;

    }

    public boolean delete_task(String Due_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from assigntasks where Due_date=?", new String[]{Due_date});
        if (cursor.getCount() > 0) {
            long r = db.delete("assigntasks", "Due_date=?", new String[]{Due_date});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;
    }
    /* Database for Task submission submitted_task  task_name Submission_progress*/



    public boolean submit_tsk(String username, String task_name, int Submission_progress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("username", username);
        c.put("task_name", task_name);
        c.put("Submission_progress", Submission_progress);
        long r = db.insert("submitted_task", null, c);
        if (r == -1) return false;
        else
            return true;
    }

    public Cursor getsubmittaskinfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from submitted_task", null);
        return cursor;
    }

/*submitted_task(username text,task_name Text primary key,Submission_progress Integer*/
    public boolean delete_submit(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from submitted_task where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long r = db.delete("submitted_task", "username=?", new String[]{username});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;
    }
}





















