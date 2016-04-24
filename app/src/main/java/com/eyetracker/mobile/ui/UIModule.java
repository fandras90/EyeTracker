package com.eyetracker.mobile.ui;

import android.content.Context;

import com.eyetracker.mobile.di.Network;
import com.eyetracker.mobile.ui.camera.CameraPresenter;
import com.eyetracker.mobile.ui.framelist.FrameListPresenter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mobsoft on 2016. 04. 11..
 */
@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public FrameListPresenter provideMainPresenter() {
        return new FrameListPresenter();
    }

    @Provides
    @Singleton
    public CameraPresenter provideCameraPresenter() {
        return new CameraPresenter();
    }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}