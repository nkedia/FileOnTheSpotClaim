package com.app.service;

import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

public class ExistingClaimsServiceTask  extends AsyncTask<SoapObject, Void, List<SoapObject>>{

	private static final String NAMESPACE = "localhost:8080/ClaimsService/";
	private static final String URL = "http://ec2-54-165-60-108.compute-1.amazonaws.com/ClaimsServiceProject/ClaimsService?wsdl";
	private String soapAction =  "";
	
	
	public ExistingClaimsServiceTask() {
	}
	
	public ExistingClaimsServiceTask(String soapAction) {
		this.soapAction = soapAction;
	}
	
	public String getSoapAction() {
		return soapAction;
	}

	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
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
			androidHttpTransport.call(soapAction, envelope);
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
