
package com.app.service.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class PhoneType implements KvmSerializable{

    protected String office;
    protected String residence;
    protected String mobile;

    public PhoneType(String office, String residence, String mobile) {
    	this.office = office;
    	this.residence = residence;
    	this.mobile = mobile;
    }

	public PhoneType() {
		
	}

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

	@Override
	public Object getProperty(int index) {
		switch(index) {
		case 0: return office;
		case 1: return residence;
		case 2: return mobile;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 3;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable table, PropertyInfo info) {
		switch(index) {
		case 0:
			info.setName("office");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 1:
			info.setName("residence");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 2:
			info.setName("mobile");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		default: throw new IllegalArgumentException("");
		}		
	}

	@Override
	public void setProperty(int index, Object value) {
		switch(index) {
		case 0:
			this.setOffice(value.toString());
			break;
		case 1:
			this.setResidence(value.toString());
			break;		
		case 2:
			this.setMobile(value.toString());
			break;		
		default: throw new IllegalArgumentException("");
		}		
	}

}
