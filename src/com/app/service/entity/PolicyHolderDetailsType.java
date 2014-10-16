
package com.app.service.entity;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

public class PolicyHolderDetailsType implements Serializable{

	private static final long serialVersionUID = -448811522889272254L;
	protected String policyNo;
    protected String coverNoteNo;
    protected PeriodOfInsuranceType periodOfInsurance;
    protected String nameOfInsured;
    protected XMLGregorianCalendar dobOfInsured;
    protected String addressOfInsured;
    protected String pinOfInsured;
    protected PhoneType phoneOfInsured;
    protected String emailOfInsured;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String value) {
        this.policyNo = value;
    }

    public String getCoverNoteNo() {
        return coverNoteNo;
    }

    public void setCoverNoteNo(String value) {
        this.coverNoteNo = value;
    }

    public PeriodOfInsuranceType getPeriodOfInsurance() {
        return periodOfInsurance;
    }

    public void setPeriodOfInsurance(PeriodOfInsuranceType value) {
        this.periodOfInsurance = value;
    }

    public String getNameOfInsured() {
        return nameOfInsured;
    }

    public void setNameOfInsured(String value) {
        this.nameOfInsured = value;
    }

    public XMLGregorianCalendar getDobOfInsured() {
        return dobOfInsured;
    }

    public void setDobOfInsured(XMLGregorianCalendar value) {
        this.dobOfInsured = value;
    }

    public String getAddressOfInsured() {
        return addressOfInsured;
    }

    public void setAddressOfInsured(String value) {
        this.addressOfInsured = value;
    }

    public String getPinOfInsured() {
        return pinOfInsured;
    }

    public void setPinOfInsured(String value) {
        this.pinOfInsured = value;
    }

    public PhoneType getPhoneOfInsured() {
        return phoneOfInsured;
    }

    public void setPhoneOfInsured(PhoneType value) {
        this.phoneOfInsured = value;
    }

    public String getEmailOfInsured() {
        return emailOfInsured;
    }

    public void setEmailOfInsured(String value) {
        this.emailOfInsured = value;
    }

}
