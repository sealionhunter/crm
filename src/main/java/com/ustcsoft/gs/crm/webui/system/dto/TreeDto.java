package com.ustcsoft.gs.crm.webui.system.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeDto {
    private int treeID;
    private String flag;
    private int id;
    private String text;
    private boolean leaf;
    private boolean expanded;
    private boolean checked;
    private int fatherID;
    private List<TreeDto> children = new ArrayList<TreeDto>();

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the fatherID
     */
    public int getFatherID() {
        return fatherID;
    }

    /**
     * @param fatherID
     *            the fatherID to set
     */
    public void setFatherID(int fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the leaf
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * @param leaf
     *            the leaf to set
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * @return the children
     */

    /**
     * @return the expanded
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * @param expanded
     *            the expanded to set
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * @return the children
     */
    public List<TreeDto> getChildren() {
        return children;
    }

    /**
     * @param children
     *            the children to set
     */
    public void setChildren(List<TreeDto> children) {
        this.children = children;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked
     *            the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void addChild(TreeDto tree) {
        children.add(tree);
    }

    /**
     * @return the treeID
     */
    public int getTreeID() {
        return treeID;
    }

    /**
     * @param treeID
     *            the treeID to set
     */
    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }
}
