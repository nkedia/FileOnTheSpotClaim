package com.app.fileonthespotclaim;

import java.io.File;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import com.claims.service.ClaimsService;
import com.claims.service.ClaimsService_Service;
import com.example.fileonthespotclaim.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DocumentsActivity extends ActionBarActivity {
	
	static final int REQUEST_IMAGE_CAPTURE = 1;
	public static int count=0;
	Intent takePictureIntent;
	File photoFile;
	
	private static final String NAMESPACE = "http://localhost:8080/ClaimsService/";
	private static String URL="http://localhost:8080/ClaimsServiceProject/services/ClaimsService?WSDL"; 
	private static final String METHOD_NAME = "fileNewClaim";
	//private static final String SOAP_ACTION =  "http://hello_webservice/hello";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_documents);
		
		 Button bt = (Button) findViewById(R.id.submit_next);
	     Button.OnClickListener myListener = new Button.OnClickListener(){
	        		
	        		public void onClick(View v) {
	        			Toast.makeText(DocumentsActivity.this, "This app works", Toast.LENGTH_LONG).show();
	        			//TODO call claims web service
	        			SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
	        			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        			envelope.setOutputSoapObject(request);
	        			
	        			ClaimsService_Service service1 = new ClaimsService_Service();
	        			ClaimsService port1 = service1.getClaimsServiceSOAP();
	        			javax.xml.ws.Holder<String> claimID = null;
	        			javax.xml.ws.Holder<Boolean> result = null;
	        			port1.fileNewClaim(null, null, null, null, claimID, result);
	        			System.out.println("result" + result.value);
	        			System.out.println("claimID" + claimID.value);
	        			Intent myIntent = new Intent(DocumentsActivity.this, MainActivity.class);
	        			DocumentsActivity.this.startActivity(myIntent);
	        		}
	        };
	     bt.setOnClickListener(myListener);

	     Button bt2 = (Button) findViewById(R.id.carImage);
	     Button.OnClickListener myListener2 = new Button.OnClickListener(){
        		
        		public void onClick(View v) {
        			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        		    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        		        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        		    }
        		}
            };
         bt2.setOnClickListener(myListener2);
       
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Toast.makeText(DocumentsActivity.this, "The camera works", Toast.LENGTH_LONG).show();
	        Log.d("CameraDemo", "Pic saved");
	        ImageView mImageView = new ImageView(getApplicationContext());
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        mImageView.setImageBitmap(imageBitmap);
	        setContentView(mImageView);
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
	
	/*private File createImageFile() throws IOException {
	    // Create an image file name
	    final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/"; 
        File newdir = new File(dir); 
        newdir.mkdirs();
        
        count++;
        String file = dir+count+".jpg";
        File newfile = new File(file);
        newfile.createNewFile();
        return newfile;

	}    */
}
