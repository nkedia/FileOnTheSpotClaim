
package com.app.service.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class AccidentDetailsType implements Parcelable{

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

}
