package com.app.service;

import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

public class ExistingClaimsServiceTask  extends AsyncTask<SoapObject, Void, List<SoapObject>>{
	private String soapAction =  "";
	private String URL = "";
	
	public ExistingClaimsServiceTask(String soapAction, String URL) {
		this.soapAction = soapAction;
		this.URL = URL;
	}
	
	public String getSoapAction() {
		return soapAction;
	}

	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	protected List<SoapObject> doInBackground(SoapObject... params) {
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(params[0]);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
		List<SoapObject> response = null;
		try {
		   androidHttpTransport.call(this.soapAction, envelope);
		   response = (List<SoapObject>) envelope.getResponse();
		   Log.d("result", response.toString());;
		  } catch (Exception e) {
			e.printStackTrace();  
		   
		  }
		finally{
			Log.d("request dump", androidHttpTransport.requestDump, null);
			Log.d("response dump", androidHttpTransport.responseDump, null);
		}
		return response;
	}
	

}
