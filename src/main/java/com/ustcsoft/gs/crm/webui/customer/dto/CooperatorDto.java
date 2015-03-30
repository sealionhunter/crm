package com.ustcsoft.gs.crm.webui.customer.dto;

import java.util.Set;

public class CooperatorDto {
    private int cooperatorID = 0;
    private String cooperatorName;
    private String cooperatorCity;
    private String cooperatorIndustry;
    private String cooperatorScale;
    private String cooperatorType;
    private String cooperatorTelephone;
    private String cooperatorEmail;
    private String cooperatorFax;
    private String cooperatorWebsite;
    private String cooperatorAddress;
    private String cooperatorRemark;
    private String cooperatorDetail;
    private Boolean isAbolished;
    private Set<CooperationProjectDto> cooperationProjectDto;

    public int getCooperatorID() {
        return cooperatorID;
    }

    public void setCooperatorID(int cooperatorID) {
        this.cooperatorID = cooperatorID;
    }

    public String getCooperatorName() {
        return cooperatorName;
    }

    public void setCooperatorName(String cooperatorName) {
        if (cooperatorName != null) {
            this.cooperatorName = cooperatorName.trim();
        }

    }

    public String getCooperatorCity() {
        return cooperatorCity;
    }

    public void setCooperatorCity(String cooperatorCity) {
        this.cooperatorCity = cooperatorCity.trim();
    }

    public String getCooperatorIndustry() {
        return cooperatorIndustry;
    }

    public void setCooperatorIndustry(String cooperatorIndustry) {
        this.cooperatorIndustry = cooperatorIndustry.trim();
    }

    public String getCooperatorScale() {
        return cooperatorScale;
    }

    public void setCooperatorScale(String cooperatorScale) {
        this.cooperatorScale = cooperatorScale.trim();
    }

    public String getCooperatorType() {
        return cooperatorType;
    }

    public void setCooperatorType(String cooperatorType) {
        this.cooperatorType = cooperatorType.trim();
    }

    public String getCooperatorTelephone() {
        return cooperatorTelephone;
    }

    public void setCooperatorTelephone(String cooperatorTelephone) {
        this.cooperatorTelephone = cooperatorTelephone.trim();
    }

    public String getCooperatorEmail() {
        return cooperatorEmail;
    }

    public void setCooperatorEmail(String cooperatorEmail) {
        this.cooperatorEmail = cooperatorEmail.trim();
    }

    public String getCooperatorFax() {
        return cooperatorFax;
    }

    public void setCooperatorFax(String cooperatorFax) {
        this.cooperatorFax = cooperatorFax.trim();
    }

    public String getCooperatorWebsite() {
        return cooperatorWebsite;
    }

    public void setCooperatorWebsite(String cooperatorWebsite) {
        this.cooperatorWebsite = cooperatorWebsite.trim();
    }

    public String getCooperatorAddress() {
        return cooperatorAddress;
    }

    public void setCooperatorAddress(String cooperatorAddress) {
        this.cooperatorAddress = cooperatorAddress.trim();
    }

    public String getCooperatorRemark() {
        return cooperatorRemark;
    }

    public void setCooperatorRemark(String cooperatorRemark) {
        this.cooperatorRemark = cooperatorRemark.trim();
    }

    public String getCooperatorDetail() {
        return cooperatorDetail;
    }

    public void setCooperatorDetail(String cooperatorDetail) {
        this.cooperatorDetail = cooperatorDetail.trim();
    }

    public Boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    public Set<CooperationProjectDto> getCooperationProjectDto() {
        return cooperationProjectDto;
    }

    public void setCooperationProjectDto(Set<CooperationProjectDto> cooperationProjectDto) {
        this.cooperationProjectDto = cooperationProjectDto;
    }

}
