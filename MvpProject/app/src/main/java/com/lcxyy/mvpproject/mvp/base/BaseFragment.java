package com.lcxyy.mvpproject.mvp.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcxyy.mvpproject.mvp.BaseModel;
import com.lcxyy.mvpproject.mvp.BaseMvpFragment;
import com.lcxyy.mvpproject.mvp.BaseMvpPresenter;

public abstract class BaseFragment<P extends BaseMvpPresenter, M extends BaseModel> extends BaseMvpFragment<P,M> {
    protected BaseActivity mActivity;
    private boolean isVisible = false;                  //是否可见状态
    private boolean isPrepared = false;                 //标志位，View已经初始化完成。
    protected boolean mIsImmersion = false;
    //是否加载完成
    public String TAG = getClass().getSimpleName();

    protected abstract int getLayoutId();

    protected void initView(View view, Bundle savedInstanceState) {
    }

    protected abstract void initData();

    protected boolean isLazyLoad() {
        return true;
    }


    protected void setStatuBar() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) getActivity();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initView(getRootView(), savedInstanceState);
        if (isLazyLoad()) {
            isPrepared = true;
            mIsImmersion = true;
            lazyLoad();
        }
        setStatuBar();
        return getRootView();
    }


    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
        if (isPrepared && isVisible) {
            isPrepared = false;
            initData();
        }
    }


}
