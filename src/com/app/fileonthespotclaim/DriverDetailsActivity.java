package com.app.fileonthespotclaim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.entity.DriverDetailsType;
import com.app.entity.LicenseType;
import com.example.fileonthespotclaim.R;

public class DriverDetailsActivity extends ActionBarActivity {

	Button bt;
	EditText name;
	EditText relation;
	EditText address;
	EditText number;
	EditText dob;
	EditText licenseNo;
	EditText issuingRTO;
	EditText effectiveFrom;
	EditText expiryDate;
	Spinner vehicleClassSpinner;
	Spinner vehicleTypeSpinner;
	String vehicleClass;
	String vehicleType;
	Boolean getExistingClaims = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_details);

		bt = (Button) findViewById(R.id.dd_next);
		name = (EditText) findViewById(R.id.editText1);
		relation = (EditText) findViewById(R.id.editText2);
		address = (EditText) findViewById(R.id.editText3);
		number = (EditText) findViewById(R.id.editText4);
		dob = (EditText) findViewById(R.id.editText5);
		licenseNo = (EditText) findViewById(R.id.editText6);
		issuingRTO = (EditText) findViewById(R.id.editText7);
		effectiveFrom = (EditText) findViewById(R.id.editText8);
		expiryDate = (EditText) findViewById(R.id.editText9);
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
        
        DriverDetailsType driverDetails = (DriverDetailsType) getIntent().getParcelableExtra("driverDetails");
		getExistingClaims = getIntent().getBooleanExtra("getExistingClaims", false);
		setDriverDetails(driverDetails, getExistingClaims);
		
		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(DriverDetailsActivity.this, DocumentsActivity.class);
				DriverDetailsType driverDetails = getDriverDetails();
				myIntent.putExtra("policyHolderDetails", getIntent().getParcelableExtra("policyHolderDetails"));
				myIntent.putExtra("vehicleDetails", getIntent().getParcelableExtra("vehicleDetails"));
				myIntent.putExtra("accidentDetails", getIntent().getParcelableExtra("accidentDetails"));
				myIntent.putExtra("driverDetails", driverDetails);
				if(getExistingClaims)
    				myIntent.putExtra("claimId", getIntent().getStringExtra("claimId"));
				myIntent.putExtra("getExistingClaims", getExistingClaims);
				DriverDetailsActivity.this.startActivity(myIntent);
			}
		};

		bt.setOnClickListener(myListener);
	}
	
	
	private void setDriverDetails(DriverDetailsType driverDetails,
			Boolean getExistingClaims) {
		name.setText(driverDetails.getName());
		relation.setText(driverDetails.getRelationWithInsured());
		address.setText(driverDetails.getAddress());
		number.setText(driverDetails.getContactNo());
		dob.setText(driverDetails.getDOB());
		licenseNo.setText(driverDetails.getLicenseType().getLicenseNo());
		issuingRTO.setText(driverDetails.getLicenseType().getIssuingRTO());
		effectiveFrom.setText(driverDetails.getLicenseType().getEffectiveFrom());
		expiryDate.setText(driverDetails.getLicenseType().getExpiryDate());
		int position = 0;
		if(driverDetails.getLicenseType().getClazz().equals("MCycle"))
			position = 0;
		else if(driverDetails.getLicenseType().getClazz().equals("LMV"))
			position = 1;
		else if(driverDetails.getLicenseType().getClazz().equals("HGV"))
			position = 2;
		else if(driverDetails.getLicenseType().getClazz().equals("Transport"))
			position = 3;
		else
			position = 4;
		vehicleClassSpinner.setSelection(position);
		
		if(driverDetails.getLicenseType().getType().equals("Permanent"))
			position = 0;
		else
			position = 1;
		vehicleTypeSpinner.setSelection(position);
		
		if(getExistingClaims) {
			name.setEnabled(false);
			relation.setEnabled(false);
			address.setEnabled(false);
			number.setEnabled(false);
			dob.setEnabled(false);
			licenseNo.setEnabled(false);
			issuingRTO.setEnabled(false);
			effectiveFrom.setEnabled(false);
			expiryDate.setEnabled(false);
			vehicleClassSpinner.setEnabled(false);
			vehicleTypeSpinner.setEnabled(false);
			
		}
		
	}


	protected DriverDetailsType getDriverDetails() {
		DriverDetailsType driverDetails = new DriverDetailsType();
			
		driverDetails.setName(name.getText().toString());
		driverDetails.setRelationWithInsured(relation.getText().toString());
		driverDetails.setAddress(address.getText().toString());
		driverDetails.setContactNo(number.getText().toString());
		driverDetails.setDOB(dob.getText().toString());
		LicenseType licenseType = new LicenseType(licenseNo.getText().toString(), issuingRTO.getText().toString(),
				effectiveFrom.getText().toString(), expiryDate.getText().toString(), 
				vehicleClass, vehicleType);
		driverDetails.setLicenseType(licenseType);
			
		return driverDetails;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.driver_details, menu);
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
