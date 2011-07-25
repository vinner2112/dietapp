package com.dietapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class currentDay extends ListActivity implements OnClickListener{
	
	private static final int ACTIVITY_CREATE=0;
	
	Button manualButton,scanButton,exerciseButton;
	
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
    					//put whatever you want to do with the code here
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
		IntentIntegrator.initiateScan(this);
	}
	else if (exerciseButton.getId() == ((Button) v).getId()){
		finish();
	}
	
}

    
    
}
