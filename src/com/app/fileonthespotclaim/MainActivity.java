package com.app.fileonthespotclaim;

import com.app.service.entity.AccidentDetailsType;
import com.app.service.entity.DriverDetailsType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.app.service.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	Button nc;
	Button ec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);

		nc = (Button) findViewById(R.id.btn_nc);
		ec = (Button) findViewById(R.id.btn_ec);

		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, PolicyHolderDetailsActivity.class);
				PolicyHolderDetailsType policyHolderDetails = getPolicyHolderDetails();
				VehicleDetailsType vehicleDetails = getVehicleDetails();
				AccidentDetailsType accidentDetails = getAccidentDetails();
				DriverDetailsType driverDetails = getDriverDetails();
				myIntent.putExtra("policyHolderDetails", policyHolderDetails);
				myIntent.putExtra("vehicleDetails", vehicleDetails);
				myIntent.putExtra("accidentDetails", accidentDetails);
				myIntent.putExtra("driverDetails", driverDetails);
				//myIntent.putExtra("fileNewClaim", true);
				MainActivity.this.startActivity(myIntent);
			}
		};

		nc.setOnClickListener(myListener);

		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, GetExistingClaimsActivity.class);
				//myIntent.putExtra("key", value); //Optional parameters
				MainActivity.this.startActivity(myIntent);
			}
		};

		ec.setOnClickListener(myListener2);

	}
	


	protected DriverDetailsType getDriverDetails() {
		DriverDetailsType driverDetails = new DriverDetailsType();
		return driverDetails;
	}



	protected AccidentDetailsType getAccidentDetails() {
		AccidentDetailsType accidentDetails = new AccidentDetailsType();
		return accidentDetails;
	}



	protected VehicleDetailsType getVehicleDetails() {
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
		return vehicleDetails;
	}



	protected PolicyHolderDetailsType getPolicyHolderDetails() {
		PolicyHolderDetailsType policyHolderDetails = new PolicyHolderDetailsType();
		return policyHolderDetails;
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
