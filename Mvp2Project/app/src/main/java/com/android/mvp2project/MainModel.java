package com.android.mvp2project;

import android.os.Handler;

import com.android.mvp2project.callback.JsonCallBack;

@SuppressWarnings("unchecked")
public class MainModel implements MainContract.Module {
    @Override
    public void update(final JsonCallBack callBack) {

        //请求接口
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess("aaaaa");
            }
        }, 2000);
    }
}
