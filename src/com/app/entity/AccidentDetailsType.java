
package com.app.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.os.Parcel;
import android.os.Parcelable;

public class AccidentDetailsType implements Parcelable, KvmSerializable{

	protected String dateOfAccident;
    protected String time;
    protected String speed;
    protected String place;
    protected String noOfPeopleTravelling;
    protected String policeStationName;
    protected String firNo;
    protected String mileage;

    public AccidentDetailsType() {
    	dateOfAccident = "";
    	time = "";
    	speed = "";
    	place = "";
    	noOfPeopleTravelling = "";
    	policeStationName = "";
    	firNo = "";
    	mileage = "";
    }

    public AccidentDetailsType(Parcel source) {
    	setDateOfAccident(source.readString());
    	setTime(source.readString());
    	setSpeed(source.readString());
    	setPlace(source.readString());
    	setNoOfPeopleTravelling(source.readString());
    	setPoliceStationName(source.readString());
    	setFIRNo(source.readString());
    	setMileage(source.readString());
    	
    }

	public String getDateOfAccident() {
        return dateOfAccident;
    }

    public void setDateOfAccident(String value) {
        this.dateOfAccident = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String value) {
        this.speed = value;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String value) {
        this.place = value;
    }

    public String getNoOfPeopleTravelling() {
        return noOfPeopleTravelling;
    }

    public void setNoOfPeopleTravelling(String value) {
        this.noOfPeopleTravelling = value;
    }

    public String getPoliceStationName() {
        return policeStationName;
    }

    public void setPoliceStationName(String value) {
        this.policeStationName = value;
    }

    public String getFIRNo() {
        return firNo;
    }

    public void setFIRNo(String value) {
        this.firNo = value;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String value) {
        this.mileage = value;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getDateOfAccident());
		dest.writeString(getTime());
		dest.writeString(getSpeed());
		dest.writeString(getPlace());
		dest.writeString(getNoOfPeopleTravelling());
		dest.writeString(getPoliceStationName());
		dest.writeString(getFIRNo());
		dest.writeString(getMileage());
	}
	
	public static final Parcelable.Creator<AccidentDetailsType> CREATOR = new Creator<AccidentDetailsType>() {

		@Override
		public AccidentDetailsType createFromParcel(Parcel source) {
			return new AccidentDetailsType(source);
		}

		@Override
		public AccidentDetailsType[] newArray(int size) {
			return new AccidentDetailsType[size];
		}
	
	};

	@Override
	public Object getProperty(int index) {
		switch(index) {
		case 0: return dateOfAccident;
		case 1: return time;
		case 2: return speed;
		case 3: return place;
		case 4: return noOfPeopleTravelling;
		case 5: return policeStationName;
		case 6: return firNo;
		case 7: return mileage;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 8;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable table, PropertyInfo info) {
		switch(index) {
		case 0:
			info.setName("dateOfAccident");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 1:
			info.setName("time");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 2:
			info.setName("speed");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 3:
			info.setName("place");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 4:
			info.setName("noOfPeopleTravelling");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 5:
			info.setName("policeStationName");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 6:
			info.setName("FIRNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 7:
			info.setName("Mileage");
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
			this.setDateOfAccident(value.toString());
			break;
		case 1:
			this.setTime(value.toString());
			break;
		case 2:
			this.setSpeed(value.toString());
			break;
		case 3:
			this.setPlace(value.toString());
			break;
		case 4:
			this.setNoOfPeopleTravelling(value.toString());
			break;
		case 5:
			this.setPoliceStationName(value.toString());
			break;
		case 6:
			this.setFIRNo(value.toString());
			break;
		case 7:
			this.setMileage(value.toString());
			break;
		default:
			throw new IllegalArgumentException();
		
	}		
	}

}
