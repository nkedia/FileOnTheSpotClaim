package com.app.fileonthespotclaim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.entity.PeriodOfInsuranceType;
import com.app.entity.PhoneType;
import com.app.entity.PolicyHolderDetailsType;
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
	Boolean getExistingClaims = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_policy_holder_details);

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
		
		PolicyHolderDetailsType policyHolderDetails = (PolicyHolderDetailsType) getIntent().getParcelableExtra("policyHolderDetails");
		getExistingClaims = getIntent().getBooleanExtra("getExistingClaims", false);
		setPolicyHolderDetails(policyHolderDetails, getExistingClaims);

		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				PolicyHolderDetailsType policyHolderDetails = getPolicyHolderDetails();
				Intent myIntent = new Intent(PolicyHolderDetailsActivity.this, VehicleDetailsActivity.class);
				myIntent.putExtra("policyHolderDetails", policyHolderDetails);
				myIntent.putExtra("vehicleDetails", getIntent().getParcelableExtra("vehicleDetails"));
				myIntent.putExtra("accidentDetails", getIntent().getParcelableExtra("accidentDetails"));
				myIntent.putExtra("driverDetails", getIntent().getParcelableExtra("driverDetails"));
				if(getExistingClaims)
					myIntent.putExtra("claimId", getIntent().getStringExtra("claimId"));
				myIntent.putExtra("getExistingClaims", getExistingClaims);
				PolicyHolderDetailsActivity.this.startActivity(myIntent);
			}

		};

		bt.setOnClickListener(myListener);

	}

	private void setPolicyHolderDetails(PolicyHolderDetailsType policyHolderDetails, Boolean getExistingClaims) {
		policyNo.setText(policyHolderDetails.getPolicyNo());
		coverNoteNo.setText(policyHolderDetails.getCoverNoteNo());
		from.setText(policyHolderDetails.getPeriodOfInsurance().getFrom());
		to.setText(policyHolderDetails.getPeriodOfInsurance().getTo());
		name.setText(policyHolderDetails.getNameOfInsured());
		dob.setText(policyHolderDetails.getDobOfInsured());
		address.setText(policyHolderDetails.getAddressOfInsured());
		pin.setText(policyHolderDetails.getPinOfInsured());
		office.setText(policyHolderDetails.getPhoneType().getOffice());
		residence.setText(policyHolderDetails.getPhoneType().getResidence());
		mobile.setText(policyHolderDetails.getPhoneType().getMobile());
		email.setText(policyHolderDetails.getEmailOfInsured());
		
		if (getExistingClaims) {
			policyNo.setEnabled(false);
			coverNoteNo.setEnabled(false);
			from.setEnabled(false);
			to.setEnabled(false);
			name.setEnabled(false);
			dob.setEnabled(false);
			address.setEnabled(false);
			pin.setEnabled(false);
			office.setEnabled(false);
			residence.setEnabled(false);
			mobile.setEnabled(false);
			email.setEnabled(false);

		}
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
