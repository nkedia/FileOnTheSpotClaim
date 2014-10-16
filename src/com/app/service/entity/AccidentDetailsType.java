
package com.app.service.entity;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

public class AccidentDetailsType implements Serializable{

	private static final long serialVersionUID = 187051818772640337L;
	protected XMLGregorianCalendar dateOfAccident;
    protected XMLGregorianCalendar time;
    protected String speed;
    protected String place;
    protected String noOfPeopleTravelling;
    protected String policeStationName;
    protected String firNo;
    protected String mileage;

    public XMLGregorianCalendar getDateOfAccident() {
        return dateOfAccident;
    }

    public void setDateOfAccident(XMLGregorianCalendar value) {
        this.dateOfAccident = value;
    }

    public XMLGregorianCalendar getTime() {
        return time;
    }

    public void setTime(XMLGregorianCalendar value) {
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

}
