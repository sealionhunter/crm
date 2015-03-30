package com.ustcsoft.gs.crm.webui.system.dto;

public class ActionGroupDto {
    private int actionGroupID;
    private int actionID;
    private int groupID;

    public int getActionGroupID() {
        return actionGroupID;
    }

    public void setActionGroupID(int actionGroupID) {
        this.actionGroupID = actionGroupID;
    }

    public int getActionID() {
        return actionID;
    }

    public void setActionID(int actionID) {
        this.actionID = actionID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public ActionGroupDto() {
    }

    public ActionGroupDto(int actionGroupID, int actionID, int groupID) {
        super();
        this.actionGroupID = actionGroupID;
        this.actionID = actionID;
        this.groupID = groupID;
    }
}
