
package com.app.service.entity;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

public class LicenseType implements Serializable{

	private static final long serialVersionUID = -5153298976840055930L;
	protected String licenseNo;
    protected String issuingRTO;
    protected XMLGregorianCalendar effectiveFrom;
    protected XMLGregorianCalendar expiryDate;
    protected ClassType clazz;
    protected Type type;

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

    public XMLGregorianCalendar getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(XMLGregorianCalendar value) {
        this.effectiveFrom = value;
    }

    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

    public ClassType getClazz() {
        return clazz;
    }

    public void setClazz(ClassType value) {
        this.clazz = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type value) {
        this.type = value;
    }

}
