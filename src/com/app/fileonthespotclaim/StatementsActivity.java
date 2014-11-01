package com.app.fileonthespotclaim;

import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class StatementsActivity extends ActionBarActivity {

	static final int REQUEST_VIDEO_CAPTURE = 1;
	Boolean getExistingClaims = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statements);
		getExistingClaims = getIntent().getBooleanExtra("getExistingClaims", false);
		
		Button bt = (Button) findViewById(R.id.stmt_next);
		Button.OnClickListener myListener = new Button.OnClickListener(){
			public void onClick(View v) {
				Intent myIntent = new Intent(StatementsActivity.this, DriverDetailsActivity.class);
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

		Button bt2 = (Button) findViewById(R.id.driverStatement);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt2.setOnClickListener(myListener2);

		Button bt3 = (Button) findViewById(R.id.passengerStatement);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt3.setOnClickListener(myListener3);

		Button bt4 = (Button) findViewById(R.id.thirdPartyStatement);
		Button.OnClickListener myListener4 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt4.setOnClickListener(myListener4);

		Button bt5 = (Button) findViewById(R.id.witnessStatement);
		Button.OnClickListener myListener5 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
				}
			}
		};
		bt5.setOnClickListener(myListener5);


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
			Uri videoUri = data.getData();
			VideoView mVideoView = new VideoView(getApplicationContext());
			mVideoView.setVideoURI(videoUri);
			MediaController mediaController = new MediaController(this);
			mediaController.setAnchorView(mVideoView);
			mVideoView.setMediaController(mediaController);
			mVideoView.start();
			//setContentView(mVideoView);
			//mVideoView.start();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statements, menu);
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
