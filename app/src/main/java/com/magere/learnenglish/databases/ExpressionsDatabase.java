package com.magere.learnenglish.databases;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ExpressionsDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "Expressions.db";
    private static final int DATABASE_VERSION = 1;

    ExpressionsDatabase(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
    }
}
