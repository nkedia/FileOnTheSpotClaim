package com.app.fileonthespotclaim;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.service.NewClaimsServiceTask;
import com.app.service.S3UploadTask;
import com.app.service.entity.AccidentDetailsType;
import com.app.service.entity.DriverDetailsType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.app.service.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

@SuppressLint("NewApi")
public class DocumentsActivity extends ActionBarActivity {

	static final int REQUEST_IMAGE_CAPTURE = 1;
	public static int count=0;
	Intent takePictureIntent;
	File photoFile;
	private Button bt;
	private Button bt2;
	private Button bt3;

	private static final String NAMESPACE = "localhost:8080/ClaimsService/";
	private static final String METHOD_NAME = "fileNewClaim";
	private SoapPrimitive result = null;
	private Intent myIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_documents);

		//submit File new Claim
		bt = (Button) findViewById(R.id.submit_next);
		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				if(result == null) {
					Toast.makeText(DocumentsActivity.this, "This app works", Toast.LENGTH_LONG).show();
					myIntent = new Intent(DocumentsActivity.this, MainActivity.class);

					PolicyHolderDetailsType policyHolderDetails = (PolicyHolderDetailsType) getIntent().getParcelableExtra("policyHolderDetails");
					VehicleDetailsType vehicleDetails = (VehicleDetailsType) getIntent().getParcelableExtra("vehicleDetails");
					AccidentDetailsType accidentDetails = (AccidentDetailsType) getIntent().getParcelableExtra("accidentDetails");
					DriverDetailsType driverDetails = (DriverDetailsType) getIntent().getParcelableExtra("driverDetails");

					PropertyInfo policyHolderDetailsProp = new PropertyInfo();
					policyHolderDetailsProp.name = "policyHolderDetails";
					policyHolderDetailsProp.type = PolicyHolderDetailsType.class;
					policyHolderDetailsProp.setValue(policyHolderDetails);

					PropertyInfo vehicleDetailsProp = new PropertyInfo();
					vehicleDetailsProp.name="vehicleDetails";
					vehicleDetailsProp.type= VehicleDetailsType.class;
					vehicleDetailsProp.setValue(vehicleDetails);

					PropertyInfo accidentDetailsProp = new PropertyInfo();
					accidentDetailsProp.name = "accidentDetails";
					accidentDetailsProp.type = AccidentDetailsType.class;
					accidentDetailsProp.setValue(accidentDetails);

					PropertyInfo driverDetailsProp = new PropertyInfo();
					driverDetailsProp.name = "driverDetails";
					driverDetailsProp.type = DriverDetailsType.class;
					driverDetailsProp.setValue(driverDetails);

					SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);

					request.addProperty(policyHolderDetailsProp);
					request.addProperty(vehicleDetailsProp);
					request.addProperty(accidentDetailsProp);
					request.addProperty(driverDetailsProp);
					try {
						// Calling fileNewClaim web service
						result = new NewClaimsServiceTask().execute(request).get();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
					if(photoFile != null) {
						uploadFiles(result);
					} else {
						Toast.makeText(DocumentsActivity.this, "Please click Damaged Car Image, then click on submit", Toast.LENGTH_LONG).show();
					}
				} else {
					if(photoFile != null) {
						uploadFiles(result);
					} else {
						Toast.makeText(DocumentsActivity.this, "Please click Damaged Car Image, then click on submit", Toast.LENGTH_LONG).show();
					}
				}
			}
		};
		bt.setOnClickListener(myListener);

		//Save Damaged Car Image
		bt2 = (Button) findViewById(R.id.clickImage);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					photoFile = File.createTempFile("damagedcar", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(photoFile));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt2.setOnClickListener(myListener2);

		//Cancel File new Insurance
		//Go to Main Activity
		bt3 = (Button) findViewById(R.id.cancel);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			public void onClick(View v) {
				Toast.makeText(DocumentsActivity.this, "File New Claim Cancelled", Toast.LENGTH_LONG).show();
				myIntent = new Intent(DocumentsActivity.this, MainActivity.class);
				DocumentsActivity.this.startActivity(myIntent);
			}
		};
		bt3.setOnClickListener(myListener3);

	}

	protected void uploadFiles(SoapPrimitive result2) {
		String claimId = result.toString();
		Context context = DocumentsActivity.this.getApplicationContext();
		try {
			//saving documents to S3
			//Go to Main Activity
			new S3UploadTask(context, claimId).execute(photoFile.getAbsolutePath());
			DocumentsActivity.this.startActivity(myIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.documents, menu);
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
