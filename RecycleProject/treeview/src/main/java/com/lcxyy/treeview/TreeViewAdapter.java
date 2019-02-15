package com.lcxyy.treeview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TreeViewAdapter extends RecyclerView.Adapter {


    private Context context;

    private TreeNode root;

    private List<TreeNode> expandedNodeList;

    private BaseNodeViewFactory baseNodeViewFactory;

    private View EMPTY_PARAMETER;

    private TreeView treeView;

    public TreeViewAdapter(Context context, TreeNode root, BaseNodeViewFactory baseNodeViewFactory) {
        this.context = context;
        this.root = root;
        this.baseNodeViewFactory = baseNodeViewFactory;
        this.expandedNodeList = new ArrayList<>();
        this.EMPTY_PARAMETER = new View(context);
        buildExpandedNodeList();
    }

    private void buildExpandedNodeList() {
        expandedNodeList.clear();
        for (TreeNode child : root.getChildren()) {
            insertNode(expandedNodeList, child);
        }
    }

    private void insertNode(List<TreeNode> nodeList, TreeNode node) {
        nodeList.clear();
        if (!node.hasChild()) {
            return;
        }
        if (node.isExpanded()) {
            for (TreeNode child :
                    node.getChildren()) {
                insertNode(nodeList, child);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return expandedNodeList.get(position).getLevel();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int level) {

        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
