package com.dietapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class currentDay extends ListActivity implements OnClickListener{
	
	private static final int ACTIVITY_CREATE=0;
	
	Button manualButton,scanButton,exerciseButton;
	
	String upc;
	
	private String testBarcode = "123456789012";
	
	int exerciseCalories = 0;
	
	String exerciseName = "";
	
    protected AppPreferences appPrefs;
    
	private DatabaseAdapter dbHelper;
	
	private Cursor cursor;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current);
        
	    appPrefs = new AppPreferences(getApplicationContext());
        
    	manualButton = (Button) findViewById(R.id.manual);
    	scanButton = (Button) findViewById(R.id.scan);
    	exerciseButton = (Button) findViewById(R.id.exercise);
    
    	manualButton.setOnClickListener(this);
    	
    	scanButton.setOnClickListener(this);
    	
    	exerciseButton.setOnClickListener(this);
    	
		dbHelper = new DatabaseAdapter(this);
		dbHelper.open();
		fillData();
		registerForContextMenu(getListView());
    }
    
    private void fillData(){
    	
    	
		cursor = dbHelper.fetchAllCurrentEntries();
		startManagingCursor(cursor);

		String[] from = new String[] { DatabaseAdapter.KEY_currentName,DatabaseAdapter.KEY_currentCalories };
		int[] to = new int[] { R.id.entryname,R.id.entrycalories };

		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.currentrow, cursor, from, to);
		setListAdapter(notes);
    	
    }
    
    
   
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    		case IntentIntegrator.REQUEST_CODE: {
    			if (resultCode != RESULT_CANCELED) {
    				IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    				if (scanResult != null) {
    					upc = scanResult.getContents();
    				}
    			}
    		break;
    		}
    	}
    }
   
   public void viewLog(){
	   Intent i = new Intent(this, dietLog.class);
	   startActivity(i);
   }

@Override
public void onClick(View v) {
	if (manualButton.getId() == ((Button) v).getId()){
		Intent i = new Intent(this,ManualActivity.class);
		startActivity(i);
	}
	else if (scanButton.getId() == ((Button) v).getId()){
		//IntentIntegrator.initiateScan(this);
		
		InputStream is = null;
		String result = "";
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("sendCode",testBarcode));
		try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://www.creepedon.com/android.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
			}catch(Exception e){
			Log.e("log_tag", "Error in http "+e.toString());
			}try{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	        }
	        is.close();
	 
	        result=sb.toString();
			}catch(Exception e){
				Log.e("log_tag", "Error converting "+e.toString());
		}	try{
	        JSONArray jArray = new JSONArray(result);
	        for(int i=0;i<jArray.length();i++){
	                JSONObject json_data = jArray.getJSONObject(i);
	                TextView text = new TextView(this);
	                String imthebest = "Barcode: " + json_data.getLong("barcode") + "\nName: " + json_data.getString("name") + "\nCalories:" + json_data.getInt("calories") + "\n\n\nIT FUCKING WORKS!!!!!!!!!!!!!!!!";
	                text.setText(imthebest);
	                setContentView(text);
	        }
			}catch(Exception e){
				Log.e("log_tag", "Error JSON "+e.toString());
		}
	
	}
	else if (exerciseButton.getId() == ((Button) v).getId()){
		final int weight = appPrefs.getInt("weight");
		AlertDialog.Builder showList = new AlertDialog.Builder(this);
		showList.setTitle("Select an exercise (30 minutes)");
		final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.exerciseArray, android.R.layout.simple_list_item_1);
		showList.setAdapter(adapter, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        switch(item){
		        case 0: //aerobics
		        	exerciseCalories = (weight/10) * 15 - 5;
		        	break;
		        case 1: //badminton
		        	exerciseCalories = (weight/10) * 15;
		        	break;
		        case 2: //basketball
		        	exerciseCalories = (weight/10) * 22;
		        	break;
		        case 3: //bicycling
		        	exerciseCalories = (weight/10) * 13 -5;
		        	break;
		        case 4: //dancing
		        	exerciseCalories = (weight/10) * 10;
		        	break;
		        case 5: //golfing
		        	exerciseCalories = (weight/10) * 10;
		        	break;
		        case 6: //hiking
		        	exerciseCalories = (weight/10) * 18;
		        	break;
		        case 7: //housework
		        	exerciseCalories = (weight/10) * 9;
		        	break;
		        case 8: //jogging (12 min mile)
		        	exerciseCalories = (weight/10) * 18 + 5;
		        	break;
		        case 9: //jogging (10 min mile)
		        	exerciseCalories = (weight/10) * 23;
		        	break;
		        case 10: //running (7.5 min mile)
		        	exerciseCalories = (weight/10) * 31 - 5;
		        	break;
		        case 11: //running (6.7 min mile)
		        	exerciseCalories = (weight/10) * 33;
		        	break;
		        case 12: //running (6 min mile)
		        	exerciseCalories = (weight/10) * 35;
		        	break;
		        case 13: //scuba diving
		        	exerciseCalories = (weight/10) * 19;
		        	break;
		        case 14: // skiing
		        	exerciseCalories = (weight/10) * 13;
		        	break;
		        case 15: // soccer
		        	exerciseCalories = (weight/10) * 19 + 5;
		        	break;
		        case 16: //stair climbing
		        	exerciseCalories = (weight/10) * 16;
		        	break;
		        case 17: //swimming (25 yds/min)
		        	exerciseCalories = (weight/10) * 9;
		        	break;
		        case 18: //swimming (50 yds/min)
		        	exerciseCalories = (weight/10) * 23 - 5;
		        	break;
		        case 19: //tennis
		        	exerciseCalories = (weight/10) * 16;
		        	break;
		        case 20: //volleyball
		        	exerciseCalories = (weight/10) * 12;
		        	break;
		        case 21: //walking (30 min mile)
		        	exerciseCalories = (weight/10) * 6;
		        	break;
		        case 22: //walking (20 min mile)
		        	exerciseCalories = (weight/10) * 8;
		        	break;
		        case 23: //walking (15 min mile)
		        	exerciseCalories = (weight/10) * 10;
		        	break;
		        case 24: //weight training (40 sec between sets)
		        	exerciseCalories = (weight/10) * 25 + 5;
		        	break;
		        case 25: //weight training (60 sec between sets)
		        	exerciseCalories = (weight/10) * 19;
		        	break;
		        case 26: //weight training (90 sec between sets)
		        	exerciseCalories = (weight/10) * 9 - 5;
		        	break;
		        }
		    	exerciseName = adapter.getItem(item).toString();
		    	//Toast.makeText(currentDay.this, Integer.toString(exerciseCalories), Toast.LENGTH_LONG).show();
		    	dbHelper.createEntry(exerciseName, 1, exerciseCalories);
		    	fillData();
		    }
		});
		
		showList.show();
	}
	
}

    
protected void onDestroy() {
	super.onDestroy();
	if (dbHelper != null) {
		dbHelper.close();
	}
}

    
}
