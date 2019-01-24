package com.android.mvp2project;

import android.widget.TextView;

import com.android.common_base.base.BaseMvpActivity;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View{


    private TextView mTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        presenter.checkUpdate();
        mTv = ((TextView) findViewById(R.id.tv));
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void needUpdate(String appInfo) {
        mTv.setText(appInfo);
    }
}
