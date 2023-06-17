package com.example.mypanchayat.Models;

public class Requests {

    private String requestCategory, requestId,userId, stateName, districtName, talukaName, villageName, wardName, userName, userPhoneNo, userAadhaarNo;

    public Requests(String requestCategory, String userId, String stateName, String districtName, String talukaName, String villageName, String wardName, String userName, String userPhoneNo, String userAadhaarNo) {
        this.requestCategory = requestCategory;
        this.userId = userId;
        this.stateName = stateName;
        this.districtName = districtName;
        this.talukaName = talukaName;
        this.villageName = villageName;
        this.wardName = wardName;
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.userAadhaarNo = userAadhaarNo;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Requests() {

    }

    public String getRequestCategory() {
        return requestCategory;
    }

    public void setRequestCategory(String requestCategory) {
        this.requestCategory = requestCategory;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getTalukaName() {
        return talukaName;
    }

    public void setTalukaName(String talukaName) {
        this.talukaName = talukaName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserAadhaarNo() {
        return userAadhaarNo;
    }

    public void setUserAadhaarNo(String userAadhaarNo) {
        this.userAadhaarNo = userAadhaarNo;
    }
}
