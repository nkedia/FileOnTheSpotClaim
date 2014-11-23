package com.app.fileonthespotclaim;

import java.util.Calendar;
import com.app.entity.DriverDetailsType;
import com.app.entity.LicenseType;
import com.app.entity.VehicleDetailsType;
import com.app.sqlite.ClaimDataSQLHelper;
import com.example.fileonthespotclaim.R;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
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
	protected ClaimDataSQLHelper claimDataSQLHelper;
	private static final String DATABASE_NAME = "ClaimData.db";
	private static final int DATABASE_VERSION = 1;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings1);

		Drawable d=getResources().getDrawable(R.drawable.background);  
		getActionBar().setBackgroundDrawable(d);
		Drawable icon = getResources().getDrawable(R.drawable.settings_icon);
		getActionBar().setIcon(icon);
		

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

		//next button
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

		//get Date of First Registration
		bt1 = (Button) findViewById(R.id.firstRegDate);
		Button.OnClickListener myListener1 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				int myyear;
				int mymonth;
				int mydate;
				if(bt1.getText().toString().isEmpty()) {
					Calendar c = Calendar.getInstance();
					myyear = c.get(Calendar.YEAR);
					mymonth = c.get(Calendar.MONTH);
					mydate = c.get(Calendar.DATE);
				} else {
					String date[] = bt1.getText().toString().split("-");
					myyear = Integer.parseInt(date[0]);
					mymonth = Integer.parseInt(date[1])-1;
					mydate = Integer.parseInt(date[2]);
				}


				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

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
						regDate = year + "-" + month + "-" + day;
						bt1.setText(regDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt1.setOnClickListener(myListener1);

		// get Date of Transfer
		bt2 = (Button) findViewById(R.id.transferDate);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				int myyear;
				int mymonth;
				int mydate;
				if(bt2.getText().toString().isEmpty()) {
					Calendar c = Calendar.getInstance();
					myyear = c.get(Calendar.YEAR);
					mymonth = c.get(Calendar.MONTH);
					mydate = c.get(Calendar.DATE);
				} else {
					String date[] = bt2.getText().toString().split("-");
					myyear = Integer.parseInt(date[0]);
					mymonth = Integer.parseInt(date[1])-1;
					mydate = Integer.parseInt(date[2]);
				}


				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

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
						transferDate = year + "-" + month + "-" + day;
						bt2.setText(transferDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt2.setOnClickListener(myListener2);

		//get Effective from date
		bt3 = (Button) findViewById(R.id.effectiveFromDate);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				int myyear;
				int mymonth;
				int mydate;
				if(bt3.getText().toString().isEmpty()) {
					Calendar c = Calendar.getInstance();
					myyear = c.get(Calendar.YEAR);
					mymonth = c.get(Calendar.MONTH);
					mydate = c.get(Calendar.DATE);
				} else {
					String date[] = bt3.getText().toString().split("-");
					myyear = Integer.parseInt(date[0]);
					mymonth = Integer.parseInt(date[1])-1;
					mydate = Integer.parseInt(date[2]);
				}

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

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
						effectiveFrom = year + "-" + month + "-" + day;
						bt3.setText(effectiveFrom);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt3.setOnClickListener(myListener3);


		//get Expiry date
		bt4 = (Button) findViewById(R.id.expiryDateDt);
		Button.OnClickListener myListener4 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				int myyear;
				int mymonth;
				int mydate;
				if(bt4.getText().toString().isEmpty()) {
					Calendar c = Calendar.getInstance();
					myyear = c.get(Calendar.YEAR);
					mymonth = c.get(Calendar.MONTH);
					mydate = c.get(Calendar.DATE);
				} else {
					String date[] = bt4.getText().toString().split("-");
					myyear = Integer.parseInt(date[0]);
					mymonth = Integer.parseInt(date[1])-1;
					mydate = Integer.parseInt(date[2]);
				}

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity1.this, new OnDateSetListener() {

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
						expiryDate = year + "-" + month + "-" + day;
						bt4.setText(expiryDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt4.setOnClickListener(myListener4);

		claimDataSQLHelper = new ClaimDataSQLHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
		SQLiteDatabase db = claimDataSQLHelper.getWritableDatabase();
		Cursor cursor = db.query(ClaimDataSQLHelper.TABLE, null, null, null, null, null, null);
		startManagingCursor(cursor);
		if(cursor.moveToLast()) {
			//0 will give base id
			regdNo.setText(cursor.getString(13));
			make.setText(cursor.getString(14));
			bt1.setText(cursor.getString(15));
			chassisNo.setText(cursor.getString(16));
			engineNo.setText(cursor.getString(17));
			bt2.setText(cursor.getString(18));
			fuelType.setText(cursor.getString(19));
			color.setText(cursor.getString(20));
			licenseNo.setText(cursor.getString(21));
			issuingRTO.setText(cursor.getString(22));
			bt3.setText(cursor.getString(23));
			bt4.setText(cursor.getString(24));
			int position = 0;
			if(cursor.getString(25).equals("MCycle"))
				position = 0;
			else if(cursor.getString(25).equals("LMV"))
				position = 1;
			else if(cursor.getString(25).equals("HGV"))
				position = 2;
			else if(cursor.getString(25).equals("Transport"))
				position = 3;
			else
				position = 4;
			vehicleClassSpinner.setSelection(position);

			if(cursor.getString(26).equals("Permanent"))
				position = 0;
			else
				position = 1;
			vehicleTypeSpinner.setSelection(position);
		}

	}

	protected VehicleDetailsType getVehicleDetails() {
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
		vehicleDetails.setRegdNo(regdNo.getText().toString());
		vehicleDetails.setMake(make.getText().toString());
		vehicleDetails.setDateOfFirstRegistration(bt1.getText().toString());
		vehicleDetails.setChassisNo(chassisNo.getText().toString());
		vehicleDetails.setEngineNo(engineNo.getText().toString());
		vehicleDetails.setDateOfTransfer(bt2.getText().toString());
		vehicleDetails.setTypeOfFuel(fuelType.getText().toString());
		vehicleDetails.setColor(color.getText().toString());
		return vehicleDetails;
	}

	protected DriverDetailsType getDriverDetails() {
		DriverDetailsType driverDetails = new DriverDetailsType();
		LicenseType licenseType = new LicenseType(licenseNo.getText().toString(), issuingRTO.getText().toString(),
				bt3.getText().toString(), bt4.getText().toString(), 
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
