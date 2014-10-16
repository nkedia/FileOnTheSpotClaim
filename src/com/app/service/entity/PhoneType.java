
package com.app.service.entity;

import java.io.Serializable;

public class PhoneType implements Serializable{

	private static final long serialVersionUID = 1189082384158506426L;
	protected String office;
    protected String residence;
    protected String mobile;

    public String getOffice() {
        return office;
    }

    public void setOffice(String value) {
        this.office = value;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String value) {
        this.residence = value;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String value) {
        this.mobile = value;
    }

}
