package com.dietapp;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemSelectedListener;

public class ManualActivity extends Activity implements OnClickListener,OnItemSelectedListener{
	
	Button foodButton,exerciseButton;
	String foodName, exerciseName;
	int foodCalories, foodServings, exerciseCalories;
	ViewFlipper vf;
	Spinner spinner;
	ArrayAdapter<CharSequence> adapter;
	DatabaseAdapter dbHelper;
	Calendar cal;
	String date;
	public String dateform = "MM/dd/yy";



	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.manual);
	    spinner = (Spinner) findViewById(R.id.manualSpinner);
	    adapter = ArrayAdapter.createFromResource(
	            this, R.array.manualArray, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    spinner.setOnItemSelectedListener(this);
		exerciseButton = (Button) findViewById(R.id.manualExerciseButton);
	    exerciseButton.setOnClickListener(this);
		foodButton = (Button) findViewById(R.id.manualFoodButton);
	    foodButton.setOnClickListener(this);
	    vf = (ViewFlipper) findViewById(R.id.manualFlipper);
	    dbHelper = new DatabaseAdapter(this);
		dbHelper.open();
	}

	@Override
	public void onClick(View v) {
		cal = Calendar.getInstance();
		date = DateFormat.format(dateform, cal.get(Calendar.DATE)).toString();
		if (foodButton.getId() == ((Button) v).getId()){
			EditText getInput = (EditText) findViewById(R.id.manualFoodName);
			if (getInput.getText().length() != 0)
				foodName = getInput.getText().toString();
			getInput = (EditText) findViewById(R.id.manualFoodCalories);
			if (getInput.getText().length() != 0)
				foodCalories = Integer.parseInt(getInput.getText().toString());
			getInput = (EditText) findViewById(R.id.manualFoodServings);
			if (getInput.getText().length() != 0)
				foodServings = Integer.parseInt(getInput.getText().toString());
			foodCalories = foodCalories * foodServings;
			dbHelper.createEntry(date, foodName, 0, foodCalories);
			dbHelper.close();
			finish();
		}
		else if (exerciseButton.getId() == ((Button) v).getId()){
			EditText getInput = (EditText) findViewById(R.id.manualExcerciseName);
			if (getInput.getText().length() != 0)
				exerciseName = getInput.getText().toString();
			getInput = (EditText) findViewById(R.id.manualExcerciseCalories);
			if (getInput.getText().length() != 0)
				exerciseCalories = Integer.parseInt(getInput.getText().toString());
			dbHelper.createEntry(date, exerciseName, 1, exerciseCalories);
			dbHelper.close();
			finish();
		}
		
		
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
				vf.setDisplayedChild(arg2);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}

}
