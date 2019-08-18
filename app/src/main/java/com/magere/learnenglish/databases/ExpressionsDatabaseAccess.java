package com.magere.learnenglish.databases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpressionsDatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static ExpressionsDatabaseAccess instance;

    public ExpressionsDatabaseAccess(Context context) {
        this.openHelper = new ExpressionsDatabase(context);
    }

    public static ExpressionsDatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new ExpressionsDatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public Cursor getAllData() {
        return db.rawQuery("SELECT * FROM Expressions", null);
    }

    public int getCountStudiedExpressions() {
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select count(*) as count from Expressions " +
                "where groupa = 6;", null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return 0;
        return cursor.getInt(cursor.getColumnIndex("count"));
    }

    public int getCountStudiesExpressions() {
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("select count(*) as count from Expressions " +
                "where groupa BETWEEN 1 and 5;", null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return 0;
        return cursor.getInt(cursor.getColumnIndex("count"));
    }

    public Cursor getDataByTime(long time) {
        String query = "SELECT * FROM Expressions WHERE time = " + time + ";";
        return db.rawQuery(query, null);
    }

    public void updateData(long toTime, long fromTime) {
        String query = "UPDATE Expressions SET groupa = 1, time = " + toTime
                + " WHERE time = " + fromTime;
        db.execSQL(query);
    }

}
