package com.app.fileonthespotclaim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.entity.AccidentDetailsType;
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
	TextView fir;
	Boolean getExistingClaims = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accident_details);

		bt = (Button) findViewById(R.id.ad_next);
		date = (EditText) findViewById(R.id.editText1);
		time = (EditText) findViewById(R.id.editText2);
		speed = (EditText) findViewById(R.id.editText3);
		place = (EditText) findViewById(R.id.editText4);
		peopleNo = (EditText) findViewById(R.id.editText5);
		policeStationName = (EditText) findViewById(R.id.editText7);
		firNo = (EditText) findViewById(R.id.editText8);
		mileage = (EditText) findViewById(R.id.editText9);
		fir = (TextView) findViewById(R.id.firNo);

		//TODO set map to nearest police station for edittext6
		
		AccidentDetailsType accidentDetails = (AccidentDetailsType) getIntent().getParcelableExtra("accidentDetails");
		getExistingClaims = getIntent().getBooleanExtra("getExistingClaims", false);
		setAccidentDetails(accidentDetails, getExistingClaims);

		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				AccidentDetailsType accidentDetails = getAccidentDetails();
				Intent myIntent = new Intent(AccidentDetailsActivity.this, StatementsActivity.class);
				myIntent.putExtra("policyHolderDetails", getIntent().getParcelableExtra("policyHolderDetails"));
				myIntent.putExtra("vehicleDetails", getIntent().getParcelableExtra("vehicleDetails"));
				myIntent.putExtra("accidentDetails", accidentDetails); 
				myIntent.putExtra("driverDetails", getIntent().getParcelableExtra("driverDetails"));
				if(getExistingClaims)
    				myIntent.putExtra("claimId", getIntent().getStringExtra("claimId"));
				myIntent.putExtra("getExistingClaims", getExistingClaims);
				AccidentDetailsActivity.this.startActivity(myIntent);
			}
		};

		bt.setOnClickListener(myListener);
	}

	private void setAccidentDetails(AccidentDetailsType accidentDetails,
			Boolean getExistingClaims) {
		date.setText(accidentDetails.getDateOfAccident());
		time.setText(accidentDetails.getTime());
		speed.setText(accidentDetails.getSpeed());
		place.setText(accidentDetails.getPlace());
		peopleNo.setText(accidentDetails.getNoOfPeopleTravelling());
		policeStationName.setText(accidentDetails.getPoliceStationName());
		mileage.setText(accidentDetails.getMileage());
		fir.setEnabled(false);
		firNo.setEnabled(false);
		
		if(getExistingClaims) {
			date.setEnabled(false);
			time.setEnabled(false);
			speed.setEnabled(false);
			place.setEnabled(false);
			peopleNo.setEnabled(false);
			mileage.setEnabled(false);
			policeStationName.setEnabled(true);
			fir.setEnabled(true);
			firNo.setEnabled(true);
			firNo.setText(accidentDetails.getFIRNo());
			
		}
		
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
		menu.findItem(R.id.action_settings).setVisible(false);
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
