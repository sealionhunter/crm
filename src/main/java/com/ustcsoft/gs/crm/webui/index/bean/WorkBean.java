package com.ustcsoft.gs.crm.webui.index.bean;

import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;

public class WorkBean extends WorkDto {

    private String customerName = null;
    private String priorityName = null;
    private String workTypeName = null;
    private String completionName = null;

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
        this.customerName = customerName;
    }

    /**
     * @return the priorityName
     */
    public String getPriorityName() {
        return priorityName;
    }

    /**
     * @param priorityName
     *            the priorityName to set
     */
    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    /**
     * @return the workTypeName
     */
    public String getWorkTypeName() {
        return workTypeName;
    }

    /**
     * @param workTypeName
     *            the workTypeName to set
     */
    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    /**
     * @return the completionName
     */
    public String getCompletionName() {
        return completionName;
    }

    /**
     * @param completionName
     *            the completionName to set
     */
    public void setCompletionName(String completionName) {
        this.completionName = completionName;
    }
}
