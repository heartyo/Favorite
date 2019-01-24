package com.android.mvp2project;

import com.android.common_base.base.mvp.IBaseModel;
import com.android.common_base.base.mvp.IBaseView;
import com.android.mvp2project.callback.JsonCallBack;

class MainContract {
    interface Module extends IBaseModel {
        void update(JsonCallBack callBack);
    }

    interface View extends IBaseView {
        void needUpdate(String appInfo);
    }

    interface Presenter {
        void checkUpdate();

    }

}
