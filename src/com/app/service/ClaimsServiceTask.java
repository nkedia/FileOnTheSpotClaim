package com.app.service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

public class ClaimsServiceTask extends AsyncTask<SoapObject, Void, SoapPrimitive>{

	private static final String SOAP_ACTION =  "http://192.168.1.9:8080/ClaimsService/fileNewClaim/";
	private static String URL="http://192.168.1.9:8080/ClaimsServiceProject/ClaimsService?wsdl";
	
	@Override
	protected SoapPrimitive doInBackground(SoapObject... params) {
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(params[0]);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		SoapPrimitive resultsRequestSOAP = null;
		try {
		   androidHttpTransport.call(SOAP_ACTION, envelope);
		   resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
		   Log.d("result", resultsRequestSOAP.toString());
		  } catch (Exception e) {
			e.printStackTrace();  
		   
		  }
		return resultsRequestSOAP;
	}
	

	
}	
