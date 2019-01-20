package com.lcxyy.mvpproject.mvp;

import android.app.Activity;

public interface IBaseView {
    Activity getAc();

    void showLoading();

    void hideLoading();

    void showToast(String msg);


}
