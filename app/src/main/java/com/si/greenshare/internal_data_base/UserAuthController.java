package com.si.greenshare.internal_data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by joao.silva.
 */

public class UserAuthController {
    private SQLiteDatabase dataBaseSystem;
    private DataBase dataBase;
    private Boolean dontCloseDataBase;

    public UserAuthController(Context context) {
        this.dataBase = new DataBase(context);
        this.dontCloseDataBase = false;
    }

    public boolean insertUserAuth(String email, String password) {
        openDataBase();
        this.dontCloseDataBase = true;

        deleteUserAuth();

        ContentValues values;
        long result;

        values = new ContentValues();
        values.put(DataBase.EMAIL, email);
        values.put(DataBase.PASSWORD, password);

        result = this.dataBaseSystem.insert(DataBase.USER_TABLE, null, values);

        this.dontCloseDataBase = false;
        closeDataBase();

        if (result == -1) {
            return false;
        }
        return true;
    }

    public UserAuth getUserAuth() {
        openDataBase();
        this.dontCloseDataBase = true;

        Cursor cursor;
        UserAuth userAuth;
        String[] fields = {this.dataBase.EMAIL, this.dataBase.PASSWORD};
        cursor = this.dataBaseSystem.query(this.dataBase.USER_TABLE, fields, null, null, null, null, null, null);

        try {
            if (cursor != null) {
                if (cursor.getCount() > 1) {
                    deleteUserAuth();
                } else if (cursor.getCount() == 1) {
                    cursor.moveToFirst();
                    userAuth = new UserAuth();
                    userAuth.setEmail(cursor.getString(0));
                    userAuth.setPassword(cursor.getString(1));
                    return userAuth;
                }
                return null;
            }

            return null;
        } catch (SQLiteException e) {
            Log.d("SQL Error", e.getMessage());
            return null;
        } finally {
            cursor.close();
            this.dontCloseDataBase = false;
            closeDataBase();
        }
    }

    public void deleteUserAuth() {
        openDataBase();
        this.dataBaseSystem.delete(DataBase.USER_TABLE, null, null);
        closeDataBase();
    }

    private void openDataBase() {
        if (this.dataBaseSystem == null || !this.dataBaseSystem.isOpen()) {
            this.dataBaseSystem = this.dataBase.getReadableDatabase();
        }
    }

    private void closeDataBase() {
        if (!this.dontCloseDataBase) {
            this.dataBaseSystem.close();
        }
    }
}
