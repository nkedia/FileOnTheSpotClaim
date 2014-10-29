package com.app.service;

import java.io.File;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class S3UploadTask extends AsyncTask<String, Void, Boolean>{

	Context context = null;
	String claimId;
	
	public S3UploadTask() {
		
	}
	
	public S3UploadTask(Context context, String claimId) {
		this.context = context;
		this.claimId = claimId;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		Log.d("Inside S3UploadTask", "");
		CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
			    context, // get the context for the current activity
			    "734355927470",
			    "us-east-1:3733c0c4-a82d-4aab-9f15-faec39fa6370",
			    "arn:aws:iam::734355927470:role/Cognito_ClaimDataUnauth_DefaultRole",
			    "arn:aws:iam::734355927470:role/Cognito_ClaimDataAuth_DefaultRole",
			    Regions.US_EAST_1
			);
		 cognitoProvider.refresh();
		 Log.d("Cognito Provider", cognitoProvider.toString());
		 AmazonS3Client mClient = new AmazonS3Client(cognitoProvider);
		 Region region = Region.getRegion(Regions.US_EAST_1);
		 mClient.setRegion(region);
		 TransferManager transferManager = new TransferManager(cognitoProvider);
		 Log.d("TransferManager", transferManager.toString());
		 try {
			 for(String path : params) {
				 File file = new File(path);
				 String[] filePath = path.split("/");
				 String fileName = "claimid" + claimId + "/" + filePath[filePath.length-1];
				 Log.d("path", path);
				 Upload upload = transferManager.upload(
					        "claim-data",
					        fileName,
					        file);
				 upload.waitForCompletion();
			 }
		} catch(Exception e) {
			e.printStackTrace();
		}
			 
		return true;
	}

}
