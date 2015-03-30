package com.ustcsoft.gs.crm.webui.count.dto;

/**
 * 
 * @author jiaxu
 * 
 */
public class SalesCountResult {

    private int count;

    private int rightCount;

    private int leftCount;

    private String state;

    private int allCount;

    /**
     * 
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 
     * @return rightCount
     */
    public int getRightCount() {
        return rightCount;
    }

    /**
     * 
     * @param rightCount
     */
    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    /**
     * 
     * @return leftCount
     */
    public int getLeftCount() {
        return leftCount;
    }

    /**
     * 
     * @param leftCount
     */
    public void setLeftCount(int leftCount) {
        this.leftCount = -leftCount;
    }

    /**
     * 
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return allCount
     */
    public int getAllCount() {
        return allCount;
    }

    /**
     * 
     * @param allCount
     */
    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }
}
