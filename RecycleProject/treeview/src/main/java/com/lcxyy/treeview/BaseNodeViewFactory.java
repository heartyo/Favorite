package com.lcxyy.treeview;

import android.view.View;

public abstract class BaseNodeViewFactory {

    public abstract BaseNodeViewBinder getNodeViewBinder(View view, int level);
}
