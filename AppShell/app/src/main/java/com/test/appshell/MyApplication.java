package com.test.appshell;

import com.test.basic.BasicApplication;
import com.test.router.Router;

public class MyApplication extends BasicApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Router.getInstance().init(this);

    }
}
