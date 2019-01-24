package com.lcxyy.mvpproject.gank;

import android.app.Activity;

import com.lcxyy.mvpproject.mvp.BaseModel;
import com.lcxyy.mvpproject.mvp.BaseMvpPresenter;
import com.lcxyy.mvpproject.mvp.IBaseView;
import com.lcxyy.mvpproject.mvp.MVPListener;

public interface GankContract {

    interface GankView extends IBaseView {
        void setGank(String s);
    }

    interface GankModel extends BaseModel {
        void requestJoke(Activity activity,String pNum, String pSize, MVPListener pMVPListener);

    }

    abstract class GankPresenter extends BaseMvpPresenter<GankView, GankModel> {

        public abstract void requestGank(String pNum, String pSize);
    }
}
