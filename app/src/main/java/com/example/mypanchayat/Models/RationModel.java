package com.example.mypanchayat.Models;

public class RationModel {

    private String rationId, userId, stateName, districtName, talukaName, villageName, wardName, itemName, rationQuantity, rationPic;
    private long rationUploadTime, rationValidityTime;

    public RationModel() {

    }

    public RationModel(String userId, String stateName, String districtName, String talukaName, String villageName, String wardName, String itemName, String rationQuantity, long rationUploadTime, long rationValidityTime, String rationPic) {
        this.userId = userId;
        this.stateName = stateName;
        this.districtName = districtName;
        this.talukaName = talukaName;
        this.villageName = villageName;
        this.wardName = wardName;
        this.itemName = itemName;
        this.rationQuantity = rationQuantity;
        this.rationUploadTime = rationUploadTime;
        this.rationValidityTime = rationValidityTime;
        this.rationPic = rationPic;
    }

    public String getRationPic() {
        return rationPic;
    }

    public void setRationPic(String rationPic) {
        this.rationPic = rationPic;
    }

    public String getRationId() {
        return rationId;
    }

    public void setRationId(String rationId) {
        this.rationId = rationId;
    }

    public long getRationValidityTime() {
        return rationValidityTime;
    }

    public void setRationValidityTime(long rationValidityTime) {
        this.rationValidityTime = rationValidityTime;
    }

    public long getRationUploadTime() {
        return rationUploadTime;
    }

    public void setRationUploadTime(long rationUploadTime) {
        this.rationUploadTime = rationUploadTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRationQuantity() {
        return rationQuantity;
    }

    public void setRationQuantity(String rationQuantity) {
        this.rationQuantity = rationQuantity;
    }
}
