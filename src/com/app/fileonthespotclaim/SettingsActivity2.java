package com.app.fileonthespotclaim;

import java.io.File;
import java.io.IOException;

import com.app.entity.DriverDetailsType;
import com.app.entity.PolicyHolderDetailsType;
import com.app.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

// Upload documents
public class SettingsActivity2 extends ActionBarActivity {
	
	Button bt1;
	Button bt2;
	Button bt3;
	Button bt4;
	File insuranceCopy;
	File rcCopy;
	File licenseCopy;
	static final int REQUEST_IMAGE_CAPTURE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings2);
		
		// save Insurance copy
		bt1 = (Button) findViewById(R.id.clickImageIC);
		Button.OnClickListener myListener1 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					insuranceCopy = File.createTempFile("insurance", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(insuranceCopy));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt1.setOnClickListener(myListener1);
		
		// save RC copy
		bt2 = (Button) findViewById(R.id.clickImageRC);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					rcCopy = File.createTempFile("rcImage", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(rcCopy));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt2.setOnClickListener(myListener2);
		
		// save license copy
		bt3 = (Button) findViewById(R.id.clickImageLC);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					licenseCopy = File.createTempFile("license", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(licenseCopy));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt3.setOnClickListener(myListener3);
		
		bt4 = (Button) findViewById(R.id.submit);
		Button.OnClickListener myListener4 = new Button.OnClickListener(){

			public void onClick(View v) {
				PolicyHolderDetailsType policyHolderDetails = getIntent().getParcelableExtra("policyHolderDetails");
				VehicleDetailsType vehicleDetails = getIntent().getParcelableExtra("vehicleDetails");
				DriverDetailsType driverDetails = getIntent().getParcelableExtra("driverDetails");
				//TODO add above details to SQLite
				Log.d("File Path", insuranceCopy.getAbsolutePath());
				Log.d("File Path", rcCopy.getAbsolutePath());
				Log.d("File Path", licenseCopy.getAbsolutePath());
				Log.d("File Path", Environment.getExternalStorageDirectory().getAbsolutePath());
				
				Intent myIntent = new Intent(SettingsActivity2.this, MainActivity.class);
				SettingsActivity2.this.startActivity(myIntent);	
			}
		};
		bt4.setOnClickListener(myListener4);
		
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
