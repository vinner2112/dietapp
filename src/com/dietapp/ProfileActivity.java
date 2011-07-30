package com.dietapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileActivity extends Activity implements OnClickListener{
	
    String name;
    int goalWeight, goalTime, age, height, weight,deficit;
    float bmr;
    boolean male;
    
    protected AppPreferences appPrefs;
    

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.profile);
	    Button save = (Button) findViewById(R.id.saveProfileButton);
	    save.setOnClickListener(this);
	    appPrefs = new AppPreferences(getApplicationContext());
	}

	@Override
	public void onClick(View arg0) {
		EditText inputField = (EditText) findViewById(R.id.ageInput);
		age = Integer.parseInt(inputField.getText().toString());
		inputField = (EditText) findViewById(R.id.goalTimeInput);
		goalTime = Integer.parseInt(inputField.getText().toString());
		inputField = (EditText) findViewById(R.id.goalWeightInput);
		goalWeight = Integer.parseInt(inputField.getText().toString());
		inputField = (EditText) findViewById(R.id.heightInput);
		height = Integer.parseInt(inputField.getText().toString());
		inputField = (EditText) findViewById(R.id.nameInput);
		name = inputField.getText().toString();
		inputField = (EditText) findViewById(R.id.weightInput);
		weight = Integer.parseInt(inputField.getText().toString());
		RadioButton rb_male = (RadioButton) findViewById(R.id.radio_male);
		RadioButton rb_female = (RadioButton) findViewById(R.id.radio_female);
		if (rb_male.isChecked()){
			male = true;
			bmr = 66 + (6.23f * weight) + (12.7f * height) - (6.8f * age);
		}
		else if (rb_female.isChecked()){
			male = false;
			bmr = 665 + (4.35f * weight) + (4.7f * height) - (4.7f * age);
		}
		deficit = ((goalWeight * 3500)/goalTime) - (int) bmr;
		Toast.makeText(this, "Profile saved!", Toast.LENGTH_LONG).show();
		appPrefs.saveString("name",name);
		appPrefs.saveInt("weight", weight);
		appPrefs.saveInt("bmr", (int)bmr);
		appPrefs.saveInt("deficit", deficit);
	}

}
