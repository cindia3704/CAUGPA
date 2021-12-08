package com.example.caugpa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(Context context){
        super(context,"groupDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table mySubjects(id integer, sName char(100), year integer, score char(10), weight integer, major char(20), majorSpecific char(100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists mySubjects;");
        onCreate(db);
    }
}
