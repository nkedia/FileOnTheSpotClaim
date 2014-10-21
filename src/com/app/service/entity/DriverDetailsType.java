
package com.app.service.entity;


import android.os.Parcel;
import android.os.Parcelable;


public class DriverDetailsType implements Parcelable{

	protected String name;
    protected String relationWithInsured;
    protected String address;
    protected String contactNo;
    protected String dob;
    protected String licenseNo;
    protected String issuingRTO;
    protected String effectiveFrom;
    protected String expiryDate;
    protected String clazz;
    protected String type;

    public DriverDetailsType() {
    	name = "";
    	relationWithInsured = "";
    	address = "";
    	contactNo = "";
    	dob = "";
    	licenseNo = "";
    	issuingRTO = "";
    	effectiveFrom = "";
    	expiryDate = "";
    	clazz = "";
    	type = "";
    	
	}

    public DriverDetailsType(Parcel source) {
    	setName(source.readString());
    	setRelationWithInsured(source.readString());
    	setAddress(source.readString());
    	setContactNo(source.readString());
    	setDOB(source.readString());
    	setLicenseNo(source.readString());
    	setIssuingRTO(source.readString());
    	setEffectiveFrom(source.readString());
    	setExpiryDate(source.readString());
    	setClazz(source.readString());
    	setType(source.readString());

    }

	public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getRelationWithInsured() {
        return relationWithInsured;
    }

    public void setRelationWithInsured(String value) {
        this.relationWithInsured = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String value) {
        this.contactNo = value;
    }

    public String getDOB() {
        return dob;
    }

    public void setDOB(String value) {
        this.dob = value;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String value) {
        this.licenseNo = value;
    }

    public String getIssuingRTO() {
        return issuingRTO;
    }

    public void setIssuingRTO(String value) {
        this.issuingRTO = value;
    }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String value) {
        this.effectiveFrom = value;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String value) {
        this.expiryDate = value;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String value) {
        this.clazz = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getName());
		dest.writeString(getRelationWithInsured());
		dest.writeString(getAddress());
		dest.writeString(getContactNo());
		dest.writeString(getDOB());
		dest.writeString(getLicenseNo());
		dest.writeString(getIssuingRTO());
		dest.writeString(getEffectiveFrom());
		dest.writeString(getExpiryDate());
		dest.writeString(getClazz());
		dest.writeString(getType());;		
	}
	
	public static final Parcelable.Creator<DriverDetailsType> CREATOR = new Creator<DriverDetailsType>() {

		@Override
		public DriverDetailsType createFromParcel(Parcel source) {
			return new DriverDetailsType(source);
		}

		@Override
		public DriverDetailsType[] newArray(int size) {
			return new DriverDetailsType[size];
		}
	
	};


}
