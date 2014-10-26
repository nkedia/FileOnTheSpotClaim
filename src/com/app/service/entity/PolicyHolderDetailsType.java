
package com.app.service.entity;


import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.os.Parcel;
import android.os.Parcelable;

public class PolicyHolderDetailsType implements Parcelable,KvmSerializable{

	protected String policyNo;
    protected String coverNoteNo;
    protected PeriodOfInsuranceType periodOfInsurance;
    protected String nameOfInsured;
    protected String dobOfInsured;
    protected String addressOfInsured;
    protected String pinOfInsured;
    protected PhoneType phoneType;
    protected String emailOfInsured;

    public PolicyHolderDetailsType(Parcel source) {
		setPolicyNo(source.readString());
		setCoverNoteNo(source.readString());
		String from = source.readString();
		String to = source.readString();
		setPeriodOfInsurance(new PeriodOfInsuranceType(from, to));
		setNameOfInsured(source.readString());
		setDobOfInsured(source.readString());
		setAddressOfInsured(source.readString());
		setPinOfInsured(source.readString());
		setPhoneType(new PhoneType(source.readString(), source.readString(), source.readString()));
		setEmailOfInsured(source.readString());
		
	}

	public PolicyHolderDetailsType() {
		policyNo = "";
		coverNoteNo = "";
		periodOfInsurance = new PeriodOfInsuranceType();
		nameOfInsured = "";
		dobOfInsured = "";
		addressOfInsured = "";
		pinOfInsured = "";
		phoneType = new PhoneType();
		emailOfInsured = "";
	}

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

	public void setPeriodOfInsurance(PeriodOfInsuranceType periodOfInsurance) {
		this.periodOfInsurance = periodOfInsurance;
	}

	public String getNameOfInsured() {
        return nameOfInsured;
    }

    public void setNameOfInsured(String value) {
        this.nameOfInsured = value;
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


    public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public String getEmailOfInsured() {
        return emailOfInsured;
    }

    public void setEmailOfInsured(String value) {
        this.emailOfInsured = value;
    }
    
	public String getDobOfInsured() {
		return dobOfInsured;
	}

	public void setDobOfInsured(String dobOfInsured) {
		this.dobOfInsured = dobOfInsured;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getPolicyNo());
		dest.writeString(getCoverNoteNo());
		dest.writeString(periodOfInsurance.getFrom());
		dest.writeString(periodOfInsurance.getTo());
		dest.writeString(getNameOfInsured());
		dest.writeString(getDobOfInsured());
		dest.writeString(getAddressOfInsured());
		dest.writeString(getPinOfInsured());
		dest.writeString(phoneType.getOffice());
		dest.writeString(phoneType.getResidence());
		dest.writeString(phoneType.getMobile());
		dest.writeString(getEmailOfInsured());
		
	}
	
	public static final Parcelable.Creator<PolicyHolderDetailsType> CREATOR = new Creator<PolicyHolderDetailsType>() {

		@Override
		public PolicyHolderDetailsType createFromParcel(Parcel source) {
			return new PolicyHolderDetailsType(source);
		}

		@Override
		public PolicyHolderDetailsType[] newArray(int size) {
			return new PolicyHolderDetailsType[size];
		}
	
	};

	@Override
	public Object getProperty(int index) {
		switch(index) {
		case 0: return policyNo;
		case 1: return coverNoteNo;
		case 2: return periodOfInsurance;
		case 3: return nameOfInsured;
		case 4: return dobOfInsured;
		case 5: return addressOfInsured;
		case 6: return pinOfInsured;
		case 7: return phoneType;
		case 8 : return emailOfInsured;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 9;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable table, PropertyInfo info) {
		switch(index) {
		case 0:
			info.setName("PolicyNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 1:
			info.setName("coverNoteNo");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 2:
			info.setName("periodOfInsurance");
			info.setType(PropertyInfo.OBJECT_CLASS);
			break;
		case 3:
			info.setName("NameOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 4:
			info.setName("dobOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 5:
			info.setName("addressOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 6:
			info.setName("pinOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 7:
			info.setName("phoneOfInsured");
			info.setType(PropertyInfo.OBJECT_CLASS);
			break;
		case 8:
			info.setName("emailOfInsured");
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
			this.setPolicyNo(value.toString());
			break;
		case 1:
			this.setCoverNoteNo(value.toString());
			break;
		case 2:
			this.setPeriodOfInsurance((PeriodOfInsuranceType)value);
			break;
		case 3:
			this.setNameOfInsured(value.toString());
			break;
		case 4:
			this.setDobOfInsured(value.toString());
			break;
		case 5:
			this.setAddressOfInsured(value.toString());
			break;
		case 6:
			this.setPinOfInsured(value.toString());
			break;
		case 7:
			this.setPhoneType((PhoneType)value);
			break;
		case 8:
			this.setEmailOfInsured(value.toString());
			break;
		default:
			throw new IllegalArgumentException();
		
	}
	}

}
