package com.app.task;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class FetchPoliceStationTask extends AsyncTask<String, Void, List<String>>{
	
	@Override
	protected List<String> doInBackground(String... plookupString) {
		String lookupString = plookupString[0];
		final String lookupStringUriencoded = Uri.encode(lookupString);
		HttpGet httpGet = new HttpGet(
				"https://maps.googleapis.com/maps/api/place/nearbysearch/json?types=police&location="
				+ lookupStringUriencoded 
				+ "&rankby=distance&sensor=true&key=AIzaSyAXd_dfqKZSIpqJrVGYsq1R6pbzZLtLXXU");
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();
		String policeStationName="";
		String psLatLong="";
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject();
		try {
			Log.d("MAPSAPI", stringBuilder.toString());
			jsonObject = new JSONObject(stringBuilder.toString());
			if (jsonObject.getString("status").equals("OK")) {
				jsonObject = jsonObject.getJSONArray("results")
						.getJSONObject(0);
				policeStationName = jsonObject.getString("name");
				jsonObject = jsonObject.getJSONObject("geometry");
				jsonObject = jsonObject.getJSONObject("location");
				psLatLong = jsonObject.getString("lat") + "," + jsonObject.getString("lng");
				Log.d("MAPSAPI", "Police Station lat long " + psLatLong);
				Log.d("MAPSAPI", "Police Station Name " + policeStationName);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<String> list = new ArrayList<>();
		list.add(0, policeStationName);
		list.add(1, psLatLong);
		return list;
	}


}
