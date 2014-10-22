package com.app.fileonthespotclaim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.service.entity.PolicyHolderDetailsType;
import com.example.fileonthespotclaim.R;

public class GetExistingClaimsActivity extends ActionBarActivity {
	
	// Button bt;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_existing_claims);
		
		/*// Button bt = (Button) findViewById(R.id.phd_next);
	        
	        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
//	        			Intent myIntent = new Intent(GetExistingClaimsActivity.this, VehicleDetailsActivity.class);
	//        			GetExistingClaimsActivity.this.startActivity(myIntent);
	        		}

	        };
	        
	        bt.setOnClickListener(myListener);*/
	        
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_existing_claims, menu);
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
