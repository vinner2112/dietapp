package com.dietapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class dietLog extends ListActivity {
	
	private DatabaseAdapter dbHelper;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.diet_log);
	        fillData();

	        //ListView lv = getListView();
	        //lv.setTextFilterEnabled(true);

	        //lv.setOnItemClickListener(new OnItemClickListener() {
	         // public void onItemClick(AdapterView<?> parent, View view,
	          //    int position, long id) {
	          //  goToMenu();
	         // }
	       // });

	        
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
		  Cursor c = dbHelper.fetchAllCurrentEntries();
		  startManagingCursor(c);
		  String[] dates = new String[] { DatabaseAdapter.KEY_currentDate };
		  int[] to = new int[] {R.id.text1};

		  SimpleCursorAdapter entries = new SimpleCursorAdapter(this,
					R.layout.calorie_table, c, dates, to);
		  setListAdapter(entries);
	  
	  }
	  
	  public void viewDay(int i){
		  //Intent inten = Intent(this, dayView.class);
		  //startActivity(inten);
	  }

}
