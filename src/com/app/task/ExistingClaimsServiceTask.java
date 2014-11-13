package com.app.task;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

public class ExistingClaimsServiceTask  extends AsyncTask<SoapObject, Void, List<SoapObject>>{

	private static final String URL = "http://ec2-54-165-60-108.compute-1.amazonaws.com/ClaimsServiceProject/ClaimsService?wsdl";
	private static final String SOAP_ACTION =  "getExistingClaims";
	
	@Override
	protected List<SoapObject> doInBackground(SoapObject... params) {

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(params[0]);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
		List<SoapObject> response = new ArrayList<SoapObject>();
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			if((envelope.getResponse()) instanceof List)
				response = (List<SoapObject>) envelope.getResponse();
			else
				response.add((SoapObject)envelope.getResponse());
			Log.d("result", response.toString());;
		} catch (Exception e) {
			response = null;

		}
		finally{
			Log.d("request dump", androidHttpTransport.requestDump, null);
			Log.d("response dump", androidHttpTransport.responseDump, null);
		}
		return response;
	}


}
