package com.app.fileonthespotclaim;

import java.util.Calendar;

import com.app.entity.DriverDetailsType;
import com.app.entity.LicenseType;
import com.app.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

//Enter Vehicle Details
public class SettingsActivity1 extends ActionBarActivity {
	
	Button bt;
	Button bt1;
	Button bt2;
	Button bt3;
	Button bt4;
	EditText regdNo;
	EditText make;
	String regDate;
	EditText chassisNo;
	EditText engineNo;
	String transferDate;
	EditText fuelType;
	EditText color;
	EditText licenseNo;
	EditText issuingRTO;
	String effectiveFrom;
	String expiryDate;
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
		chassisNo = (EditText) findViewById(R.id.editText17);
		engineNo = (EditText) findViewById(R.id.editText18);
		fuelType = (EditText) findViewById(R.id.editText20);
		color = (EditText) findViewById(R.id.editText21);
		licenseNo = (EditText) findViewById(R.id.editText22);
		issuingRTO = (EditText) findViewById(R.id.editText23);
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
		
		
		bt1 = (Button) findViewById(R.id.firstRegDate);
		Button.OnClickListener myListener1 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				int myyear = c.YEAR ;
				int mymonth = c.MONTH;
				int mydate = c.DATE ;

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Log.d("date set", year + "/" +monthOfYear + "/" + dayOfMonth);
						monthOfYear++;
						regDate = year + "-" + dayOfMonth + "-" + monthOfYear;
						bt3.setText(regDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt1.setOnClickListener(myListener1);
		
		
		bt2 = (Button) findViewById(R.id.transferDate);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				int myyear = c.YEAR ;
				int mymonth = c.MONTH;
				int mydate = c.DATE ;

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Log.d("date set", year + "/" +monthOfYear + "/" + dayOfMonth);
						monthOfYear++;
						transferDate = year + "-" + dayOfMonth + "-" + monthOfYear;
						bt3.setText(transferDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt2.setOnClickListener(myListener2);
		
		
		bt3 = (Button) findViewById(R.id.effectiveFromDate);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				int myyear = c.YEAR ;
				int mymonth = c.MONTH;
				int mydate = c.DATE ;

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Log.d("date set", year + "/" +monthOfYear + "/" + dayOfMonth);
						monthOfYear++;
						effectiveFrom = year + "-" + dayOfMonth + "-" + monthOfYear;
						bt3.setText(effectiveFrom);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt3.setOnClickListener(myListener3);
		
		
		bt4 = (Button) findViewById(R.id.expiryDateDt);
		Button.OnClickListener myListener4 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				int myyear = c.YEAR ;
				int mymonth = c.MONTH;
				int mydate = c.DATE ;

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Log.d("date set", year + "/" +monthOfYear + "/" + dayOfMonth);
						monthOfYear++;
						expiryDate = year + "-" + dayOfMonth + "-" + monthOfYear;
						bt3.setText(expiryDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt4.setOnClickListener(myListener4);
		
		
	}

	protected VehicleDetailsType getVehicleDetails() {
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
		vehicleDetails.setRegdNo(regdNo.getText().toString());
		vehicleDetails.setMake(make.getText().toString());
		vehicleDetails.setDateOfFirstRegistration(regDate);
		vehicleDetails.setChassisNo(chassisNo.getText().toString());
		vehicleDetails.setEngineNo(engineNo.getText().toString());
		vehicleDetails.setDateOfTransfer(transferDate);
		vehicleDetails.setTypeOfFuel(fuelType.getText().toString());
		vehicleDetails.setColor(color.getText().toString());
		return vehicleDetails;
	}
	
	protected DriverDetailsType getDriverDetails() {
		DriverDetailsType driverDetails = new DriverDetailsType();
		LicenseType licenseType = new LicenseType(licenseNo.getText().toString(), issuingRTO.getText().toString(),
				effectiveFrom, expiryDate, 
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
