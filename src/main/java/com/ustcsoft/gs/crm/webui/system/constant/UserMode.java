package com.ustcsoft.gs.crm.webui.system.constant;

/**
 * 
 * @author maxue
 * 
 */
public enum UserMode {
    GROUP(0), USER(1), SYSTEM(2);
    private int i;

    /**
     * 
     * @param i
     */
    private UserMode(int i) {
        setI(i);
    }

    /**
     * 
     * @return i
     */
    public int getI() {
        return i;
    }

    /**
     * 
     * @param i
     */
    public void setI(int i) {
        this.i = i;
    }
}
