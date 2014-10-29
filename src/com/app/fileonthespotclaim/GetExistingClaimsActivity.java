package com.app.fileonthespotclaim;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.app.service.ExistingClaimsServiceTask;
import com.app.service.NewClaimsServiceTask;
import com.app.service.entity.AccidentDetailsType;
import com.app.service.entity.ClaimsType;
import com.app.service.entity.DriverDetailsType;
import com.app.service.entity.LicenseType;
import com.app.service.entity.PeriodOfInsuranceType;
import com.app.service.entity.PhoneType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.app.service.entity.VehicleDetailsType;
import com.example.fileonthespotclaim.R;

public class GetExistingClaimsActivity extends ActionBarActivity {
	
	Button bt;
	TableLayout existingClaimsTable;
	
	private static final String NAMESPACE = "localhost:8080/ClaimsService/";
	private static final String METHOD_NAME = "getExistingClaims";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_existing_claims);
		
		PropertyInfo policyId = new PropertyInfo();
		policyId.name = "policyId";
		policyId.type = String.class;
		policyId.setValue("84321");
		
		SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
		List<SoapObject> result = null;
		request.addProperty(policyId);
		try {
			//call getExistingClaims web service
			result = new ExistingClaimsServiceTask().execute(request).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		List<ClaimsType> claimsList = new ArrayList<ClaimsType>();
		
		for(SoapObject soapObj : result) {
			Log.d("soapObj", soapObj.toString());
			ClaimsType claim = new ClaimsType();
			claim.setClaimId(soapObj.getPrimitivePropertyAsString("claimId"));
			claim.setClaimStatus(soapObj.getPrimitivePropertyAsString("claimStatus"));
			claim.setAmountSettled(soapObj.getPrimitivePropertyAsString("amountSettled"));
			claim.setDateOfSettlement(soapObj.getPrimitivePropertyAsString("dateOfSettlement"));
			
			PolicyHolderDetailsType policyHolderDetails = getPolicyHolderDetails(soapObj);
			VehicleDetailsType vehicleDetails = getVehicleDetails(soapObj);
			AccidentDetailsType accidentDetails = getAccidentDetails(soapObj);
			DriverDetailsType driverDetails = getDriverDetails(soapObj);

			claim.setPolicyHolderDetails(policyHolderDetails);
			claim.setVehicleDetails(vehicleDetails);
			claim.setAccidentDetails(accidentDetails);
			claim.setDriverDetails(driverDetails);
			claimsList.add(claim);
		}
		
		existingClaimsTable = (TableLayout) findViewById(R.id.existingClaimsTable);
		existingClaimsTable.setStretchAllColumns(true);	
		existingClaimsTable.bringToFront();
		
		TableRow tr1 =  new TableRow(this);
        TextView c1 = new TextView(this);
        c1.setText("ID    ");
        tr1.addView(c1);
        TextView c2 = new TextView(this);
        c2.setText("Status");
        tr1.addView(c2);
        TextView c3 = new TextView(this);
        c3.setText("Amount");
        tr1.addView(c3);
        TextView c4 = new TextView(this);
        c4.setText("Date");
        tr1.addView(c4);
        existingClaimsTable.addView(tr1);
		
		
		for(ClaimsType claim : claimsList) {
			TableRow tr2 =  new TableRow(this);
	        
			TextView c21 = new TextView(this);
	        c21.setText(claim.getClaimId());
	        tr2.addView(c21);
	        TextView c22 = new TextView(this);
	        c22.setText(claim.getClaimStatus());
	        tr2.addView(c22);
	        TextView c23 = new TextView(this);
	        c23.setText(claim.getAmountSettled());
	        tr2.addView(c23);
	        TextView c24 = new TextView(this);
	        c24.setText(claim.getDateOfSettlement());
	        tr2.addView(c24);
	        
	        tr2.setClickable(true);
	        tr2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
	        tr2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                v.setBackgroundColor(Color.GRAY);
	                System.out.println("Row clicked: " + v.getId());

	               //get the data you need
	               TableRow tablerow = (TableRow)v.getParent();
	               TextView sample = (TextView) tablerow.getChildAt(2);
	               String result=sample.getText().toString();
	            }
	        });
	        
	        existingClaimsTable.addView(tr2);
		}
		
			
		Button bt = (Button) findViewById(R.id.back);
		Button.OnClickListener myListener = new Button.OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(GetExistingClaimsActivity.this, MainActivity.class);
				GetExistingClaimsActivity.this.startActivity(myIntent);
			}

		};

		bt.setOnClickListener(myListener);
	}


	private DriverDetailsType getDriverDetails(SoapObject soapObj) {
		DriverDetailsType driverDetails = new DriverDetailsType();
		SoapObject driverDetailsObj = (SoapObject) soapObj.getProperty("driverDetails");
		driverDetails.setName(driverDetailsObj.getPrimitivePropertyAsString("name"));
		driverDetails.setRelationWithInsured(driverDetailsObj.getPrimitivePropertyAsString("relationWithInsured"));
		driverDetails.setAddress(driverDetailsObj.getPrimitivePropertyAsString("address"));
		driverDetails.setContactNo(driverDetailsObj.getPrimitivePropertyAsString("contactNo"));
		driverDetails.setDOB(driverDetailsObj.getPrimitivePropertyAsString("DOB"));
		LicenseType license = new LicenseType();
		SoapObject licenseObj = (SoapObject) driverDetailsObj.getProperty("license");
		license.setLicenseNo(licenseObj.getPrimitivePropertyAsString("licenseNo"));
		license.setIssuingRTO(licenseObj.getPrimitivePropertyAsString("issuingRTO"));
		license.setEffectiveFrom(licenseObj.getPrimitivePropertyAsString("effectiveFrom"));
		license.setExpiryDate(licenseObj.getPrimitivePropertyAsString("expiryDate"));
		license.setClazz(licenseObj.getPrimitivePropertyAsString("class"));
		license.setType(licenseObj.getPrimitivePropertyAsString("type"));
		driverDetails.setLicenseType(license);
		return driverDetails;
	}


	private AccidentDetailsType getAccidentDetails(SoapObject soapObj) {
		
		AccidentDetailsType accidentDetails = new AccidentDetailsType();
		SoapObject accidentDetailsObj = (SoapObject) soapObj.getProperty("accidentDetails");
		accidentDetails.setDateOfAccident(accidentDetailsObj.getPrimitivePropertyAsString("dateOfAccident"));
		accidentDetails.setFIRNo(accidentDetailsObj.getPrimitivePropertyAsString("FIRNo"));
		accidentDetails.setMileage(accidentDetailsObj.getPrimitivePropertyAsString("Mileage"));
		accidentDetails.setNoOfPeopleTravelling(accidentDetailsObj.getPrimitivePropertyAsString("noOfPeopleTravelling"));
		accidentDetails.setPlace(accidentDetailsObj.getPrimitivePropertyAsString("place"));
		accidentDetails.setPoliceStationName(accidentDetailsObj.getPrimitivePropertyAsString("policeStationName"));
		accidentDetails.setSpeed(accidentDetailsObj.getPrimitivePropertyAsString("speed"));
		accidentDetails.setTime(accidentDetailsObj.getPrimitivePropertyAsString("time"));
		return accidentDetails;
	}


	private VehicleDetailsType getVehicleDetails(SoapObject soapObj) {
		
		VehicleDetailsType vehicleDetails = new VehicleDetailsType();
		SoapObject vehicleDetailsObj = (SoapObject) soapObj.getProperty("vehicleDetails");
		vehicleDetails.setRegdNo(vehicleDetailsObj.getPrimitivePropertyAsString("regdNo"));
		vehicleDetails.setChassisNo(vehicleDetailsObj.getPrimitivePropertyAsString("chassisNo"));
		vehicleDetails.setEngineNo(vehicleDetailsObj.getPrimitivePropertyAsString("engineNo"));
		vehicleDetails.setDateOfFirstRegistration(vehicleDetailsObj.getPrimitivePropertyAsString("dateOfFirstRegistration"));
		vehicleDetails.setDateOfTransfer(vehicleDetailsObj.getPrimitivePropertyAsString("dateOfTransfer"));
		vehicleDetails.setMake(vehicleDetailsObj.getPrimitivePropertyAsString("make"));
		vehicleDetails.setTypeOfFuel(vehicleDetailsObj.getPrimitivePropertyAsString("typeOfFuel"));
		vehicleDetails.setColor(vehicleDetailsObj.getPrimitivePropertyAsString("color"));
		return vehicleDetails;
	}


	private PolicyHolderDetailsType getPolicyHolderDetails(SoapObject soapObj) {
		
		PolicyHolderDetailsType policyHolderDetails = new PolicyHolderDetailsType();
		SoapObject policyHolderDetailsObj = (SoapObject) soapObj.getProperty("policyHolderDetails");
		policyHolderDetails.setPolicyNo(policyHolderDetailsObj.getPrimitivePropertyAsString("PolicyNo"));
		policyHolderDetails.setCoverNoteNo(policyHolderDetailsObj.getPrimitivePropertyAsString("coverNoteNo"));
		PeriodOfInsuranceType periodOfInsurance = new PeriodOfInsuranceType();
		SoapObject periodObj = (SoapObject) policyHolderDetailsObj.getProperty("periodOfInsurance");
		periodOfInsurance.setFrom(periodObj.getPrimitivePropertyAsString("from"));
		periodOfInsurance.setTo(periodObj.getPrimitivePropertyAsString("to"));
		policyHolderDetails.setPeriodOfInsurance(periodOfInsurance);
		policyHolderDetails.setNameOfInsured(policyHolderDetailsObj.getPrimitivePropertyAsString("NameOfInsured"));
		policyHolderDetails.setDobOfInsured(policyHolderDetailsObj.getPrimitivePropertyAsString("dobOfInsured"));
		policyHolderDetails.setAddressOfInsured(policyHolderDetailsObj.getPrimitivePropertyAsString("addressOfInsured"));
		policyHolderDetails.setPinOfInsured(policyHolderDetailsObj.getPrimitivePropertyAsString("pinOfInsured"));
		PhoneType phone = new PhoneType();
		SoapObject phoneObj = (SoapObject) policyHolderDetailsObj.getProperty("phoneOfInsured");
		phone.setMobile(phoneObj.getPrimitivePropertyAsString("mobile"));
		phone.setOffice(phoneObj.getPrimitivePropertyAsString("office"));
		phone.setResidence(phoneObj.getPrimitivePropertyAsString("residence"));
		policyHolderDetails.setPhoneType(phone);
		policyHolderDetails.setEmailOfInsured(policyHolderDetailsObj.getPrimitivePropertyAsString("emailOfInsured"));
		return policyHolderDetails;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_existing_claims, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
