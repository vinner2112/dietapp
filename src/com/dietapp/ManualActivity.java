package com.dietapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

public class ManualActivity extends Activity implements OnClickListener,OnItemSelectedListener{
	
	Button foodButton,excerciseButton;
	String foodName, excerciseName;
	int foodCalories, foodServings, excerciseTime, excerciseCalories;
	ViewFlipper vf;
	Spinner spinner;
	ArrayAdapter<CharSequence> adapter;

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
		excerciseButton = (Button) findViewById(R.id.manualExcerciseButton);
	    excerciseButton.setOnClickListener(this);
		foodButton = (Button) findViewById(R.id.manualFoodButton);
	    foodButton.setOnClickListener(this);
	    vf = (ViewFlipper) findViewById(R.id.manualFlipper);
	}

	@Override
	public void onClick(View v) {
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
			Toast.makeText(this, "Entry made!", Toast.LENGTH_SHORT);
		}
		else if (excerciseButton.getId() == ((Button) v).getId()){
			EditText getInput = (EditText) findViewById(R.id.manualExcerciseName);
			if (getInput.getText().length() != 0)
				excerciseName = getInput.getText().toString();
			getInput = (EditText) findViewById(R.id.manualExcerciseCalories);
			if (getInput.getText().length() != 0)
				excerciseCalories = Integer.parseInt(getInput.getText().toString());
			getInput = (EditText) findViewById(R.id.manualExcerciseTime);
			if (getInput.getText().length() != 0)
				excerciseTime = Integer.parseInt(getInput.getText().toString());
			Toast.makeText(this, "Entry made!", Toast.LENGTH_SHORT);
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
