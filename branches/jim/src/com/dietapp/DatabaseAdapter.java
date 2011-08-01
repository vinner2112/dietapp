package com.dietapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
	public static final String KEY_currentRow = "_id";
	public static final String KEY_currentName = "name";
	public static final String KEY_currentType = "type";
	public static final String KEY_currentCalories = "calories";
	public static final String KEY_currentDate = "date";
	
	private Context context;
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	
	public DatabaseAdapter(Context context) {
		this.context = context;
	}
	
	
	public DatabaseAdapter open() throws SQLException {
		dbHelper = new DatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public long createEntry(String date, String name, int type, int calories) {
		ContentValues values = createContentValues(date, name, type,
				calories);

		return database.insert("currentTable", null, values);
	}
	
	
	public Cursor fetchAllCurrentEntries() {
		return database.query("currentTable", new String[] {KEY_currentRow, KEY_currentDate, KEY_currentName, KEY_currentType, KEY_currentCalories}, null, null, null,
				null, null);
	}
	
	
	private ContentValues createContentValues(String date, String name, int type, int calories) {
		ContentValues values = new ContentValues();
		values.put(KEY_currentDate, date);
		values.put(KEY_currentName, name);
		values.put(KEY_currentType, type);
		values.put(KEY_currentCalories, calories);
		return values;
	}
	
	
}
