package com.android.mvp2project;

import com.android.common_base.base.mvp.BasePresenter;
import com.android.mvp2project.callback.JsonCallBack;

public class MainPresenter extends BasePresenter<MainContract.Module,MainContract.View> implements MainContract.Presenter{
    @Override
    protected MainContract.Module createModel() {
        return new MainModel();
    }

    @Override
    public void checkUpdate() {
        if (isViewAttached()) {
            getModule().update(new JsonCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    getView().needUpdate(s);
                }
            });
        }
    }
}
