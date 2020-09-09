package com.test.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.View;

import com.test.annotation.BindPath;
import com.test.common.LiveDataBus;
import com.test.router.Router;

@BindPath("login/login")
public class LoginActivity extends AppCompatActivity {

    private MutableLiveData<String> liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        liveData = LiveDataBus.getInstance().with("lisi", String.class);

    }

    public void jumpActivity(View view){
//        Router.getInstance().jumpActivity("member", null);
        liveData.postValue("1111222");
    }
}
