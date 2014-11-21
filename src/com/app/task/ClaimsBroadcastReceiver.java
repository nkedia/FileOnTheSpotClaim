package com.app.task;

import java.io.File;
import java.util.concurrent.ExecutionException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class ClaimsBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		NetworkInfo networkInfo = intent
				.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		if (networkInfo != null) {
			Log.d("Type : State : ",  networkInfo.getType() + " : " + networkInfo.getState());


			if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
					Log.d("wifi", "connected");
					Toast.makeText(context, "hello wifi", Toast.LENGTH_LONG).show();
					File externalDir = context.getExternalFilesDir(null);
					File[] fileList = externalDir.listFiles();
					String uploadFiles[] = new String[4];
					for(File claimFolder : fileList) {
						if(claimFolder.getName().contains("claimId_")) {
							int i = 0;
							String claimId = claimFolder.getName().split("_")[1];
							File[] files = claimFolder.listFiles();
							for(File file : files) {
								uploadFiles[i] = file.getAbsolutePath();
								i++;
							}
							//call s3 upload task
							try {
								new S3UploadTask(context, claimId).execute(uploadFiles).get();
								for (File file : files) {
									file.delete();
								}
								claimFolder.delete();
							} catch (InterruptedException | ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

	}

}
