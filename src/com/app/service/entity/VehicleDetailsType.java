
package com.app.service.entity;

import android.os.Parcel;
import android.os.Parcelable;


public class VehicleDetailsType implements Parcelable{

	protected String regdNo;
    protected String make;
    protected String dateOfFirstRegistration;
    protected String chassisNo;
    protected String engineNo;
    protected String dateOfTransfer;
    protected String typeOfFuel;
    protected String color;

    public VehicleDetailsType() {
    	regdNo = "";
    	make = "";
    	dateOfFirstRegistration = "";
    	chassisNo = "";
    	engineNo = "";
    	dateOfTransfer = "";
    	typeOfFuel = "";
    	color = "";
    }
    
    public VehicleDetailsType(Parcel source) {
    	setRegdNo(source.readString());
    	setMake(source.readString());
    	setDateOfFirstRegistration(source.readString());
    	setChassisNo(source.readString());
    	setEngineNo(source.readString());
    	setDateOfTransfer(source.readString());
    	setTypeOfFuel(source.readString());
    	setColor(source.readString());
	}

	public String getRegdNo() {
        return regdNo;
    }

    public void setRegdNo(String value) {
        this.regdNo = value;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String value) {
        this.make = value;
    }

    public String getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(String value) {
        this.dateOfFirstRegistration = value;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String value) {
        this.chassisNo = value;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String value) {
        this.engineNo = value;
    }

    public String getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(String value) {
        this.dateOfTransfer = value;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String value) {
        this.typeOfFuel = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String value) {
        this.color = value;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getChassisNo());
		dest.writeString(getColor());
		dest.writeString(getDateOfFirstRegistration());
		dest.writeString(getDateOfTransfer());
		dest.writeString(getEngineNo());
		dest.writeString(getMake());
		dest.writeString(getRegdNo());
		dest.writeString(getTypeOfFuel());
	}
	
	public static final Parcelable.Creator<VehicleDetailsType> CREATOR = new Creator<VehicleDetailsType>() {

		@Override
		public VehicleDetailsType createFromParcel(Parcel source) {
			return new VehicleDetailsType(source);
		}

		@Override
		public VehicleDetailsType[] newArray(int size) {
			return new VehicleDetailsType[size];
		}
	
	};


}
