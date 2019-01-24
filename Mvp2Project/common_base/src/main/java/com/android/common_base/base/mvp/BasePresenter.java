package com.android.common_base.base.mvp;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@SuppressWarnings("unchecked")
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {

    private V mProxyView;
    private M mModule;
    private WeakReference<V> weakReference;

    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new MvpViewHandler(weakReference.get()));
        if (mModule == null) {
            this.mModule = createModel();
        }

    }

    public void detachView() {
        this.mModule = null;
        if (isViewAttached()) {
            weakReference.clear();
            weakReference = null;
        }
    }

    protected boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }


    public V getView() {
        return mProxyView;
    }

    public M getModule() {
        return mModule;
    }

    public Context getContex() {
        return getView().getContext();
    }

    public void showLoading() {
        getView().showLoading();
    }

    public void dismissLoading() {
        getView().dismissLoading();
    }

    protected abstract M createModel();

    private class MvpViewHandler implements InvocationHandler {

        private IBaseView mvpView;

        public MvpViewHandler(IBaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            if (isViewAttached()) {
                return method.invoke(mvpView, objects);
            }
            return null;
        }
    }
}
