package com.app.service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.app.fileonthespotclaim.DocumentsActivity;
import com.app.service.entity.PolicyHolderDetailsType;

import android.os.AsyncTask;
import android.util.Log;

public class ClaimsServiceTask extends AsyncTask<SoapObject, Void, SoapPrimitive>{

	private static final String SOAP_ACTION =  "fileNewClaim";
	private static String URL="http://ec2-54-165-60-108.compute-1.amazonaws.com/ClaimsServiceProject/ClaimsService?wsdl";
	
	@Override
	protected SoapPrimitive doInBackground(SoapObject... params) {
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.addMapping(DocumentsActivity.NAMESPACE, "policyHolderDetails", PolicyHolderDetailsType.class);
		envelope.setOutputSoapObject(params[0]);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
		SoapPrimitive resultsRequestSOAP = null;
		try {
		   androidHttpTransport.call(SOAP_ACTION, envelope);
		   resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
		   Log.d("result", resultsRequestSOAP.toString());;
		  } catch (Exception e) {
			e.printStackTrace();  
		   
		  }
		finally{
			Log.d("request dump", androidHttpTransport.requestDump, null);
			Log.d("response dump", androidHttpTransport.responseDump, null);
		}
		return resultsRequestSOAP;
	}
	

	
}	
