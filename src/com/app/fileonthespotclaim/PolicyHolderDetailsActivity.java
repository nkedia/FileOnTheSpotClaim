package com.app.fileonthespotclaim;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.service.entity.PeriodOfInsuranceType;
import com.app.service.entity.PhoneType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.example.fileonthespotclaim.R;

public class PolicyHolderDetailsActivity extends ActionBarActivity {
	
	 Button bt;
	 EditText policyNo;
	 EditText coverNoteNo;
	 EditText from;
	 EditText to;
	 EditText name;
	 EditText dob;
	 EditText address;
	 EditText pin;
	 EditText office;
	 EditText residence;
	 EditText mobile;
	 EditText email;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_policy_holder_details);
		//getWindow().getDecorView().setBackgroundColor(Color.CYAN);
		
		 Button bt = (Button) findViewById(R.id.phd_next);
		 policyNo = (EditText) findViewById(R.id.editText4);
		 coverNoteNo = (EditText) findViewById(R.id.editText5);
		 from = (EditText) findViewById(R.id.editText6);
		 to = (EditText) findViewById(R.id.editText7);
		 name = (EditText) findViewById(R.id.editText2);
		 dob = (EditText) findViewById(R.id.editText8);
		 address = (EditText) findViewById(R.id.editText1);
		 pin = (EditText) findViewById(R.id.editText9);
		 office = (EditText) findViewById(R.id.editText10);
		 residence = (EditText) findViewById(R.id.editText11);
		 mobile = (EditText) findViewById(R.id.editText12);
		 email = (EditText) findViewById(R.id.editText13);
	        
	        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Intent myIntent = new Intent(PolicyHolderDetailsActivity.this, VehicleDetailsActivity.class);
	        			PolicyHolderDetailsType policyHolderDetails = getPolicyHolderDetails(); 
	        			myIntent.putExtra("policyHolderDetails", policyHolderDetails);
	        			PolicyHolderDetailsActivity.this.startActivity(myIntent);
	        		}

	        };
	        
	        bt.setOnClickListener(myListener);
	        
	}

	protected PolicyHolderDetailsType getPolicyHolderDetails() {
		PolicyHolderDetailsType policyHolderDetails = new PolicyHolderDetailsType();
		policyHolderDetails.setPolicyNo(policyNo.getText().toString());
		policyHolderDetails.setCoverNoteNo(coverNoteNo.getText().toString());
		PeriodOfInsuranceType periodOfInsurance = new PeriodOfInsuranceType(from.getText().toString(), to.getText().toString());
		policyHolderDetails.setPeriodOfInsurance(periodOfInsurance);
		policyHolderDetails.setNameOfInsured(name.getText().toString());
		policyHolderDetails.setDobOfInsured(dob.getText().toString());
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
		getMenuInflater().inflate(R.menu.policy_holder_details, menu);
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
