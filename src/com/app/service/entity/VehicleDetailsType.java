
package com.app.service.entity;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

public class VehicleDetailsType implements Serializable{

	private static final long serialVersionUID = -2580177642041199018L;
	protected String regdNo;
    protected String make;
    protected XMLGregorianCalendar dateOfFirstRegistration;
    protected String chassisNo;
    protected String engineNo;
    protected XMLGregorianCalendar dateOfTransfer;
    protected String typeOfFuel;
    protected String color;

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

    public XMLGregorianCalendar getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(XMLGregorianCalendar value) {
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

    public XMLGregorianCalendar getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(XMLGregorianCalendar value) {
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

}
