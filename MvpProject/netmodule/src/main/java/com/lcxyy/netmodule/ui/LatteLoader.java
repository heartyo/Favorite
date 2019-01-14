package com.lcxyy.netmodule.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.lcxyy.netmodule.R;
import com.lcxyy.netmodule.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class LatteLoader {

    private static final int LOAD_SIZE_SCALE = 8;
    private static final int LOAD_OFFSET_SCALE = 10;

    private static final String DEFAULT_LOADER = LoadStyle.BallClipRotatePulseIndicator.name();
    private static final ArrayList<AppCompatDialog> LOADS = new ArrayList<>();

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        int screenWidth = DimenUtil.getScreenWidth(context);
        int screenHeight = DimenUtil.getScreenHeight(context);
        final Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = screenWidth / LOAD_SIZE_SCALE;
            lp.height = screenHeight / LOAD_SIZE_SCALE;
            lp.height = lp.height + screenHeight / LOAD_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }
    public static void showLoading(Context context,Enum<LoadStyle> type) {
        showLoading(context, type.name());
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog :
                LOADS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
