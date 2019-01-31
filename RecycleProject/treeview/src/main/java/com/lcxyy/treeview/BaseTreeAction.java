package com.lcxyy.treeview;

import java.util.List;

//定义功能
public interface BaseTreeAction {

    void expandAll();

    void expandNode(TreeNode treeNode);

    void expandLevel(int level);

    void collapseAll();

    void collapseNode(TreeNode treeNode);

    void collapseLevel();

    void toggleNode(TreeNode treeNode);

    void deleteNode(TreeNode treeNode);

    void addNode(TreeNode parent, TreeNode treeNode);

    List<TreeNode> getAllNodes();



}
