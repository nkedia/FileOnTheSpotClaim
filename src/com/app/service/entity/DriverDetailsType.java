
package com.app.service.entity;


import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.os.Parcel;
import android.os.Parcelable;


public class DriverDetailsType implements Parcelable, KvmSerializable{

	protected String name;
    protected String relationWithInsured;
    protected String address;
    protected String contactNo;
    protected String dob;
    protected LicenseType licenseType;

    public DriverDetailsType() {
    	name = "";
    	relationWithInsured = "";
    	address = "";
    	contactNo = "";
    	dob = "";
    	licenseType = new LicenseType();
    	
	}

    public DriverDetailsType(Parcel source) {
    	setName(source.readString());
    	setRelationWithInsured(source.readString());
    	setAddress(source.readString());
    	setContactNo(source.readString());
    	setDOB(source.readString());
    	setLicenseType(new LicenseType(source.readString(), source.readString(),
    			source.readString(), source.readString(), source.readString(), source.readString()));

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

    public LicenseType getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
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
		dest.writeString(licenseType.getLicenseNo());
		dest.writeString(licenseType.getIssuingRTO());
		dest.writeString(licenseType.getEffectiveFrom());
		dest.writeString(licenseType.getExpiryDate());
		dest.writeString(licenseType.getClazz());
		dest.writeString(licenseType.getType());		
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

	@Override
	public Object getProperty(int index) {
		switch(index) {
		case 0: return name;
		case 1: return relationWithInsured;
		case 2: return address;
		case 3: return contactNo;
		case 4: return dob;
		case 5: return licenseType;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 6;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch(index) {
		case 0:
			info.setName("name");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 1:
			info.setName("relationWithInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 2:
			info.setName("address");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 3:
			info.setName("contactNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 4:
			info.setName("DOB");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 5:
			info.setName("license");
			info.setType(PropertyInfo.OBJECT_CLASS);
			break;
		default:
			throw new IllegalArgumentException();
		}		
	}

	@Override
	public void setProperty(int index, Object value) {
		switch(index) {
		case 0:
			this.setName(value.toString());
			break;
		case 1:
			this.setRelationWithInsured(value.toString());
			break;
		case 2:
			this.setAddress(value.toString());
			break;
		case 3:
			this.setContactNo(value.toString());
			break;
		case 4:
			this.setDOB(value.toString());
			break;
		case 5:
			this.setLicenseType((LicenseType)value);
			break;
		default:
			throw new IllegalArgumentException();
		
	}		
	}


}
