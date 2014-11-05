package com.app.fileonthespotclaim;

import com.app.entity.DriverDetailsType;
import com.app.entity.LicenseType;
import com.app.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

//Enter Vehicle Details
public class SettingsActivity1 extends ActionBarActivity {
	
	Button bt;
	EditText regdNo;
	EditText make;
	EditText regDate;
	EditText chassisNo;
	EditText engineNo;
	EditText transferDtae;
	EditText fuelType;
	EditText color;
	EditText licenseNo;
	EditText issuingRTO;
	EditText effectiveFrom;
	EditText expiryDate;
	Spinner vehicleClassSpinner;
	Spinner vehicleTypeSpinner;
	String vehicleClass;
	String vehicleType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings1);
		
		regdNo = (EditText) findViewById(R.id.editText14);
		make = (EditText) findViewById(R.id.editText15);
		regDate = (EditText) findViewById(R.id.editText16);
		chassisNo = (EditText) findViewById(R.id.editText17);
		engineNo = (EditText) findViewById(R.id.editText18);
		transferDtae = (EditText) findViewById(R.id.editText19);
		fuelType = (EditText) findViewById(R.id.editText20);
		color = (EditText) findViewById(R.id.editText21);
		licenseNo = (EditText) findViewById(R.id.editText22);
		issuingRTO = (EditText) findViewById(R.id.editText23);
		effectiveFrom = (EditText) findViewById(R.id.editText24);
		expiryDate = (EditText) findViewById(R.id.editText25);
		vehicleClassSpinner = (Spinner) findViewById(R.id.vehicleClassSpinner);
		vehicleTypeSpinner = (Spinner) findViewById(R.id.vehicleTypeSpinner);
		
		vehicleClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                vehicleClass = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        vehicleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                vehicleType = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
		bt = (Button) findViewById(R.id.next);
		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				VehicleDetailsType vehicleDetails = getVehicleDetails();
				DriverDetailsType driverDetails = getDriverDetails();
				Intent myIntent = new Intent(SettingsActivity1.this, SettingsActivity2.class);
				myIntent.putExtra("policyHolderDetails", getIntent().getParcelableExtra("policyHolderDetails"));
    			myIntent.putExtra("vehicleDetails", vehicleDetails);
    			myIntent.putExtra("driverDetails", driverDetails);
				SettingsActivity1.this.startActivity(myIntent);	
			}
		};
		bt.setOnClickListener(myListener);
	}

	protected VehicleDetailsType getVehicleDetails() {
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
		vehicleDetails.setRegdNo(regdNo.getText().toString());
		vehicleDetails.setMake(make.getText().toString());
		vehicleDetails.setDateOfFirstRegistration(regDate.getText().toString());
		vehicleDetails.setChassisNo(chassisNo.getText().toString());
		vehicleDetails.setEngineNo(engineNo.getText().toString());
		vehicleDetails.setDateOfTransfer(transferDtae.getText().toString());
		vehicleDetails.setTypeOfFuel(fuelType.getText().toString());
		vehicleDetails.setColor(color.getText().toString());
		return vehicleDetails;
	}
	
	protected DriverDetailsType getDriverDetails() {
		DriverDetailsType driverDetails = new DriverDetailsType();
		LicenseType licenseType = new LicenseType(licenseNo.getText().toString(), issuingRTO.getText().toString(),
				effectiveFrom.getText().toString(), expiryDate.getText().toString(), 
				vehicleClass, vehicleType);
		driverDetails.setLicenseType(licenseType);
		return driverDetails;
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
