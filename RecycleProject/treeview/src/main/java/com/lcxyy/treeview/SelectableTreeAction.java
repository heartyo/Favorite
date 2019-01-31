package com.lcxyy.treeview;

import java.util.List;

public interface SelectableTreeAction extends BaseTreeAction {

    void selectNode(TreeNode treeNode);

    void sdeSelectNode(TreeNode treeNode);

    void selectAll();

    void deSelectAll();

    List<TreeNode> getSelectedNodes();

}
