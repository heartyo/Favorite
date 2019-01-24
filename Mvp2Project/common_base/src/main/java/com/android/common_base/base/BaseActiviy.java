package com.android.common_base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActiviy extends FragmentActivity {


    protected Context mContext;
    private Unbinder mUnbinder;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected abstract int getLayoutId();

}
