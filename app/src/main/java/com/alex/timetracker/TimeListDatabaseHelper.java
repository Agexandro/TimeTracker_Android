package com.alex.timetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TimeListDatabaseHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "timetracker.db";
    private static final String TABLE_NAME = "timerecords";

    public static final String TIMETRACKER_COLUMN_ID = "_id";
    public static final String TIMETRACKER_COLUMN_TIME = "time";
    public static final String TIMETRACKER_COLUMN_NOTE = "note";
    private SQLiteDatabase sqLiteDatabase;
    private TimeTrackerOpenHelper openHelper;

    public TimeListDatabaseHelper(Context context) {
        openHelper = new TimeTrackerOpenHelper(context);
        sqLiteDatabase = openHelper.getWritableDatabase();
    }

    public void saveTimeRecord(String time, String note) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TIMETRACKER_COLUMN_TIME, time);
        contentValues.put(TIMETRACKER_COLUMN_NOTE, note);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor getAllTimeRecords () {
        return sqLiteDatabase.rawQuery("select*from "+TABLE_NAME, null);
    }

    private class TimeTrackerOpenHelper extends SQLiteOpenHelper {

        public TimeTrackerOpenHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME +
                    "(" + TIMETRACKER_COLUMN_ID + " integer primary key," +
                    TIMETRACKER_COLUMN_TIME + " text ," + TIMETRACKER_COLUMN_NOTE + " text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            onCreate(db);
        }

    }

}
