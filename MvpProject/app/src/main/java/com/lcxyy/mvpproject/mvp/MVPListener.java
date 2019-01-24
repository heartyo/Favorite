package com.lcxyy.mvpproject.mvp;

public interface MVPListener<E> {
    void onSuccess(E s);
    void  onError();
}
