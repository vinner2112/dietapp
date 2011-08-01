package com.dietapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferences {
    private static final String APP_SHARED_PREFS = "profile_prefs";
    private SharedPreferences appSharedPrefs;
    private Editor prefsEditor;

    public AppPreferences(Context context)
    {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public int getInt(String n) {
        return appSharedPrefs.getInt(n,0);
    }

    public void saveInt(String n, int v) {
        prefsEditor.putInt(n,v);
        prefsEditor.commit();
    }
    
    public String getString(String n){
    	return appSharedPrefs.getString(n, "dne");
    }
    
    public void saveString(String n, String v){
    	prefsEditor.putString(n, v);
    	prefsEditor.commit();
    }
}