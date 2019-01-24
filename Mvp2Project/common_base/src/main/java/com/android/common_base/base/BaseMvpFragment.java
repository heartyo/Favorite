package com.android.common_base.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.common_base.base.mvp.BasePresenter;
import com.android.common_base.base.mvp.IBaseView;

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IBaseView{
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
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
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
    protected abstract P createPresenter();
}
