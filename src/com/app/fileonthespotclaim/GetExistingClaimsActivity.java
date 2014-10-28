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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.app.service.ExistingClaimsServiceTask;
import com.app.service.NewClaimsServiceTask;
import com.app.service.entity.ClaimsType;
import com.app.service.entity.DriverDetailsType;
import com.app.service.entity.PolicyHolderDetailsType;
import com.example.fileonthespotclaim.R;

public class GetExistingClaimsActivity extends ActionBarActivity {
	
	Button bt;
	TableLayout existingClaimsTable;
	
	private static final String NAMESPACE = "localhost:8080/ClaimsService/";
	private static final String METHOD_NAME = "getExistingClaims";
	private static final String URL = "http://ec2-54-165-60-108.compute-1.amazonaws.com/ClaimsServiceProject/ClaimsService?wsdl";
	 
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
			result = new ExistingClaimsServiceTask(METHOD_NAME, URL).execute(request).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		/*ClaimsType[] claimsTypes = new ClaimsType[result.getPropertyCount()];
		for(int i=0 ; i<claimsTypes.length; i++) {
			SoapObject obj = (SoapObject) result.getProperty(i);
            ClaimsType claim = new ClaimsType();
            claim.setClaimId(obj.getProperty(0).toString());
            claim.setClaimStatus(obj.getProperty(1).toString());
            claimsTypes[i] = claim;
		}*/
		
		Log.d("result for getExisiting claims", result.toString());
		System.out.println(result);
		
		existingClaimsTable = (TableLayout) findViewById(R.id.existingClaimsTable);
		existingClaimsTable.setStretchAllColumns(true);	
		existingClaimsTable.bringToFront();
		List<String> list = new ArrayList<>();
		list.add("abc");
		list.add("abcd");
		list.add("abce");
		list.add("abcf");
		list.add("abcg");
		
		for(String s : list) {
			TableRow tr =  new TableRow(this);
	        TextView c1 = new TextView(this);
	        c1.setText(s);
	        tr.addView(c1);
	        existingClaimsTable.addView(tr);
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
