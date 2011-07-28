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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class currentDay extends ListActivity implements OnClickListener{
	
	private static final int ACTIVITY_CREATE=0;
	
	Button manualButton,scanButton,exerciseButton;
	
	
	private String testBarcode = "123456789012";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current);
        fillEntries();
        
    	manualButton = (Button) findViewById(R.id.manual);
    	scanButton = (Button) findViewById(R.id.scan);
    	exerciseButton = (Button) findViewById(R.id.exercise);
    
    	manualButton.setOnClickListener(this);
    	
    	scanButton.setOnClickListener(this);
    	
    	exerciseButton.setOnClickListener(this);
    	

    }
   
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    		case IntentIntegrator.REQUEST_CODE: {
    			if (resultCode != RESULT_CANCELED) {
    				IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    				if (scanResult != null) {
    					String upc = scanResult.getContents();
    					TextView tv = new TextView(this);
    					tv.setText(upc);
    					setContentView(tv);
    				}
    			}
    		break;
    		}
    	}
    }
   
   public void fillEntries(){
	   
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
		finish();
	}
	
}

    
    
}
