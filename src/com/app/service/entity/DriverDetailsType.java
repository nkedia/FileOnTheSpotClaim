
package com.app.service.entity;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

public class DriverDetailsType implements Serializable{

	private static final long serialVersionUID = 1889973629264102886L;
	protected String name;
    protected String relationWithInsured;
    protected String address;
    protected String contactNo;
    protected XMLGregorianCalendar dob;
    protected LicenseType license;

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

    public XMLGregorianCalendar getDOB() {
        return dob;
    }

    public void setDOB(XMLGregorianCalendar value) {
        this.dob = value;
    }

    public LicenseType getLicense() {
        return license;
    }

    public void setLicense(LicenseType value) {
        this.license = value;
    }

}
