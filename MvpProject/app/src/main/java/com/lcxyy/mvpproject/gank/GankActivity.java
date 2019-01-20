package com.lcxyy.mvpproject.gank;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lcxyy.mvpproject.R;
import com.lcxyy.mvpproject.mvp.base.BaseActivity;
import com.lcxyy.netmodule.RestClient;
import com.lcxyy.netmodule.callback.IError;
import com.lcxyy.netmodule.callback.IFailure;
import com.lcxyy.netmodule.callback.ISuccess;

public class GankActivity extends BaseActivity<GankPresenter,GankModel> implements GankContract.GankView{
    private Activity activity;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
    }


    public void click(View view) {
        Log.e("ss", String.valueOf(this));
        mPresenter.requestGank("", "");
    }


    @Override
    public void setGank(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }


}
