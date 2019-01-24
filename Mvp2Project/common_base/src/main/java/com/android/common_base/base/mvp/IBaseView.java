package com.android.common_base.base.mvp;

import android.content.Context;

public interface IBaseView {

    void showLoading();

    void dismissLoading();

    void onEmpty();


    void onError(Object tag, String errorMsg);

    Context getContext();


}
