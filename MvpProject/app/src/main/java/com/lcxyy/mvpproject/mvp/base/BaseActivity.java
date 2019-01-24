package com.lcxyy.mvpproject.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lcxyy.mvpproject.mvp.BaseModel;
import com.lcxyy.mvpproject.mvp.BaseMvpActivity;
import com.lcxyy.mvpproject.mvp.BaseMvpPresenter;


public abstract class BaseActivity<P extends BaseMvpPresenter,M extends BaseModel> extends BaseMvpActivity<P,M> {
    protected String TAG = this.getClass().getSimpleName();
    protected BaseActivity mActivity;
    private InputMethodManager imm;

    protected abstract int getContentViewId();

    protected void initViews() {
    }

    protected void initData() {
    }



    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public Activity getAc() {
        return this;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        initViews();
        initData();
    }


    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }



    //隐藏键盘
    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }


}
