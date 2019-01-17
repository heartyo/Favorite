package com.android.lancherproject.util;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {

    private ITimerListener mTimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mTimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mTimerListener != null) {
            mTimerListener.onTimer();
        }
    }
}
