package com.app.fileonthespotclaim;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.app.entity.PolicyHolderDetailsType;
import com.app.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

public class VehicleDetailsActivity extends ActionBarActivity {
	
	Button bt;
	EditText regdNo;
	EditText make;
	Button regDate;
	EditText chassisNo;
	EditText engineNo;
	Button transferDtae;
	EditText fuelType;
	EditText color;
	Boolean getExistingClaims = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_details);
		
		bt = (Button) findViewById(R.id.vd_next);
		regdNo = (EditText) findViewById(R.id.editText1);
		make = (EditText) findViewById(R.id.editText2);
		regDate = (Button) findViewById(R.id.firstRegDate);
		chassisNo = (EditText) findViewById(R.id.editText4);
		engineNo = (EditText) findViewById(R.id.editText5);
		transferDtae = (Button) findViewById(R.id.transferDate);
		fuelType = (EditText) findViewById(R.id.editText7);
		color = (EditText) findViewById(R.id.editText8);
		

		VehicleDetailsType vehicleDetails = (VehicleDetailsType) getIntent().getParcelableExtra("vehicleDetails");
		getExistingClaims = getIntent().getBooleanExtra("getExistingClaims", false);
		setVehicleDetails(vehicleDetails, getExistingClaims);
	        
        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			VehicleDetailsType vehicleDetails = getVehicleDetails();
	        			Intent myIntent = new Intent(VehicleDetailsActivity.this, AccidentDetailsActivity.class);
	        			myIntent.putExtra("policyHolderDetails", getIntent().getParcelableExtra("policyHolderDetails"));
	        			myIntent.putExtra("vehicleDetails", vehicleDetails);
	        			myIntent.putExtra("accidentDetails", getIntent().getParcelableExtra("accidentDetails"));
	    				myIntent.putExtra("driverDetails", getIntent().getParcelableExtra("driverDetails"));
	    				if(getExistingClaims)
	    					myIntent.putExtra("claimId", getIntent().getStringExtra("claimId"));
	    				myIntent.putExtra("getExistingClaims", getExistingClaims);
	        			VehicleDetailsActivity.this.startActivity(myIntent);
	        		}
	        };
	        
	        bt.setOnClickListener(myListener);
	        
	      //get Date of First Registration
			Button.OnClickListener myListener1 = new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					int myyear;
					int mymonth;
					int mydate;
					if(regDate.getText().toString().isEmpty()) {
						Calendar c = Calendar.getInstance();
						myyear = c.get(Calendar.YEAR);
						mymonth = c.get(Calendar.MONTH);
						mydate = c.get(Calendar.DATE);
					} else {
						String date[] = regDate.getText().toString().split("-");
						myyear = Integer.parseInt(date[0]);
						mymonth = Integer.parseInt(date[1])-1;
						mydate = Integer.parseInt(date[2]);
					}


					DatePickerDialog ddp = new DatePickerDialog(VehicleDetailsActivity.this, new OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							Log.d("date set", year + "/" +monthOfYear + "/" + dayOfMonth);
							monthOfYear++;
							String month = monthOfYear + "";
							String day = dayOfMonth + "";
							if(monthOfYear < 10)
								month = "0" + monthOfYear;
							if(dayOfMonth < 10)
								day = "0" + dayOfMonth;
							String regDateString = year + "-" + month + "-" + day;
							regDate.setText(regDateString);
						}

					}, myyear, mymonth, mydate);

					ddp.show();
				}
			};
			regDate.setOnClickListener(myListener1);

			// get Date of Transfer
			Button.OnClickListener myListener2 = new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					int myyear;
					int mymonth;
					int mydate;
					if(transferDtae.getText().toString().isEmpty()) {
						Calendar c = Calendar.getInstance();
						myyear = c.get(Calendar.YEAR);
						mymonth = c.get(Calendar.MONTH);
						mydate = c.get(Calendar.DATE);
					} else {
						String date[] = transferDtae.getText().toString().split("-");
						myyear = Integer.parseInt(date[0]);
						mymonth = Integer.parseInt(date[1])-1;
						mydate = Integer.parseInt(date[2]);
					}


					DatePickerDialog ddp = new DatePickerDialog(VehicleDetailsActivity.this, new OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							Log.d("date set", year + "/" +monthOfYear + "/" + dayOfMonth);
							monthOfYear++;
							String month = monthOfYear + "";
							String day = dayOfMonth + "";
							if(monthOfYear < 10)
								month = "0" + monthOfYear;
							if(dayOfMonth < 10)
								day = "0" + dayOfMonth;
							String transferDate = year + "-" + month + "-" + day;
							transferDtae.setText(transferDate);
						}

					}, myyear, mymonth, mydate);

					ddp.show();
				}
			};
			transferDtae.setOnClickListener(myListener2);
			
	}

	private void setVehicleDetails(VehicleDetailsType vehicleDetails,
			Boolean getExistingClaims) {
		regdNo.setText(vehicleDetails.getRegdNo());
		make.setText(vehicleDetails.getMake());
		regDate.setText(vehicleDetails.getDateOfFirstRegistration());
		chassisNo.setText(vehicleDetails.getChassisNo());
		engineNo.setText(vehicleDetails.getEngineNo());
		transferDtae.setText(vehicleDetails.getDateOfTransfer());
		fuelType.setText(vehicleDetails.getTypeOfFuel());
		color.setText(vehicleDetails.getColor());
		
		if(getExistingClaims) {
			regdNo.setEnabled(false);
			make.setEnabled(false);
			regDate.setEnabled(false);
			chassisNo.setEnabled(false);
			engineNo.setEnabled(false);
			transferDtae.setEnabled(false);
			fuelType.setEnabled(false);
			color.setEnabled(false);
		}
		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vehicle_details, menu);
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
