package com.ustcsoft.gs.crm.webui.customer.dto;

public class CustomerDto {

    private int customerID = 0;
    private String customerName = null;
    private int holder = 0;
    private String scale = null;
    private String customerType = null;
    private String industry = null;
    private String customerStatement = null;
    private String valueEvaluate = null;
    private String fee = null;
    private String customerAddr = null;
    private String earning = null;
    private Boolean isAbolished = false;
    private String descriptions = null;
    private String business1 = null;
    private String business2 = null;
    private String business3 = null;
    private String business4 = null;
    private String createTime = null;
    private String updateTime = null;

    private String businessUnit = null;
    private int number = 0;
    private int unicomNumber = 0;
    
    private String attachPath = null;
    
    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUnicomNumber() {
        return unicomNumber;
    }

    public void setUnicomNumber(int unicomNumber) {
        this.unicomNumber = unicomNumber;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName.trim();
    }

    /**
     * @return the holder
     */
    public int getHolder() {
        return holder;
    }

    /**
     * @param holder
     *            the holder to set
     */
    public void setHolder(int holder) {
        this.holder = holder;
    }

    /**
     * @return the scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * @param scale
     *            the scale to set
     */
    public void setScale(String scale) {
        this.scale = scale.trim();
    }

    /**
     * @return the customerType
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * @param customerType
     *            the customerType to set
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType.trim();
    }

    /**
     * @return the industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry
     *            the industry to set
     */
    public void setIndustry(String industry) {
        this.industry = industry.trim();
    }

    /**
     * @return the customerStatement
     */
    public String getCustomerStatement() {
        return customerStatement;
    }

    /**
     * @param customerStatement
     *            the customerStatement to set
     */
    public void setCustomerStatement(String customerStatement) {
        this.customerStatement = customerStatement.trim();
    }

    /**
     * @return the valueEvaluate
     */
    public String getValueEvaluate() {
        return valueEvaluate;
    }

    /**
     * @param valueEvaluate
     *            the valueEvaluate to set
     */
    public void setValueEvaluate(String valueEvaluate) {
        this.valueEvaluate = valueEvaluate.trim();
    }

    /**
     * @return the fee
     */
    public String getFee() {
        return fee;
    }

    /**
     * @param fee
     *            the fee to set
     */
    public void setFee(String fee) {
        this.fee = fee.trim();
    }

    /**
     * @return the customerAddr
     */
    public String getCustomerAddr() {
        return customerAddr;
    }

    /**
     * @param customerAddr
     *            the customerAddr to set
     */
    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr.trim();
    }

    /**
     * @return the earning
     */
    public String getEarning() {
        return earning;
    }

    /**
     * @param earning
     *            the earning to set
     */
    public void setEarning(String earning) {
        this.earning = earning.trim();
    }

    /**
     * @return the isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * @return the descriptions
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * @param descriptions
     *            the descriptions to set
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions.trim();
    }

    public String getBusiness1() {
        return business1;
    }

    public void setBusiness1(String business1) {
        this.business1 = business1;
    }

    public String getBusiness2() {
        return business2;
    }

    public void setBusiness2(String business2) {
        this.business2 = business2;
    }

    public String getBusiness3() {
        return business3;
    }

    public void setBusiness3(String business3) {
        this.business3 = business3;
    }

    public String getBusiness4() {
        return business4;
    }

    public void setBusiness4(String business4) {
        this.business4 = business4;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }
}
