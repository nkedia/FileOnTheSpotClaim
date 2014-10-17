package com.app.fileonthespotclaim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.app.service.entity.PolicyHolderDetailsType;
import com.app.service.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VehicleDetailsActivity extends ActionBarActivity {
	
	Button bt;
	EditText regdNo;
	EditText make;
	EditText regDate;
	EditText chassisNo;
	EditText engineNo;
	EditText transferDtae;
	EditText fuelType;
	EditText color;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_details);
		
		bt = (Button) findViewById(R.id.vd_next);
		regdNo = (EditText) findViewById(R.id.editText1);
		make = (EditText) findViewById(R.id.editText2);
		regDate = (EditText) findViewById(R.id.editText3);
		chassisNo = (EditText) findViewById(R.id.editText4);
		engineNo = (EditText) findViewById(R.id.editText5);
		transferDtae = (EditText) findViewById(R.id.editText6);
		fuelType = (EditText) findViewById(R.id.editText7);
		color = (EditText) findViewById(R.id.editText8);
	        
        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Intent myIntent = new Intent(VehicleDetailsActivity.this, AccidentDetailsActivity.class);
	        			PolicyHolderDetailsType policyHolderDetails = (PolicyHolderDetailsType) getIntent().getParcelableExtra("policyHolderDetails");
	        			VehicleDetailsType vehicleDetails = getVehicleDetails();
	        			myIntent.putExtra("policyHolderDetails", policyHolderDetails);
	        			myIntent.putExtra("vehicleDetails", vehicleDetails);
	        			Log.d("phd", policyHolderDetails.toString());
	        			Log.d("phd", policyHolderDetails.getCoverNoteNo());
	        			Log.d("phd", policyHolderDetails.getPolicyNo());
	        			
	        			VehicleDetailsActivity.this.startActivity(myIntent);
	        		}
	        };
	        
	        bt.setOnClickListener(myListener);
	}

	protected VehicleDetailsType getVehicleDetails() {
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
			/*SimpleDateFormat sf = new SimpleDateFormat("mm/dd/yyyy");
			
			Date fromDate = sf.parse(regDate.getText().toString());
			GregorianCalendar cal1 = new GregorianCalendar();
			cal1.setTime(fromDate);
			XMLGregorianCalendar gc1 =
			     DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
			
			Date toDate = sf.parse(transferDtae.getText().toString()); 
			GregorianCalendar cal2 = new GregorianCalendar();
			cal2.setTime(toDate);
			XMLGregorianCalendar gc2 =
			     DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);*/
			
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vehicle_details, menu);
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
