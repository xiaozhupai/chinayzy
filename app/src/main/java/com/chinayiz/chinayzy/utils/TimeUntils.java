package com.chinayiz.chinayzy.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/1/6.
 */

public class TimeUntils {
    private static final int MSG_NUM=5;
    private Handler handler;
    private int time=60;
    private Timer timer;
    public TimeUntils(Handler handler) {
        this.handler = handler;
    }

    public  void RunTimer() {
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg = handler.obtainMessage();
                msg.what=MSG_NUM;
                msg.arg1=time;
                handler.sendMessage(msg);
                if (time==0){//60秒结束
                    time=60;
                    timer.cancel();
                }
                Log.i("TimeUntils",time+"");
            }
        };
        timer.schedule(task, 100, 1000);
    }
}
