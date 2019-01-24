package com.lcxyy.mvpproject.mvp;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BaseMvpPresenter<V extends IBaseView,M extends BaseModel> {
    // 防止 Activity 不走 onDestory() 方法，所以采用弱引用来防止内存泄漏
    private WeakReference<V> weakReference;
    private V mProxyView;
    public M mModel;

    public V getView() {
        return mProxyView;
    }

    @SuppressWarnings("unchecked")
    public void attachView(V v,M model) {
        weakReference = new WeakReference<>(v);
        this.mModel = model;
        mProxyView = (V) Proxy.newProxyInstance(v.getClass().getClassLoader(), v.getClass().getInterfaces(), new MvpViewHandler(weakReference.get()));
    }


    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    public void detachView() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    private class MvpViewHandler implements InvocationHandler {
        private IBaseView mvpBaseView;

        MvpViewHandler(IBaseView mvpView) {
            this.mvpBaseView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isViewAttached()) { //如果V层没被销毁, 执行V层的方法.
                return method.invoke(mvpBaseView, args);
            }
            //P层不需要关注V层的返回值
            return null;
        }
    }
}
