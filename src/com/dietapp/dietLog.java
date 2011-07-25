package com.dietapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class dietLog extends ListActivity {
	
	//public MockDb mockDb;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.diet_log);
	        fillData();

	        ListView lv = getListView();
	        lv.setTextFilterEnabled(true);

	        lv.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	            goToMenu();
	          }
	        });

	        
	    	Button backButton = (Button) findViewById(R.id.back);
	    	
	    	backButton.setOnClickListener(new View.OnClickListener() {
	    		public void onClick(View view) {
	    			goToMenu();
	    		}
	    	});

	    }
	 
	 public void goToMenu(){
		 Intent i = new Intent(this, LogInfo.class);
		 startActivity(i);
	 }
	 
	  private void fillData() {
		  String[] Days = {"Day1", "Day2", "Day3", "Day4"};
		  setListAdapter(new ArrayAdapter<String>(this, R.layout.log_list, Days));
	  
	  }
	  
	  public void viewDay(int i){
		  //Intent inten = Intent(this, dayView.class);
		  //startActivity(inten);
	  }

}
