package com.android.common_base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.common_base.base.mvp.BasePresenter;
import com.android.common_base.base.mvp.IBaseView;
@SuppressWarnings("unchecked")
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActiviy implements IBaseView {

    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        initView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    protected abstract P createPresenter();
    protected abstract void initView();
}
