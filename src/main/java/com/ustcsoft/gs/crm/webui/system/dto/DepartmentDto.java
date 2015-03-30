package com.ustcsoft.gs.crm.webui.system.dto;

/**
 * 
 * @author tangyunpeng and maxue
 * 
 */
public class DepartmentDto {

    private int departmentID = 0;
    private String departmentName = null;
    private int fatherDepartmentID = 0;
    private int departmentManager = 0;
    private String serialNumber = null;
    private String departmentPhone = null;
    private String createTime = null;
    private String departmentDescription = null;
    private int depth = 0;

    /**
     * 
     * @return the fatherDepartmentID
     */
    public int getFatherDepartmentID() {
        return fatherDepartmentID;
    }

    /**
     * 
     * @param fatherDepartmentID
     *            the fatherDepartmentID to set
     */
    public void setFatherDepartmentID(int fatherDepartmentID) {
        this.fatherDepartmentID = fatherDepartmentID;
    }

    /**
     * 
     * @return the departmentManager
     */
    public int getDepartmentManager() {
        return departmentManager;
    }

    /**
     * 
     * @param departmentManager
     *            the departmentManager to set
     */
    public void setDepartmentManager(int departmentManager) {
        this.departmentManager = departmentManager;
    }

    /**
     * 
     * @return the departmentPhone
     */
    public String getDepartmentPhone() {
        return departmentPhone;
    }

    /**
     * 
     * @param departmentPhone
     *            the departmentPhone to set
     */
    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    /**
     * 
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(String createTime) {
        if (!createTime.trim().isEmpty()) {
            this.createTime = createTime;
        }
    }

    /**
     * 
     * @return the departmentDescription
     */
    public String getDepartmentDescription() {
        return departmentDescription;
    }

    /**
     * 
     * @param departmentDescription
     *            the departmentDescription to set
     */
    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    /**
     * @return the departmentID
     */
    public int getDepartmentID() {
        return departmentID;
    }

    /**
     * @param departmentID
     *            the departmentID to set
     */
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     *            the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth
     *            the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * 
     * @return serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 
     * @param serialNumber
     *            the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
