package com.android.mvp2project.callback;

import com.android.common_base.base.mvp.MvpCallback;

public abstract class JsonCallBack<T> extends MvpCallback {
  public   abstract void onSuccess(T t);
}
