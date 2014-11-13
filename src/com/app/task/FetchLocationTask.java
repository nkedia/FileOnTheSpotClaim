package com.app.task;

import java.io.InputStream;

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

public class FetchLocationTask extends AsyncTask<String, Void, String>{


	@Override
	protected String doInBackground(String... plookupString) {
		String lookupString = plookupString[0];
		final String lookupStringUriencoded = Uri.encode(lookupString);
		HttpGet httpGet = new HttpGet(
				"https://maps.googleapis.com/maps/api/geocode/json?latlng="
						+ lookupStringUriencoded);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();
		String currPlace="";
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
				currPlace = jsonObject.getString("formatted_address");
				Log.d("MAPSAPI", "currPlace " + currPlace);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return currPlace;
	}


}
