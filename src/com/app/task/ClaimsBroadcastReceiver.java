package com.app.task;

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
				//get the different network states
				if (networkInfo.getState() == NetworkInfo.State.CONNECTING 
						|| networkInfo.getState() == NetworkInfo.State.CONNECTED) {
				
					Log.d("wifi", "connected");
					Toast.makeText(context, "hello wifi", Toast.LENGTH_LONG).show();
				}
			}
		}

	}

}
