package com.lcxyy.mvpproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lcxyy.netmodule.RestClient;
import com.lcxyy.netmodule.callback.IError;
import com.lcxyy.netmodule.callback.IFailure;
import com.lcxyy.netmodule.callback.ISuccess;

public class MainActivity extends AppCompatActivity {
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        RestClient.builder().url("https://gank.io/api/today")
                .loader(activity)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build().get();
    }
}
