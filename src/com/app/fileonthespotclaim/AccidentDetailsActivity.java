package com.app.fileonthespotclaim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.claims.service.AccidentDetailsType;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.*;
import android.text.format.Time;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccidentDetailsActivity extends ActionBarActivity {

	Button bt;
	EditText date;
	EditText time;
	EditText speed;
	EditText place;
	EditText peopleNo;
	EditText policeStationName;
	EditText firNo;
	EditText mileage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accident_details);
		
		 bt = (Button) findViewById(R.id.ad_next);
		 date = (EditText) findViewById(R.id.editText1);
	     time = (EditText) findViewById(R.id.editText2);
	     speed = (EditText) findViewById(R.id.editText3);
	     place = (EditText) findViewById(R.id.editText4);
	     peopleNo = (EditText) findViewById(R.id.editText5);
	     policeStationName = (EditText) findViewById(R.id.editText7);
	     firNo = (EditText) findViewById(R.id.editText8);
	     mileage = (EditText) findViewById(R.id.editText9);
	     
	        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Intent myIntent = new Intent(AccidentDetailsActivity.this, StatementsActivity.class);
	        			AccidentDetailsType accidentDetails = getAccidentDetails();
	        			//myIntent.putExtra("key", value); //Optional parameters
	        			AccidentDetailsActivity.this.startActivity(myIntent);
	        		}
	        };
	        
	        bt.setOnClickListener(myListener);
	}

	protected AccidentDetailsType getAccidentDetails() {
		AccidentDetailsType accidentDetails = new AccidentDetailsType();
		try{
			SimpleDateFormat sf = new SimpleDateFormat("mm/dd/yyyy");

			//TODO add current date in screen
			Date fromDate = sf.parse(date.getText().toString());
			GregorianCalendar cal1 = new GregorianCalendar();
			cal1.setTime(fromDate);
			XMLGregorianCalendar gc1 =
			     DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
			
			//TODO add current  time
			Time time = new Time();
			GregorianCalendar cal2 = new GregorianCalendar();
			cal2.setTime(fromDate);
			XMLGregorianCalendar gc2 =
			     DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
			
			accidentDetails.setDateOfAccident(gc1);
			accidentDetails.setTime(gc2);
			accidentDetails.setSpeed(speed.getText().toString());
			accidentDetails.setPlace(place.getText().toString());
			accidentDetails.setNoOfPeopleTravelling(peopleNo.getText().toString());
			accidentDetails.setPoliceStationName(policeStationName.getText().toString());
			accidentDetails.setFIRNo(firNo.getText().toString());
			accidentDetails.setMileage(mileage.getText().toString());
			
		} catch (ParseException | DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accidentDetails;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accident_details, menu);
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
