package com.app.service.entity;

public class ClaimsType {

    protected String claimId;
    protected String claimStatus;
    protected String amountSettled;
    protected String dateOfSettlement;
    protected PolicyHolderDetailsType policyHolderDetails;
    protected VehicleDetailsType vehicleDetails;
    protected AccidentDetailsType accidentDetails;
    protected DriverDetailsType driverDetails;

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String value) {
        this.claimId = value;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String value) {
        this.claimStatus = value;
    }

    public String getAmountSettled() {
        return amountSettled;
    }

    public void setAmountSettled(String value) {
        this.amountSettled = value;
    }

    public String getDateOfSettlement() {
        return dateOfSettlement;
    }

    public void setDateOfSettlement(String value) {
        this.dateOfSettlement = value;
    }

    public PolicyHolderDetailsType getPolicyHolderDetails() {
        return policyHolderDetails;
    }

    public void setPolicyHolderDetails(PolicyHolderDetailsType value) {
        this.policyHolderDetails = value;
    }

    public VehicleDetailsType getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetailsType value) {
        this.vehicleDetails = value;
    }

    public AccidentDetailsType getAccidentDetails() {
        return accidentDetails;
    }

    public void setAccidentDetails(AccidentDetailsType value) {
        this.accidentDetails = value;
    }

    public DriverDetailsType getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetailsType value) {
        this.driverDetails = value;
    }

}
