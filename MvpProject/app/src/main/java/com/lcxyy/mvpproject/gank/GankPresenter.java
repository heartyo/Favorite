package com.lcxyy.mvpproject.gank;

import com.lcxyy.mvpproject.mvp.MVPListener;

public class GankPresenter extends GankContract.GankPresenter {


    @Override
    public void requestGank(String pNum, String pSize) {
        final GankContract.GankView mView = getView();
        if (mView == null) {
            return;
        }
        mView.showLoading();
        mModel.requestJoke(mView.getAc(), pNum, pSize, new MVPListener<String>() {

            @Override
            public void onSuccess(String s) {
                mView.hideLoading();
                mView.setGank(s);
            }

            @Override
            public void onError() {
                mView.hideLoading();


            }
        });

    }
}
