package com.app.fileonthespotclaim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.service.entity.AccidentDetailsType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.app.service.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

public class AccidentDetailsActivity extends ActionBarActivity {

	Button bt;
	EditText date;
	EditText time;
	EditText speed;
	EditText place;
	EditText peopleNo;
	EditText policeStationName;
	EditText firNo;
	EditText mileage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accident_details);
		
		 bt = (Button) findViewById(R.id.ad_next);
		 date = (EditText) findViewById(R.id.editText1);
		 String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		 date.setText(currDate);
	     time = (EditText) findViewById(R.id.editText2);
	     String currTime = new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime());
	     time.setText(currTime);
	     speed = (EditText) findViewById(R.id.editText3);
	     place = (EditText) findViewById(R.id.editText4);
	     peopleNo = (EditText) findViewById(R.id.editText5);
	     policeStationName = (EditText) findViewById(R.id.editText7);
	     firNo = (EditText) findViewById(R.id.editText8);
	     mileage = (EditText) findViewById(R.id.editText9);
	     
	        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Intent myIntent = new Intent(AccidentDetailsActivity.this, StatementsActivity.class);
	        			AccidentDetailsType accidentDetails = getAccidentDetails();
	        			PolicyHolderDetailsType policyHolderDetails = (PolicyHolderDetailsType) getIntent().getParcelableExtra("policyHolderDetails");
	        			VehicleDetailsType vehicleDetails = (VehicleDetailsType) getIntent().getParcelableExtra("vehicleDetails");
	        			myIntent.putExtra("policyHolderDetails", policyHolderDetails);
	        			myIntent.putExtra("vehicleDetails", vehicleDetails);
	        			myIntent.putExtra("accidentDetails", accidentDetails); 
	        			AccidentDetailsActivity.this.startActivity(myIntent);
	        		}
	        };
	        
	        bt.setOnClickListener(myListener);
	}

	protected AccidentDetailsType getAccidentDetails() {
		AccidentDetailsType accidentDetails = new AccidentDetailsType();
		accidentDetails.setDateOfAccident(date.getText().toString());
		accidentDetails.setTime(time.getText().toString());
		accidentDetails.setSpeed(speed.getText().toString());
		accidentDetails.setPlace(place.getText().toString());
		accidentDetails.setNoOfPeopleTravelling(peopleNo.getText().toString());
		accidentDetails.setPoliceStationName(policeStationName.getText().toString());
		accidentDetails.setFIRNo(firNo.getText().toString());
		accidentDetails.setMileage(mileage.getText().toString());
		
		return accidentDetails;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accident_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
