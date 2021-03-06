package com.app.fileonthespotclaim;

import java.io.File;
import java.io.IOException;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.fileonthespotclaim.R;

public class StatementsActivity extends ActionBarActivity {

	static final int REQUEST_VIDEO_CAPTURE = 1;
	Boolean getExistingClaims = false;
	File driverStatement;
	File passengerStatement;
	File thirdPartyStatement;
	File witnessStatement;
	Intent myIntent;
	Button bt2;
	Button bt3;
	Button bt4;
	Button bt5;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statements);
		
		Drawable d=getResources().getDrawable(R.drawable.background);  
		getActionBar().setBackgroundDrawable(d);
		
		getExistingClaims = getIntent().getBooleanExtra("getExistingClaims", false);
		myIntent = new Intent(StatementsActivity.this, DriverDetailsActivity.class);
		Button bt = (Button) findViewById(R.id.stmt_next);
		Button.OnClickListener myListener = new Button.OnClickListener(){
			public void onClick(View v) {
				myIntent.putExtra("policyHolderDetails", getIntent().getParcelableExtra("policyHolderDetails"));
				myIntent.putExtra("vehicleDetails", getIntent().getParcelableExtra("vehicleDetails"));
				myIntent.putExtra("accidentDetails", getIntent().getParcelableExtra("accidentDetails"));
				myIntent.putExtra("driverDetails", getIntent().getParcelableExtra("driverDetails"));
				if(getExistingClaims)
					myIntent.putExtra("claimId", getIntent().getStringExtra("claimId"));
				myIntent.putExtra("getExistingClaims", getExistingClaims);
				StatementsActivity.this.startActivity(myIntent);
			}
		};
		bt.setOnClickListener(myListener);

		//driver Statement
		bt2 = (Button) findViewById(R.id.driverStatement);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				try {
					driverStatement = File.createTempFile("driverStatement", ".mp4", getApplication().getExternalFilesDir(null));
				}catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(StatementsActivity.this, MainActivity.class);
					StatementsActivity.this.startActivity(newIntent);
				}
				Log.d("file path", driverStatement.getAbsolutePath());
				myIntent.putExtra("driverStatement", driverStatement.getAbsolutePath());
				myIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(driverStatement));
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt2.setOnClickListener(myListener2);

		//passenger Statement
		bt3 = (Button) findViewById(R.id.passengerStatement);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				try {
					passengerStatement = File.createTempFile("passengerStatement", ".mp4", getApplication().getExternalFilesDir(null));
				}catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(StatementsActivity.this, MainActivity.class);
					StatementsActivity.this.startActivity(newIntent);
				}
				Log.d("file path", passengerStatement.getAbsolutePath());
				myIntent.putExtra("passengerStatement", passengerStatement.getAbsolutePath());
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(passengerStatement));
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt3.setOnClickListener(myListener3);

		//thirdParty Statement
		bt4 = (Button) findViewById(R.id.thirdPartyStatement);
		Button.OnClickListener myListener4 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				try {
					thirdPartyStatement = File.createTempFile("thirdPartyStatement", ".mp4", getApplication().getExternalFilesDir(null));
				}catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(StatementsActivity.this, MainActivity.class);
					StatementsActivity.this.startActivity(newIntent);
				}
				Log.d("file path", thirdPartyStatement.getAbsolutePath());
				myIntent.putExtra("thirdPartyStatement", thirdPartyStatement.getAbsolutePath());
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(thirdPartyStatement));
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt4.setOnClickListener(myListener4);

		//witness Statement
		bt5 = (Button) findViewById(R.id.witnessStatement);
		Button.OnClickListener myListener5 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				try {
					witnessStatement = File.createTempFile("witnessStatement", ".mp4", getApplication().getExternalFilesDir(null));
				}catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(StatementsActivity.this, MainActivity.class);
					StatementsActivity.this.startActivity(newIntent);
				}
				Log.d("file path", witnessStatement.getAbsolutePath());
				myIntent.putExtra("witnessStatement", witnessStatement.getAbsolutePath());
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(witnessStatement));
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt5.setOnClickListener(myListener5);


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statements, menu);
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
