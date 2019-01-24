package com.lcxyy.mvpproject.mvp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BaseMvpActivity<P extends BaseMvpPresenter, M extends BaseModel> extends AppCompatActivity implements IBaseView {
    public P mPresenter;
    public M mModel;


//    public abstract P createPresenter();
    protected abstract int getContentViewId();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPresenter = createPresenter();
        if (mPresenter == null) {
            mPresenter = CreateUtil.getT(this, 0);
        }
        if (mModel == null) {
            mModel = CreateUtil.getT(this, 1);
        }
        if (mPresenter != null) {
            mPresenter.attachView(this,mModel);
        }

        setContentView(getContentViewId());

    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();

    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findView(@NonNull View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }
}
