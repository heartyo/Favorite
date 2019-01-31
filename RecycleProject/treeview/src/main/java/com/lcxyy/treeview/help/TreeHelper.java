package com.lcxyy.treeview.help;

import com.lcxyy.treeview.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeHelper {

    public static void expandAll(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        expandNode(treeNode,true);
    }

    private static List<TreeNode> expandNode(TreeNode treeNode, boolean isIncludeChild) {
        List<TreeNode> expandChildren = new ArrayList<>();
        if (treeNode == null) {
            return expandChildren;
        }

        treeNode.setExpanded(true);
        if (!treeNode.hasChild()) {
            return expandChildren;
        }

        for (TreeNode child :
                treeNode.getChildren()) {

            expandChildren.add(child);
            if (isIncludeChild && child.isExpanded()) {
                expandChildren.addAll(expandNode(child, isIncludeChild));
            }
        }

        return expandChildren;
    }


    public static void expandLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        for (TreeNode child :
                root.getChildren()) {
            if (child.getLevel() == level) {
                expandNode(child, false);
            } else {
                expandLevel(child, level);
            }

        }
    }

    public static void collapseAll(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        for (TreeNode child :
                treeNode.getChildren()) {
            performCollapseNode(child, true);
        }
    }

    public static List<TreeNode> collapseNode(TreeNode node, boolean includeChild) {
        List<TreeNode> treeNodes = performCollapseNode(node, includeChild);
        node.setExpanded(false);
        return treeNodes;
    }

    private static List<TreeNode> performCollapseNode(TreeNode node, boolean includeChild) {
        List<TreeNode> collapseChildren = new ArrayList<>();
        if (node == null) {
            return collapseChildren;
        }
        if (includeChild) {
            node.setExpanded(false);
        }

        for (TreeNode child :
                node.getChildren()) {
            collapseChildren.add(child);

            if (child.isExpanded()) {
                collapseChildren.addAll(performCollapseNode(child, includeChild));
            } else if (includeChild) {
                performCollapseNodeInner(child);
            }
        }

        return collapseChildren;

    }

    private static void performCollapseNodeInner(TreeNode node) {
        if (node == null) {
            return;
        }
        node.setExpanded(false);
        for (TreeNode child :
                node.getChildren()) {
            performCollapseNodeInner(child);
        }
    }


    public static void collapseLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        for (TreeNode child :
                root.getChildren()) {
            if (child.getLevel() == level) {
                collapseNode(child, false);
            } else {
                collapseLevel(child, level);
            }
        }
    }


    public static List<TreeNode> getAllNodes(TreeNode root) {
        List<TreeNode> allNotes = new ArrayList<>();
        fillNodeList(allNotes, root);
        allNotes.remove(root);
        return allNotes;
    }

    private static void fillNodeList(List<TreeNode> allNotes, TreeNode treeNode) {
        allNotes.add(treeNode);

        if (treeNode.hasChild()) {
            for (TreeNode child :
                    treeNode.getChildren()) {
                fillNodeList(allNotes, child);
            }
        }
    }

    public static List<TreeNode> selectNodeAndChild(TreeNode treeNode, boolean select) {
        List<TreeNode> expandChildren = new ArrayList<>();
        if (treeNode == null) {
            return expandChildren;
        }
        treeNode.setSelected(select);
        if (!treeNode.hasChild()) {
            return expandChildren;
        }
        if (treeNode.isExpanded()) {
            for (TreeNode child :
                    treeNode.getChildren()) {
                expandChildren.add(child);

                if (child.isExpanded()) {
                    expandChildren.addAll(selectNodeAndChild(child, select));
                } else {
                    selectNodeInner(child, select);
                }
            }
        } else {
            selectNodeInner(treeNode, select);
        }
        return expandChildren;
    }

    private static void selectNodeInner(TreeNode node, boolean select) {
        if (node == null) {
            return;
        }
        node.setSelected(select);
        if (node.hasChild()) {
            for (TreeNode child :
                    node.getChildren()) {
                selectNodeInner(child, select);
            }
        }
    }
}
