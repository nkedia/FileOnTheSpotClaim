package com.app.task;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
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
			List<Upload> uploadList = new ArrayList<Upload>();
			for(String path : params) {
				if(path==null || path.isEmpty())
					continue;
				String compressedPath = compressFile(path);
				File file = new File(compressedPath);
				if(file.length() > 0) {
					String[] filePath = compressedPath.split("/");
					String fileName = "claimid" + claimId + "/" + filePath[filePath.length-1];
					Log.d("path", compressedPath);
					Upload upload = transferManager.upload(
							"claim-data",
							fileName,
							file);
					uploadList.add(upload);
				}
			}
			for(Upload upload : uploadList) {
				upload.waitForCompletion();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	private String compressFile(String path) throws FileNotFoundException {
		if(path.contains("3gp")) {
			return path;
		}
		Bitmap bmp = BitmapFactory.decodeFile(path);
		Bitmap scaledBMP = Bitmap.createScaledBitmap(bmp, 800, 600, true);
		File file = new File(path);
		FileOutputStream fos = new FileOutputStream(file);
		scaledBMP.compress(CompressFormat.JPEG, 70, fos);
		return file.getAbsolutePath();
	}


}
