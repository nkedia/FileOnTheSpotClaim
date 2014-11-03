package com.app.fileonthespotclaim;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.app.entity.AccidentDetailsType;
import com.app.entity.DriverDetailsType;
import com.app.entity.LicenseType;
import com.app.entity.PeriodOfInsuranceType;
import com.app.entity.PhoneType;
import com.app.entity.PolicyHolderDetailsType;
import com.app.entity.VehicleDetailsType;
import com.app.task.FetchLocationTask;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements LocationListener{

	private Button nc;
	private Button ec;
	protected String currLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);

		nc = (Button) findViewById(R.id.btn_nc);
		ec = (Button) findViewById(R.id.btn_ec);

		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, PolicyHolderDetailsActivity.class);
				PolicyHolderDetailsType policyHolderDetails = getPolicyHolderDetails();
				VehicleDetailsType vehicleDetails = getVehicleDetails();
				AccidentDetailsType accidentDetails = getAccidentDetails();
				DriverDetailsType driverDetails = getDriverDetails();
				myIntent.putExtra("policyHolderDetails", policyHolderDetails);
				myIntent.putExtra("vehicleDetails", vehicleDetails);
				myIntent.putExtra("accidentDetails", accidentDetails);
				myIntent.putExtra("driverDetails", driverDetails);
				MainActivity.this.startActivity(myIntent);
			}
		};

		nc.setOnClickListener(myListener);

		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, GetExistingClaimsActivity.class);
				MainActivity.this.startActivity(myIntent);
			}
		};

		ec.setOnClickListener(myListener2);

	}



	protected DriverDetailsType getDriverDetails() {
		DriverDetailsType driverDetails = new DriverDetailsType();
		driverDetails.setName("Natasha Kedia");
		driverDetails.setRelationWithInsured("self");
		driverDetails.setDOB("1984-08-07");
		driverDetails.setAddress("Agate 104");
		driverDetails.setContactNo("8008288109");
		LicenseType licenseType = new LicenseType();
		licenseType.setLicenseNo("LicenseNo123");
		licenseType.setEffectiveFrom("2000-01-01");
		licenseType.setExpiryDate("2020-12-31");
		licenseType.setIssuingRTO("RTO");
		licenseType.setType("Permanent");
		licenseType.setClazz("HGV");
		driverDetails.setLicenseType(licenseType);
		return driverDetails;
	}



	protected AccidentDetailsType getAccidentDetails() {
		AccidentDetailsType accidentDetails = new AccidentDetailsType();
		String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String currTime = new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime());
		accidentDetails.setDateOfAccident(currDate);
		accidentDetails.setTime(currTime);
		accidentDetails.setSpeed("60");
		accidentDetails.setNoOfPeopleTravelling("1");
		//TODO set current location
		LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		if(!currLocation.isEmpty() && currLocation!=null)
			accidentDetails.setPlace(currLocation);
		else
			accidentDetails.setPlace("");
		//TODO set nearest policestation name
		accidentDetails.setPoliceStationName("");
		accidentDetails.setMileage("15");
		accidentDetails.setFIRNo("");
		return accidentDetails;
	}


	protected VehicleDetailsType getVehicleDetails() {
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
		vehicleDetails.setRegdNo("Regd123");
		vehicleDetails.setMake("Make123");
		vehicleDetails.setDateOfFirstRegistration("2010-10-10");
		vehicleDetails.setChassisNo("ChassisNo123");
		vehicleDetails.setEngineNo("EngineNo123");
		vehicleDetails.setDateOfTransfer("2012-10-10");
		vehicleDetails.setTypeOfFuel("fueltype123");
		vehicleDetails.setColor("color123");
		return vehicleDetails;
	}

	protected PolicyHolderDetailsType getPolicyHolderDetails() {
		PolicyHolderDetailsType policyHolderDetails = new PolicyHolderDetailsType();
		policyHolderDetails.setPolicyNo("policyno8431");
		policyHolderDetails.setCoverNoteNo("covernote123");
		PeriodOfInsuranceType periodOfInsurance = new PeriodOfInsuranceType();
		periodOfInsurance.setFrom("2013-12-10");
		periodOfInsurance.setTo("2014-12-10");
		policyHolderDetails.setPeriodOfInsurance(periodOfInsurance );
		policyHolderDetails.setNameOfInsured("Natasha Kedia");
		policyHolderDetails.setDobOfInsured("1984-08-07");
		policyHolderDetails.setAddressOfInsured("Agate 104");
		policyHolderDetails.setPinOfInsured("500049");
		PhoneType phoneType = new PhoneType();
		phoneType.setOffice("");
		phoneType.setMobile("8008288109");
		phoneType.setResidence("");
		policyHolderDetails.setPhoneType(phoneType);
		policyHolderDetails.setEmailOfInsured("natasha@gmail.com");
		return policyHolderDetails;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(MainActivity.this, "Settings option selected", Toast.LENGTH_LONG).show();
			Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
			MainActivity.this.startActivity(myIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		try {
			currLocation = new FetchLocationTask().execute(location.getLatitude(), location.getLongitude()).get();
			Log.d("Latitude, Longitude", location.getLatitude() + ", " + location.getLongitude());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
