package com.lcxyy.treeview;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private int level;

    private Object value;

    private TreeNode parent;

    private List<TreeNode> children;

    private int index;

    private boolean expanded;

    private boolean selected;

    private boolean itemClickEnabled = true;

    public TreeNode(Object value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static TreeNode root() {
        TreeNode treeNode = new TreeNode(null);
        return treeNode;
    }

    public void addChild(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        children.add(treeNode);
        treeNode.setIndex(getChildren().size());
        treeNode.setParent(this);
    }

    public void removeChild(TreeNode treeNode) {
        if (treeNode == null || getChildren().size() < 1) {
            return;
        }
        if (getChildren().indexOf(treeNode) != -1) {
            getChildren().remove(treeNode);
        }
    }

    public boolean isLastChild() {
        if (parent == null) {
            return false;
        }
        List<TreeNode> children = parent.getChildren();
        return children.size() > 0 && children.indexOf(this) == children.size() - 1;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public List<TreeNode> getSelectChildren() {
        List<TreeNode> selectChildren = new ArrayList<>();

        for (TreeNode treeNode :
                getChildren()) {
            if (treeNode.isSelected()) {
                selectChildren.add(treeNode);
            }
        }
        return selectChildren;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean hasChild() {
        return children.size() > 0;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isItemClickEnabled() {
        return itemClickEnabled;
    }

    public void setItemClickEnabled(boolean itemClickEnabled) {
        this.itemClickEnabled = itemClickEnabled;
    }

    public String getId() {
        return getLevel() + "," + getIndex();
    }


}
