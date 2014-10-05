package com.example.fileonthespotclaim;

import android.support.v7.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AccidentDetailsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accident_details);
		
		 Button bt = (Button) findViewById(R.id.ad_next);
	        
	        Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Intent myIntent = new Intent(AccidentDetailsActivity.this, StatementsActivity.class);
	        			//myIntent.putExtra("key", value); //Optional parameters
	        			AccidentDetailsActivity.this.startActivity(myIntent);
	        		}
	        };
	        
	        bt.setOnClickListener(myListener);
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
