package com.app.fileonthespotclaim;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.app.service.entity.AccidentDetailsType;
import com.app.service.entity.DriverDetailsType;
import com.app.service.entity.LicenseType;
import com.app.service.entity.PeriodOfInsuranceType;
import com.app.service.entity.PhoneType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.app.service.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	Button nc;
	Button ec;

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
		Address currAddress = null;
		LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
		if(location != null) {
			//location.
			Geocoder geoCoder = new Geocoder(getApplicationContext());
			try {
				 List<Address> address = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
				 if(address.size() == 1)
					 currAddress = address.get(0);
			} catch (Exception e) {
				// TODO Do not throw, if location is not availale, ask user to enter..
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		
		//TODO set nearest policestation name
		if(currAddress!=null)
			accidentDetails.setPlace(currAddress.getSubAdminArea() + ", " + currAddress.getSubLocality() + ", " + currAddress.getLocality());
		else
			accidentDetails.setPlace("");
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
