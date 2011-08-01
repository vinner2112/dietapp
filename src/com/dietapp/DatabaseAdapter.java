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
	public static final String KEY_logRow = "_id";
	public static final String KEY_logDate = "date";
	public static final String KEY_logName = "name";
	public static final String KEY_logType = "type";
	public static final String KEY_logCalories = "calories";
	
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
	
	public long createEntry(String name, int type, int calories) {
		ContentValues values = createContentValues(name, type,
				calories);

		return database.insert("currentTable", null, values);
	}
	
	
	public Cursor fetchAllCurrentEntries() {
		return database.query("currentTable", new String[] {KEY_currentRow,KEY_currentName,KEY_currentType,KEY_currentCalories}, null, null, null,
				null, null);
	}
	
	public Cursor fetchAllLogEntries() {
		return database.query("logTable", new String[] {KEY_logRow,KEY_logDate,KEY_logName,KEY_logType,KEY_logCalories}, null, null, null,
				null, null);
	}
	
	private ContentValues createContentValues(String name, int type, int calories) {
		ContentValues values = new ContentValues();
		values.put(KEY_currentName, name);
		values.put(KEY_currentType, type);
		values.put(KEY_currentCalories, calories);
		return values;
	}
	
	
}
