package com.app.fileonthespotclaim;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.entity.AccidentDetailsType;
import com.app.entity.DriverDetailsType;
import com.app.entity.LicenseType;
import com.app.entity.PeriodOfInsuranceType;
import com.app.entity.PhoneType;
import com.app.entity.PolicyHolderDetailsType;
import com.app.entity.VehicleDetailsType;
import com.app.sqlite.ClaimDataSQLHelper;
import com.example.fileonthespotclaim.R;


public class MainActivity extends ActionBarActivity {

	private Button nc;
	private Button ec;
	protected String currLocation;
	protected ClaimDataSQLHelper claimDataSQLHelper;
	private static final String DATABASE_NAME = "ClaimData.db";
	private static final int DATABASE_VERSION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nc = (Button) findViewById(R.id.btn_nc);
		ec = (Button) findViewById(R.id.btn_ec);

		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, PolicyHolderDetailsActivity.class);
				claimDataSQLHelper = new ClaimDataSQLHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
				SQLiteDatabase db = claimDataSQLHelper.getWritableDatabase();
				Cursor cursor = db.query(ClaimDataSQLHelper.TABLE, null, null, null, null, null, null);
				startManagingCursor(cursor);
				if(cursor.moveToLast()) {
					//0 will give base id
					PolicyHolderDetailsType policyHolderDetails = getPolicyHolderDetails(cursor);
					VehicleDetailsType vehicleDetails = getVehicleDetails(cursor);
					AccidentDetailsType accidentDetails = getAccidentDetails();
					DriverDetailsType driverDetails = getDriverDetails(cursor);
					myIntent.putExtra("policyHolderDetails", policyHolderDetails);
					myIntent.putExtra("vehicleDetails", vehicleDetails);
					myIntent.putExtra("accidentDetails", accidentDetails);
					myIntent.putExtra("driverDetails", driverDetails);
				}
				
				MainActivity.this.startActivity(myIntent);
			}
		};

		nc.setOnClickListener(myListener);

		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, GetExistingClaimsActivity.class);
				claimDataSQLHelper = new ClaimDataSQLHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
				SQLiteDatabase db = claimDataSQLHelper.getWritableDatabase();
				Cursor cursor = db.query(ClaimDataSQLHelper.TABLE, null, null, null, null, null, null);
				startManagingCursor(cursor);
				if(cursor.moveToLast()) {
					String policyNo = cursor.getString(1);
					myIntent.putExtra("policyNo", policyNo);
				}
				MainActivity.this.startActivity(myIntent);
			}
		};

		ec.setOnClickListener(myListener2);

	}



	protected DriverDetailsType getDriverDetails(Cursor cursor) {
		DriverDetailsType driverDetails = new DriverDetailsType();
		driverDetails.setName(cursor.getString(5));
		driverDetails.setRelationWithInsured("self");
		driverDetails.setDOB(cursor.getString(6));
		driverDetails.setAddress(cursor.getString(7));
		driverDetails.setContactNo(cursor.getString(11));
		LicenseType licenseType = new LicenseType();
		licenseType.setLicenseNo(cursor.getString(21));
		licenseType.setIssuingRTO(cursor.getString(22));
		licenseType.setEffectiveFrom(cursor.getString(23));
		licenseType.setExpiryDate(cursor.getString(24));
		licenseType.setClazz(cursor.getString(25));
		licenseType.setType(cursor.getString(26));
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
		accidentDetails.setPlace("");
		accidentDetails.setPoliceStationName("");
		accidentDetails.setMileage("15");
		accidentDetails.setFIRNo("");
		return accidentDetails;
	}


	protected VehicleDetailsType getVehicleDetails(Cursor cursor) {
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
		vehicleDetails.setRegdNo(cursor.getString(13));
		vehicleDetails.setMake(cursor.getString(14));
		vehicleDetails.setDateOfFirstRegistration(cursor.getString(15));
		vehicleDetails.setChassisNo(cursor.getString(16));
		vehicleDetails.setEngineNo(cursor.getString(17));
		vehicleDetails.setDateOfTransfer(cursor.getString(18));
		vehicleDetails.setTypeOfFuel(cursor.getString(19));
		vehicleDetails.setColor(cursor.getString(20));
		return vehicleDetails;
	}

	protected PolicyHolderDetailsType getPolicyHolderDetails(Cursor cursor) {
		PolicyHolderDetailsType policyHolderDetails = new PolicyHolderDetailsType();
		policyHolderDetails.setPolicyNo(cursor.getString(1));
		policyHolderDetails.setCoverNoteNo(cursor.getString(2));
		PeriodOfInsuranceType periodOfInsurance = new PeriodOfInsuranceType();
		periodOfInsurance.setFrom(cursor.getString(3));
		periodOfInsurance.setTo(cursor.getString(4));
		policyHolderDetails.setPeriodOfInsurance(periodOfInsurance );
		policyHolderDetails.setNameOfInsured(cursor.getString(5));
		policyHolderDetails.setDobOfInsured(cursor.getString(6));
		policyHolderDetails.setAddressOfInsured(cursor.getString(7));
		policyHolderDetails.setPinOfInsured(cursor.getString(8));
		PhoneType phoneType = new PhoneType();
		phoneType.setOffice(cursor.getString(9));
		phoneType.setResidence(cursor.getString(10));
		phoneType.setMobile(cursor.getString(11));
		policyHolderDetails.setPhoneType(phoneType);
		policyHolderDetails.setEmailOfInsured(cursor.getString(12));
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

	
}
