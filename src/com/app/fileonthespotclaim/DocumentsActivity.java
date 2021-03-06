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
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entity.AccidentDetailsType;
import com.app.entity.DriverDetailsType;
import com.app.entity.PolicyHolderDetailsType;
import com.app.entity.VehicleDetailsType;
import com.app.task.NewClaimsServiceTask;
import com.app.task.S3UploadTask;
import com.app.task.UpdateExistingClaimsServiceTask;
import com.example.fileonthespotclaim.R;

@SuppressLint("NewApi")
public class DocumentsActivity extends ActionBarActivity {

	static final int REQUEST_IMAGE_CAPTURE = 1;
	File photoFile;
	File photoFileThirdParty;
	File billPhoto;
	File firPhoto;
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	private Button bt5;
	private Button bt6;
	private TextView ic;
	private TextView rc;
	private TextView lc;
	private TextView dc;
	private TextView dctp;
	private TextView bills;
	private TextView fir;
	private TextView insuranceText;
	private TextView rcText;
	private TextView licenseText;
	//	private CheckBox towV;

	private static final String NAMESPACE = "localhost:8080/ClaimsService/";
	private static final String METHOD_NAME1 = "fileNewClaim";
	private static final String METHOD_NAME2 = "updateExistingClaims";
	private SoapPrimitive result = null;
	private Intent myIntent;
	public Boolean getExistingClaims = false;
	public String claimId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_documents);
		
		Drawable d=getResources().getDrawable(R.drawable.background);  
		getActionBar().setBackgroundDrawable(d);
		
		getExistingClaims = getIntent().getBooleanExtra("getExistingClaims", false);
		claimId = getIntent().getStringExtra("claimId");

		insuranceText = (TextView) findViewById(R.id.insuranceText);
		rcText = (TextView) findViewById(R.id.rcText);
		licenseText = (TextView) findViewById(R.id.licenseText);
		insuranceText.setText("insurance.jpg");
		rcText.setText("rcImage.jpg");
		licenseText.setText("license.jpg");

		//submit File new Claim
		bt1 = (Button) findViewById(R.id.submit_next);
		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				if(getExistingClaims) {
					myIntent = new Intent(DocumentsActivity.this, MainActivity.class);
					SoapObject request = getRequestObject();
					//Calling updateExistingClaim web service
					try {
						SoapPrimitive result = (SoapPrimitive) new UpdateExistingClaimsServiceTask().execute(request).get();
						Log.d("Update result", result.toString());
						//Upload garage bills to s3
						uploadFiles(claimId);
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
						Intent newIntent = new Intent(DocumentsActivity.this, MainActivity.class);
						DocumentsActivity.this.startActivity(newIntent);
					}
					Toast.makeText(DocumentsActivity.this, "Update Existing Claim Successful", Toast.LENGTH_LONG).show();
					DocumentsActivity.this.startActivity(myIntent);
				} else {
					if(photoFile == null) {
						Toast.makeText(DocumentsActivity.this, "Please click Damaged Car Image; then click on submit", Toast.LENGTH_LONG).show();
					} else {
						myIntent = new Intent(DocumentsActivity.this, MainActivity.class);
						SoapObject request = getRequestObject();
						try {
							// Calling fileNewClaim web service
							result = new NewClaimsServiceTask().execute(request).get();
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
							Intent newIntent = new Intent(DocumentsActivity.this, MainActivity.class);
							DocumentsActivity.this.startActivity(newIntent);
						}
						if(result != null) {
							//upload documents to S3
							uploadFiles(result.toString());
						} else {
							Toast.makeText(DocumentsActivity.this, "Unable to connect to server; Please check your internet connection", Toast.LENGTH_LONG).show();
						}
					}
				}
			}
		};
		bt1.setOnClickListener(myListener);

		//Save Damaged Car Image
		bt2 = (Button) findViewById(R.id.clickImageCar);
		Button.OnClickListener myListener2 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					photoFile = File.createTempFile("damagedcar", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(DocumentsActivity.this, MainActivity.class);
					DocumentsActivity.this.startActivity(newIntent);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(photoFile));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt2.setOnClickListener(myListener2);

		//Save damaged car Image Third Party
		bt6 = (Button)findViewById(R.id.clickImageCarThirdParty);
		Button.OnClickListener myListener6 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					photoFileThirdParty = File.createTempFile("damagedcarThirdParty", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(DocumentsActivity.this, MainActivity.class);
					DocumentsActivity.this.startActivity(newIntent);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(photoFileThirdParty));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt6.setOnClickListener(myListener6);


		//Save FIR copy
		bt4 = (Button) findViewById(R.id.clickImageFIR);
		Button.OnClickListener myListener4 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					billPhoto = File.createTempFile("garagebill", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(DocumentsActivity.this, MainActivity.class);
					DocumentsActivity.this.startActivity(newIntent);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(billPhoto));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt4.setOnClickListener(myListener4);


		//Save Garage Bill copy
		bt5 = (Button) findViewById(R.id.clickImageGB);
		Button.OnClickListener myListener5 = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				try {
					firPhoto = File.createTempFile("fir", ".jpg", getApplication().getExternalFilesDir(null));
				} catch (IOException e) {
					e.printStackTrace();
					Intent newIntent = new Intent(DocumentsActivity.this, MainActivity.class);
					DocumentsActivity.this.startActivity(newIntent);
				}
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(firPhoto));
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
			}
		};
		bt5.setOnClickListener(myListener5);

		if(!getExistingClaims) {
			bt4.setEnabled(false);
			bt5.setEnabled(false);
			bills = (TextView) findViewById(R.id.bills);
			bills.setEnabled(false);
			fir = (TextView) findViewById(R.id.fir);
			fir.setEnabled(false);
		} else {
			ic = (TextView) findViewById(R.id.insuranceCopy);
			rc = (TextView) findViewById(R.id.rc);
			lc = (TextView) findViewById(R.id.license);
			dc = (TextView) findViewById(R.id.carImage);
			dctp = (TextView) findViewById(R.id.carImageThirdParty);
			//towV = (CheckBox) findViewById(R.id.towV);
			ic.setEnabled(false);
			rc.setEnabled(false);
			lc.setEnabled(false);
			dc.setEnabled(false);
			dctp.setEnabled(false);
			bt2.setEnabled(false);
			bt6.setEnabled(false);
			insuranceText.setEnabled(false);
			rcText.setEnabled(false);
			licenseText.setEnabled(false);
			//towV.setEnabled(false);
		}

		//Cancel File new Insurance or Update Existing Claim
		//Go to Main Activity
		bt3 = (Button) findViewById(R.id.cancel);
		Button.OnClickListener myListener3 = new Button.OnClickListener(){

			public void onClick(View v) {
				if(getExistingClaims)
					Toast.makeText(DocumentsActivity.this, "Update Existing Claim Cancelled", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(DocumentsActivity.this, "File New Claim Cancelled", Toast.LENGTH_LONG).show();
				myIntent = new Intent(DocumentsActivity.this, MainActivity.class);
				DocumentsActivity.this.startActivity(myIntent);
			}
		};
		bt3.setOnClickListener(myListener3);

	}

	protected SoapObject getRequestObject() {
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
		SoapObject request = null;
		if(getExistingClaims) {
			request = new SoapObject(NAMESPACE, METHOD_NAME2);
			request.addProperty("claimId", claimId);
			request.addProperty("policeStationName", accidentDetails.getPoliceStationName());
			request.addProperty("FIRNo", accidentDetails.getFIRNo());
		}
		else {
			request = new SoapObject(NAMESPACE, METHOD_NAME1);
			request.addProperty(policyHolderDetailsProp);
			request.addProperty(vehicleDetailsProp);
			request.addProperty(accidentDetailsProp);
			request.addProperty(driverDetailsProp);
		}

		return request;
	}

	protected void uploadFiles(String claimId) {
		Context context = DocumentsActivity.this.getApplicationContext();
		boolean isWifiNetworkAvailable = getNetworkInfo();
		if(isWifiNetworkAvailable) {
			try {
				if(getExistingClaims) {
					//save statements to S3
					//save documents to S3
					new S3UploadTask(context, claimId).execute(billPhoto == null ? "" : billPhoto.getAbsolutePath(), 
							firPhoto == null ? "" : firPhoto.getAbsolutePath(),
									getIntent().getStringExtra("driverStatement"), getIntent().getStringExtra("passengerStatement"),
									getIntent().getStringExtra("thirdPartyStatement"), getIntent().getStringExtra("witnessStatement"));
				}
				else {
					//save insurance, rc, license copies
					File insurance = new File(getApplication().getExternalFilesDir(null), "insurance.jpg");
					File rc = new File(getApplication().getExternalFilesDir(null), "rcImage.jpg");
					File license = new File(getApplication().getExternalFilesDir(null), "license.jpg");

					//save statements to S3
					//save documents to S3
					//Go to Main Activity
					new S3UploadTask(context, claimId).execute(photoFile.getAbsolutePath(), photoFileThirdParty == null ? "" : photoFileThirdParty.getAbsolutePath(),
							insurance.getAbsolutePath(), rc.getAbsolutePath(), license.getAbsolutePath(), 
							getIntent().getStringExtra("driverStatement"), getIntent().getStringExtra("passengerStatement"),
							getIntent().getStringExtra("thirdPartyStatement"), getIntent().getStringExtra("witnessStatement"));

					Toast.makeText(DocumentsActivity.this, "File New Claim Successful", Toast.LENGTH_LONG).show();
					DocumentsActivity.this.startActivity(myIntent);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Intent newIntent = new Intent(DocumentsActivity.this, MainActivity.class);
				DocumentsActivity.this.startActivity(newIntent);
			}
		} else {
			Toast.makeText(context, "Wifi is not connected; files will be uploaded when Wifi is connected", Toast.LENGTH_LONG).show();
			if(getExistingClaims) {
				//rename statements
				//getIntent().getStringExtra("driverStatement"), getIntent().getStringExtra("passengerStatement"),
				//getIntent().getStringExtra("thirdPartyStatement"), getIntent().getStringExtra("witnessStatement")
				new File(getApplication().getExternalFilesDir(null).getAbsolutePath(), "claimId_" + claimId).mkdir();
				if(getIntent().getStringExtra("driverStatement") != null) {
					File driverStatement = new File(getIntent().getStringExtra("driverStatement"));
					File driverStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "driverStatement.mp4");
					boolean renamed = driverStatement.renameTo(driverStatementRename);
					Log.d("", "File renamed " + renamed);
				}

				if(getIntent().getStringExtra("passengerStatement") != null) {
					File passengerStatement = new File(getIntent().getStringExtra("passengerStatement"));
					File passengerStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "passengerStatement.mp4");
					passengerStatement.renameTo(passengerStatementRename);
				}

				if(getIntent().getStringExtra("thirdPartyStatement") != null) {
					File thirdPartyStatement = new File(getIntent().getStringExtra("thirdPartyStatement"));
					File thirdPartyStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "thirdPartyStatement.mp4");
					thirdPartyStatement.renameTo(thirdPartyStatementRename);
				}

				if(getIntent().getStringExtra("witnessStatement") != null) {
					File witnessStatement = new File(getIntent().getStringExtra("witnessStatement"));
					File witnessStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "witnessStatement.mp4");
					witnessStatement.renameTo(witnessStatementRename);
				}

				//Upload documents to S3
				new S3UploadTask(context, claimId).execute(billPhoto == null ? "" : billPhoto.getAbsolutePath(), 
						firPhoto == null ? "" : firPhoto.getAbsolutePath());
			}
			else {
				//save insurance, rc, license copies
				File insurance = new File(getApplication().getExternalFilesDir(null), "insurance.jpg");
				File rc = new File(getApplication().getExternalFilesDir(null), "rcImage.jpg");
				File license = new File(getApplication().getExternalFilesDir(null), "license.jpg");

				//rename statements to S3 to upload later when wifi is available
				new File(getApplication().getExternalFilesDir(null).getAbsolutePath(), "claimId_" + claimId).mkdir();
				if(getIntent().getStringExtra("driverStatement") != null) {
					File driverStatement = new File(getIntent().getStringExtra("driverStatement"));
					File driverStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "driverStatement.mp4");
					boolean renamed = driverStatement.renameTo(driverStatementRename);
					Log.d("", "File renamed " + renamed);
				}

				if(getIntent().getStringExtra("passengerStatement") != null) {
					File passengerStatement = new File(getIntent().getStringExtra("passengerStatement"));
					File passengerStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "passengerStatement.mp4");
					passengerStatement.renameTo(passengerStatementRename);
				}

				if(getIntent().getStringExtra("thirdPartyStatement") != null) {
					File thirdPartyStatement = new File(getIntent().getStringExtra("thirdPartyStatement"));
					File thirdPartyStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "thirdPartyStatement.mp4");
					thirdPartyStatement.renameTo(thirdPartyStatementRename);
				}

				if(getIntent().getStringExtra("witnessStatement") != null) {
					File witnessStatement = new File(getIntent().getStringExtra("witnessStatement"));
					File witnessStatementRename = new File(getApplication().getExternalFilesDir(null).getAbsolutePath() + "/claimId_" + claimId, "witnessStatement.mp4");
					witnessStatement.renameTo(witnessStatementRename);
				}

				//save documents to S3
				new S3UploadTask(context, claimId).execute(photoFile.getAbsolutePath(), photoFileThirdParty == null ? "" : photoFileThirdParty.getAbsolutePath(),
						insurance.getAbsolutePath(), rc.getAbsolutePath(), license.getAbsolutePath());
				//Go to Main Activity
				DocumentsActivity.this.startActivity(myIntent);
			}
		}

	}

	private boolean getNetworkInfo() {
		Context context = DocumentsActivity.this.getApplicationContext();
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (mWifi.isConnected()) {
			Log.d("wifi", "connected");
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.documents, menu);
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
