package com.example.mypanchayat.Models;

public class ComplaintsModel {

    private String userId, complaintId ,stateName, districtName, talukaName, villageName, wardName, about, description;

    public ComplaintsModel(String userId, String stateName, String districtName, String talukaName, String villageName, String wardName, String about, String description) {
        this.userId = userId;
        this.stateName = stateName;
        this.districtName = districtName;
        this.talukaName = talukaName;
        this.villageName = villageName;
        this.wardName = wardName;
        this.about = about;
        this.description = description;
    }

    public ComplaintsModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
