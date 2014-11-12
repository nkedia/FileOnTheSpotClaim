package com.app.fileonthespotclaim;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.entity.AccidentDetailsType;
import com.app.task.FetchLocationTask;
import com.app.task.FetchPoliceStationTask;
import com.example.fileonthespotclaim.R;

public class AccidentDetailsActivity extends ActionBarActivity implements LocationListener{

	Button bt;
	Button map;
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
	String latLong = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accident_details);

		LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

		bt = (Button) findViewById(R.id.ad_next);
		map = (Button) findViewById(R.id.psMap);
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

		Button.OnClickListener mapListener = new Button.OnClickListener(){

			public void onClick(View v) {
				String uri = String.format(Locale.ENGLISH, "geo:" + latLong);
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
				AccidentDetailsActivity.this.startActivity(intent);
			}
		};

		map.setOnClickListener(mapListener);
	}

	private void setAccidentDetails(AccidentDetailsType accidentDetails,
			Boolean getExistingClaims) {
		date.setText(accidentDetails.getDateOfAccident());
		time.setText(accidentDetails.getTime());
		speed.setText(accidentDetails.getSpeed());
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
			place.setText(accidentDetails.getPlace());
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

	@Override
	public void onLocationChanged(Location location) {
		if(place.getText().toString().isEmpty()) {
			try { 
				latLong = location.getLatitude() + "," + location.getLongitude();
				String currPlace = new FetchLocationTask().execute(latLong).get();
				if(currPlace.length()>45) {
					currPlace = currPlace.substring(0, 44);
				}
				place.setText(currPlace);
				String policeStation = new FetchPoliceStationTask().execute(latLong).get();
				policeStationName.setText(policeStation);
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		Log.d("Latitude, Longitude", location.getLatitude() + ", " + location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.d("Latitude","disable");

	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.d("Latitude","enable");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("Latitude","status");
	}
}
