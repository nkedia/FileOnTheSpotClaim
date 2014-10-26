
package com.app.service.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;


public class PeriodOfInsuranceType implements KvmSerializable{
	
	protected String from;
	protected String to;
	
	public PeriodOfInsuranceType() {
		
	}

	public PeriodOfInsuranceType(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public String getFrom() {
        return from;
    }

    public void setFrom(String value) {
        this.from = value;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String value) {
        this.to = value;
    }

	@Override
	public Object getProperty(int index) {
		switch(index) {
		case 0: return from;
		case 1: return to;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 2;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable table, PropertyInfo info) {
		switch(index) {
		case 0:
			info.setName("from");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 1:
			info.setName("to");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public void setProperty(int index, Object value) {
		switch(index) {
		case 0:
			this.setFrom(value.toString());
			break;
		case 1:
			this.setTo(value.toString());
			break;		
		default: throw new IllegalArgumentException("");
		}
	}

}
