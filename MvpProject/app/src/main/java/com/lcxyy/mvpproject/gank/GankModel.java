package com.lcxyy.mvpproject.gank;

import android.app.Activity;

import com.lcxyy.mvpproject.mvp.MVPListener;
import com.lcxyy.netmodule.RestClient;
import com.lcxyy.netmodule.callback.IError;
import com.lcxyy.netmodule.callback.IFailure;
import com.lcxyy.netmodule.callback.ISuccess;

public class GankModel implements GankContract.GankModel {



    @Override
    public void requestJoke(final Activity activity, String pNum, String pSize, final MVPListener pMVPListener) {
        RestClient.builder().url("https://gank.io/api/today")
                .loader(activity)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       pMVPListener.onSuccess(response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build().get();
    }
}
