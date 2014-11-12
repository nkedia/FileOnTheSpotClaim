package com.app.fileonthespotclaim;

import java.util.Calendar;

import com.app.entity.PeriodOfInsuranceType;
import com.app.entity.PhoneType;
import com.app.entity.PolicyHolderDetailsType;
import com.app.sqlite.ClaimDataSQLHelper;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

//Enter Policy Details
public class SettingsActivity extends ActionBarActivity {

	Button bt;
	EditText policyNo;
	EditText coverNoteNo;
	EditText name;
	EditText address;
	EditText pin;
	EditText office;
	EditText residence;
	EditText mobile;
	EditText email;
	String fromDate = "";
	String toDate = "";
	String dob = "";
	Button bt1;
	Button bt2;
	Button bt3;
	protected ClaimDataSQLHelper claimDataSQLHelper;
	private static final String DATABASE_NAME = "ClaimData.db";
	private static final int DATABASE_VERSION = 1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		policyNo = (EditText) findViewById(R.id.editText4);
		coverNoteNo = (EditText) findViewById(R.id.editText5);
		name = (EditText) findViewById(R.id.editText2);
		address = (EditText) findViewById(R.id.editText1);
		pin = (EditText) findViewById(R.id.editText9);
		office = (EditText) findViewById(R.id.editText10);
		residence = (EditText) findViewById(R.id.editText11);
		mobile = (EditText) findViewById(R.id.editText12);
		email = (EditText) findViewById(R.id.editText13);
		
		//Next button
		Button bt = (Button) findViewById(R.id.next);
		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				PolicyHolderDetailsType policyHolderDetails = getPolicyHolderDetails();
				Intent myIntent = new Intent(SettingsActivity.this, SettingsActivity1.class);
				myIntent.putExtra("policyHolderDetails", policyHolderDetails);
				SettingsActivity.this.startActivity(myIntent);	
			}
		};
		bt.setOnClickListener(myListener);

		//Select from date
		bt1 = (Button) findViewById(R.id.fromDate);
		Button.OnClickListener myListener1 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				int myyear = c.YEAR ;
				int mymonth = c.MONTH;
				int mydate = c.DATE ;

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity.this, new OnDateSetListener() {

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
						fromDate = year + "-" + day + "-" + month;
						bt1.setText(fromDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt1.setOnClickListener(myListener1);
		
		
		//select to date
		bt2 = (Button) findViewById(R.id.toDate);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				int myyear = c.YEAR ;
				int mymonth = c.MONTH;
				int mydate = c.DATE ;

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity.this, new OnDateSetListener() {

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
						toDate = year + "-" + day + "-" + month;
						bt2.setText(toDate);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt2.setOnClickListener(myListener2);
		
		
		//select dob
		bt3 = (Button) findViewById(R.id.dobDate);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				int myyear = c.YEAR ;
				int mymonth = c.MONTH;
				int mydate = c.DATE ;

				DatePickerDialog ddp = new DatePickerDialog(SettingsActivity.this, new OnDateSetListener() {

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
						dob = year + "-" + day + "-" + month;
						bt3.setText(dob);
					}

				}, myyear, mymonth, mydate);

				ddp.show();
			}
		};
		bt3.setOnClickListener(myListener3);
		
		claimDataSQLHelper = new ClaimDataSQLHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
		SQLiteDatabase db = claimDataSQLHelper.getWritableDatabase();
		Cursor cursor = db.query(ClaimDataSQLHelper.TABLE, null, null, null, null, null, null);
		startManagingCursor(cursor);
		if(cursor.moveToLast()) {
			//0 will give base id
			policyNo.setText(cursor.getString(1));
			coverNoteNo.setText(cursor.getString(2));
			bt1.setText(cursor.getString(3));
			bt2.setText(cursor.getString(4));
			name.setText(cursor.getString(5));
			bt3.setText(cursor.getString(6));
			address.setText(cursor.getString(7));
			pin.setText(cursor.getString(8));
			office.setText(cursor.getString(9));
			residence.setText(cursor.getString(10));
			mobile.setText(cursor.getString(11));
			email.setText(cursor.getString(12));
		}
		
	}
	
	protected PolicyHolderDetailsType getPolicyHolderDetails() {
		PolicyHolderDetailsType policyHolderDetails = new PolicyHolderDetailsType();
		policyHolderDetails.setPolicyNo(policyNo.getText().toString());
		policyHolderDetails.setCoverNoteNo(coverNoteNo.getText().toString());
		//PeriodOfInsuranceType periodOfInsurance = new PeriodOfInsuranceType(from.getText().toString(), to.getText().toString());
		PeriodOfInsuranceType periodOfInsurance = new PeriodOfInsuranceType(fromDate, toDate);
		policyHolderDetails.setPeriodOfInsurance(periodOfInsurance);
		policyHolderDetails.setNameOfInsured(name.getText().toString());
		policyHolderDetails.setDobOfInsured(dob);
		policyHolderDetails.setAddressOfInsured(address.getText().toString());
		policyHolderDetails.setPinOfInsured(pin.getText().toString());
		PhoneType phoneType = new PhoneType(office.getText().toString(), residence.getText().toString(), mobile.getText().toString());
		policyHolderDetails.setPhoneType(phoneType);
		policyHolderDetails.setEmailOfInsured(email.getText().toString());

		return policyHolderDetails;
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
