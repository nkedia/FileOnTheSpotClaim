
package com.app.service.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class LicenseType implements KvmSerializable{

    protected String licenseNo;
    protected String issuingRTO;
    protected String effectiveFrom;
    protected String expiryDate;
    protected String clazz;
    protected String type;
    
    public LicenseType() {
    	
    }

    public LicenseType(String licenseNo, String issuingRTO,
			String effectiveFrom, String expiryDate, String clazz,
			String type) {
    	this.licenseNo = licenseNo;
    	this.issuingRTO = issuingRTO;
    	this.effectiveFrom = effectiveFrom;
    	this.expiryDate = expiryDate;
    	this.clazz = clazz;
    	this.type = type;
    }

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

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String value) {
        this.effectiveFrom = value;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String value) {
        this.expiryDate = value;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String value) {
        this.clazz = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

	@Override
	public Object getProperty(int index) {
		switch(index) {
		case 0: return licenseNo;
		case 1: return issuingRTO;
		case 2: return effectiveFrom;
		case 3: return expiryDate;
		case 4: return clazz;
		case 5: return type;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 6;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable table, PropertyInfo info) {
		switch(index) {
		case 0:
			info.setName("licenseNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 1:
			info.setName("issuingRTO");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 2:
			info.setName("effectiveFrom");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 3:
			info.setName("expiryDate");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 4:
			info.setName("class");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 5:
			info.setName("type");
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
			this.setLicenseNo(value.toString());
			break;
		case 1:
			this.setIssuingRTO(value.toString());
			break;
		case 2:
			this.setEffectiveFrom(value.toString());
			break;
		case 3:
			this.setExpiryDate(value.toString());
			break;
		case 4:
			this.setClazz(value.toString());
			break;
		case 5:
			this.setType(value.toString());
			break;
		default:
			throw new IllegalArgumentException();
		
	}		
	}

}
