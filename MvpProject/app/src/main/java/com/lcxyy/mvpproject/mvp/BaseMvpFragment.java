package com.lcxyy.mvpproject.mvp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseMvpFragment<T extends BaseMvpPresenter, M extends BaseModel> extends Fragment implements IBaseView {
    public M mModel;
    public T mPresenter;
    private View mRootView;
    protected BaseMvpActivity mActivity;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), null);
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseMvpActivity) getActivity();
    }

    protected abstract int getLayoutId();


    protected View getRootView() {
        return mRootView;
    }

    protected View getChildView(int viewId) {
        return mRootView.findViewById(viewId);
    }



    public abstract T createPresenter();


    @Override
    public void showToast(String msg) {

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
