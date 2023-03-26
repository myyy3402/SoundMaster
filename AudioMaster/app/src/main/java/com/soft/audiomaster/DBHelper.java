package com.soft.audiomaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "musicapp.db";
    public static final int VERSION = 1;
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table allUsers(email varchar primary key, username varchar,address varchar, phone int,password varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists allUsers ");
    }

    public Boolean insertData(String email,String username,String address,String phone,String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("username",username);
        contentValues.put("address",address);
        contentValues.put("phone",phone);
        contentValues.put("password",password);
        long result = myDb.insert("allUsers",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor =  myDb.rawQuery("Select * from allUsers where email =?", new String[]{email});

        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor =  myDb.rawQuery("Select * from allUsers where email =? and password =?", new String[]{email,password});

        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
