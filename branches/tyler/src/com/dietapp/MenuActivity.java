package com.dietapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class MenuActivity extends Activity implements OnTouchListener,OnClickListener{
	
	Button switchProfile,switchCurrentDay,switchDietLog;
	ViewFlipper vf;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
        LinearLayout layout = (LinearLayout) findViewById(R.id.welcomeLayout);
		layout.setOnTouchListener(this);
		vf = (ViewFlipper) findViewById(R.id.menuFlipper);
		switchProfile = (Button) findViewById(R.id.profileButton);
		switchProfile.setOnClickListener(this);
		switchCurrentDay = (Button) findViewById(R.id.nowButton);
		switchCurrentDay.setOnClickListener(this);
		switchDietLog = (Button) findViewById(R.id.logButton);
		switchDietLog.setOnClickListener(this);
		
		
		

    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		vf.setDisplayedChild(1);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (switchProfile.getId() == ((Button) v).getId()){
		Intent toProfile = new Intent(this,ProfileActivity.class);
		this.startActivity(toProfile);
		}
		else if (switchCurrentDay.getId() == ((Button) v).getId()){
			Intent toCurrentDay = new Intent(this,currentDay.class);
			this.startActivity(toCurrentDay);
		}
		else if (switchDietLog.getId() == ((Button) v).getId()){
			Intent toDietLog = new Intent(this,dietLog.class);
			this.startActivity(toDietLog);
		}
	}
}