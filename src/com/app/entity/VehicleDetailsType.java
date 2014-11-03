
package com.app.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.os.Parcel;
import android.os.Parcelable;


public class VehicleDetailsType implements Parcelable, KvmSerializable{

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
		dest.writeString(getRegdNo());
		dest.writeString(getMake());
		dest.writeString(getDateOfFirstRegistration());
		dest.writeString(getChassisNo());
		dest.writeString(getEngineNo());
		dest.writeString(getDateOfTransfer());
		dest.writeString(getTypeOfFuel());
		dest.writeString(getColor());
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

	@Override
	public Object getProperty(int index) {
		switch(index) {
		case 0: return regdNo;
		case 1: return make;
		case 2: return dateOfFirstRegistration;
		case 3: return chassisNo;
		case 4: return engineNo;
		case 5: return dateOfTransfer;
		case 6: return typeOfFuel;
		case 7: return color;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 8;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable table, PropertyInfo info){
		switch(index) {
		case 0:
			info.setName("regdNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 1:
			info.setName("make");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 2:
			info.setName("dateOfFirstRegistration");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 3:
			info.setName("chassisNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 4:
			info.setName("engineNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 5:
			info.setName("dateOfTransfer");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 6:
			info.setName("typeOfFuel");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 7:
			info.setName("color");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setProperty(int index, Object value) {
		switch(index) {
		case 0:
			this.setRegdNo(value.toString());
			break;
		case 1:
			this.setMake(value.toString());
			break;
		case 2:
			this.setDateOfFirstRegistration(value.toString());
			break;
		case 3:
			this.setChassisNo(value.toString());
			break;
		case 4:
			this.setEngineNo(value.toString());
			break;
		case 5:
			this.setDateOfTransfer(value.toString());
			break;
		case 6:
			this.setTypeOfFuel(value.toString());
			break;
		case 7:
			this.setColor(value.toString());
			break;
		default:
			throw new IllegalArgumentException();
		
	}
	}


}
