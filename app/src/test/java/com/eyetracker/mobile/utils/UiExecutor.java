package com.eyetracker.mobile.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by fabia on 5/20/2016.
 */
public class UiExecutor implements Executor {

    private Handler handler;

    public UiExecutor() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable command) {
        handler.post(command);
    }

}
