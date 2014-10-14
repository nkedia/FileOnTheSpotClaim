package com.app.fileonthespotclaim;

//import android.support.v7.app.ActionBarActivity;
import com.example.fileonthespotclaim.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PolicyHolderDetailsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_policy_holder_details);
		
		 Button bt = (Button) findViewById(R.id.phd_next);
	        
	        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Intent myIntent = new Intent(PolicyHolderDetailsActivity.this, VehicleDetailsActivity.class);
	        			// myIntent.putExtra("key", value);//Optional parameters
	        			PolicyHolderDetailsActivity.this.startActivity(myIntent);
	        		}
	        };
	        
	        bt.setOnClickListener(myListener);
	        
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
