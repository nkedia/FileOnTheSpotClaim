package com.app.fileonthespotclaim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.service.entity.AccidentDetailsType;
import com.app.service.entity.DriverDetailsType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.app.service.entity.VehicleDetailsType;
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
	EditText vehicleClass;
	EditText vehicleType;
	
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
	     vehicleClass = (EditText) findViewById(R.id.editText10);
	     vehicleType = (EditText) findViewById(R.id.editText11);
	     
	        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Intent myIntent = new Intent(DriverDetailsActivity.this, DocumentsActivity.class);
	        			DriverDetailsType driverDetails = getDriverDetails();
	        			PolicyHolderDetailsType policyHolderDetails = (PolicyHolderDetailsType) getIntent().getParcelableExtra("policyHolderDetails");
	        			VehicleDetailsType vehicleDetails = (VehicleDetailsType) getIntent().getParcelableExtra("vehicleDetails");
	        			AccidentDetailsType accidentDetails = (AccidentDetailsType) getIntent().getParcelableExtra("accidentDetails");
	        			myIntent.putExtra("policyHolderDetails", policyHolderDetails);
	        			myIntent.putExtra("vehicleDetails", vehicleDetails);
	        			myIntent.putExtra("accidentDetails", accidentDetails);
	        			myIntent.putExtra("driverDetails", driverDetails);
	        			DriverDetailsActivity.this.startActivity(myIntent);
	        		}
	        };
	        
	        bt.setOnClickListener(myListener);
	}

	protected DriverDetailsType getDriverDetails() {
		DriverDetailsType driverDetails = new DriverDetailsType();
			/*SimpleDateFormat sf = new SimpleDateFormat("mm/dd/yyyy");
	
			Date dobDate = sf.parse(dob.getText().toString());
			GregorianCalendar cal1 = new GregorianCalendar();
			cal1.setTime(dobDate);
			XMLGregorianCalendar gc1 =
			     DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
			
			Date effectiveFromDate = sf.parse(effectiveFrom.getText().toString()); 
			GregorianCalendar cal2 = new GregorianCalendar();
			cal2.setTime(effectiveFromDate);
			XMLGregorianCalendar gc2 =
			     DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
			
			Date expiryDatedt = sf.parse(expiryDate.getText().toString()); 
			GregorianCalendar cal3 = new GregorianCalendar();
			cal3.setTime(expiryDatedt);
			XMLGregorianCalendar gc3 =
			     DatatypeFactory.newInstance().newXMLGregorianCalendar(cal3);*/
			
		driverDetails.setName(name.getText().toString());
		driverDetails.setRelationWithInsured(relation.getText().toString());
		driverDetails.setAddress(address.getText().toString());
		driverDetails.setContactNo(number.getText().toString());
		driverDetails.setDOB(dob.getText().toString());
		driverDetails.setLicenseNo(licenseNo.getText().toString());
		driverDetails.setIssuingRTO(issuingRTO.getText().toString());
		driverDetails.setEffectiveFrom(effectiveFrom.getText().toString());
		driverDetails.setExpiryDate(expiryDate.getText().toString());
		driverDetails.setClazz(vehicleClass.getText().toString());
		driverDetails.setType(vehicleType.getText().toString());
			
		return driverDetails;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.driver_details, menu);
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
