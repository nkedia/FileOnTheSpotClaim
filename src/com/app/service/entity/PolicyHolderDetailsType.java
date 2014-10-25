
package com.app.service.entity;


import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.os.Parcel;
import android.os.Parcelable;

public class PolicyHolderDetailsType implements Parcelable,KvmSerializable{

	protected String policyNo;
    protected String coverNoteNo;
    protected String fromDate;
    protected String toDate;
    protected String nameOfInsured;
    protected String dobOfInsured;
    protected String addressOfInsured;
    protected String pinOfInsured;
    protected String office;
    protected String residence;
    protected String mobile;
    protected String emailOfInsured;

    public PolicyHolderDetailsType(Parcel source) {
		setPolicyNo(source.readString());
		setCoverNoteNo(source.readString());
		setFromDate(source.readString());
		setToDate(source.readString());
		setNameOfInsured(source.readString());
		setDobOfInsured(source.readString());
		setAddressOfInsured(source.readString());
		setPinOfInsured(source.readString());
		setOffice(source.readString());
		setResidence(source.readString());
		setMobile(source.readString());
		setEmailOfInsured(source.readString());
		
	}

	public PolicyHolderDetailsType() {
		policyNo = "";
		coverNoteNo = "";
		fromDate = "";
		toDate = "";
		nameOfInsured = "";
		dobOfInsured = "";
		addressOfInsured = "";
		pinOfInsured = "";
		office = "";
		residence = "";
		mobile = "";
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


    public String getEmailOfInsured() {
        return emailOfInsured;
    }

    public void setEmailOfInsured(String value) {
        this.emailOfInsured = value;
    }
    
    

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDobOfInsured() {
		return dobOfInsured;
	}

	public void setDobOfInsured(String dobOfInsured) {
		this.dobOfInsured = dobOfInsured;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getPolicyNo());
		dest.writeString(getCoverNoteNo());
		dest.writeString(getFromDate());
		dest.writeString(getToDate());
		dest.writeString(getNameOfInsured());
		dest.writeString(getDobOfInsured());
		dest.writeString(getAddressOfInsured());
		dest.writeString(getPinOfInsured());
		dest.writeString(getOffice());
		dest.writeString(getResidence());
		dest.writeString(getMobile());
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
		case 2: return nameOfInsured;
		case 3: return dobOfInsured;
		case 4: return addressOfInsured;
		case 5: return pinOfInsured;
		case 6: return emailOfInsured;
		default: throw new IllegalArgumentException("");
		}
	}

	@Override
	public int getPropertyCount() {
		return 7;
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
			info.setName("NameOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 3:
			info.setName("dobOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 4:
			info.setName("addressOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 5:
			info.setName("pinOfInsured");
			info.setType(PropertyInfo.STRING_CLASS);
			break;
		case 6:
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
			this.setNameOfInsured(value.toString());
			break;
		case 3:
			this.setDobOfInsured(value.toString());
			break;
		case 4:
			this.setAddressOfInsured(value.toString());
			break;
		case 5:
			this.setPinOfInsured(value.toString());
			break;
		case 6:
			this.setEmailOfInsured(value.toString());
			break;
		default:
			throw new IllegalArgumentException();
		
	}
	}

}
