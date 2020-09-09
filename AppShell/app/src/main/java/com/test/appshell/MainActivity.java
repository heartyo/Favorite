package com.test.appshell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.test.annotation.BindPath;
import com.test.common.LiveDataBus;
import com.test.router.Router;

@BindPath("main/main")
public class MainActivity extends AppCompatActivity {
    MutableLiveData<String> liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liveData = LiveDataBus.getInstance().with("lisi", String.class);

        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("---->", s);
            }
        });
    }

    public void jumpActivity(View view) {
        Router.getInstance().jumpActivity("login/login", null);
//        liveData.postValue("1111222");

    }
}
