package com.si.greenshare.internal_data_base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joao.silva.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "greenshare.db";
    public static final String USER_TABLE = "user";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    private static final int VERSION = 1;

    public DataBase(Context context){
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ".concat(USER_TABLE)
                .concat("(")
                .concat(ID).concat(" integer primary key autoincrement,")
                .concat(EMAIL).concat(" text,")
                .concat(PASSWORD).concat(" text)"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS".concat(USER_TABLE));
        onCreate(db);
    }
}
