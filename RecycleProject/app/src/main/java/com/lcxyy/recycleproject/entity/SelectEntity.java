package com.lcxyy.recycleproject.entity;

public class SelectEntity {

    private String info;
    private String type;
    private boolean isSelected =false;

    public SelectEntity(String info, String type) {
        this.info = info;
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
