package com.android.lancherproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.android.lancherproject.util.BaseTimerTask;
import com.android.lancherproject.util.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import qiu.niorgai.StatusBarCompat;

import static com.android.lancherproject.R.id.tv_launcher_timer;

public class SpashActivity extends AppCompatActivity implements ITimerListener {

    private Timer mTimer;
    private AppCompatTextView mTvTimer;
    private int mCount = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        StatusBarCompat.translucentStatusBar(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mTvTimer = findViewById(tv_launcher_timer);
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);

    }

    @Override
    public void onTimer() {
        if (mTvTimer != null) {
          runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                  mCount--;
                  if (mCount < 0) {
                      if (mTimer != null) {
                          mTimer.cancel();
                          mTimer = null;
                      }
                      startActivity(new Intent(SpashActivity.this,GuideActivity2.class));
                  }
              }
          });
        }
    }
}
