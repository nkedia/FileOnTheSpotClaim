package com.app.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/** Helper to the database, manages versions and creation */
public class ClaimDataSQLHelper extends SQLiteOpenHelper {
	

	// Table name
	public static final String TABLE = "claimdata";

	// Columns
	public static final String POLICY_NO = "policyNo";
	public static final String COVERNOTE_NO = "coverNoteNo";
	public static final String FROM = "fromDate";
	public static final String TO = "toDate";
	public static final String NAME = "name";
	public static final String DOB = "dob";
	public static final String ADDRESS = "address";
	public static final String PIN = "pin";
	public static final String PHONE_OFF = "office";
	public static final String PHONE_RES = "residence";
	public static final String PHONE_MOB = "mobile";
	public static final String EMAIL = "email";
	public static final String REGD_NO = "regdNo";
	public static final String MAKE = "makeVehicle";
	public static final String DATE_FIRST_REGISTRATION = "dateFirstRegistration";
	public static final String CHASSIS_NO = "chassisNo";
	public static final String ENGINE_NO = "engineNo";
	public static final String DATE_TRANSFER = "dateTransfer";
	public static final String TYPE_FUEL = "typeFuel";
	public static final String COLOR = "colorVehicle";
	public static final String LICENSE_NO = "licenseNo";
	public static final String RTO = "rto";
	public static final String EFFECTIVE_FROM = "effectiveFrom";
	public static final String EXPIRY_DATE = "expiryDate";
	public static final String VEHICLE_CLASS = "vehicleClass";
	public static final String VEHICLE_TYPE = "vehicleType";
	
	
	public ClaimDataSQLHelper(Context context, String dbName,
			CursorFactory factory, int version) {
		super(context, dbName, factory, version);
		//context.deleteDatabase(dbName);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table " + TABLE + "( " 
				+ BaseColumns._ID + " integer primary key autoincrement, " 
				+ POLICY_NO + " text, "
				+ COVERNOTE_NO + " text, "
				+ FROM + " text, "
				+ TO + " text, "
				+ NAME + " text, "
				+ DOB + " text, "
				+ ADDRESS + " text, "
				+ PIN + " text, "
				+ PHONE_OFF + " text, "
				+ PHONE_RES + " text, "
				+ PHONE_MOB + " text, "
				+ EMAIL + " text, "
				+ REGD_NO + " text, "
				+ MAKE + " text, "
				+ DATE_FIRST_REGISTRATION + " text, "
				+ CHASSIS_NO + " text, "
				+ ENGINE_NO + " text, "
				+ DATE_TRANSFER + " text, "
				+ TYPE_FUEL + " text, "
				+ COLOR + " text, "
				+ LICENSE_NO + " text, "
				+ RTO + " text, "
				+ EFFECTIVE_FROM + " text, "
				+ EXPIRY_DATE + " text, "
				+ VEHICLE_CLASS + " text, "
				+ VEHICLE_TYPE + " text);";
		Log.d("claimdata", "onCreate: " + sql);
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
