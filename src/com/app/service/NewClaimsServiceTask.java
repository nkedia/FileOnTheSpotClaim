package com.app.service;

import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

public class NewClaimsServiceTask extends AsyncTask<SoapObject, Void, SoapPrimitive>{

	private static final String SOAP_ACTION =  "fileNewClaim";
	private static final String URL = "http://ec2-54-165-60-108.compute-1.amazonaws.com/ClaimsServiceProject/ClaimsService?wsdl";
	
	@Override
	protected SoapPrimitive doInBackground(SoapObject... params) {
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(params[0]);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
		SoapPrimitive response = null;
		try {
		   androidHttpTransport.call(SOAP_ACTION, envelope);
		   response = (SoapPrimitive) ((List) envelope.getResponse()).get(0);
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
