package com.dietapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {



public DatabaseHelper(Context context) {
	  super(context, "dietdb", null,33); 
	  }


@Override
public void onCreate(SQLiteDatabase db) {
	db.execSQL("create table currentTable (_id integer primary key autoincrement, date text not null, " +
			"name text not null, type integer not null, calories integer not null);");
}


@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS currentTable");
	db.execSQL("DROP TABLE IF EXISTS logTable");
	onCreate(db);
	
}








}