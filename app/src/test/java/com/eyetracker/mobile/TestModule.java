package com.eyetracker.mobile;

import android.content.Context;

import com.eyetracker.mobile.di.Network;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.repository.FrameRepository;
import com.eyetracker.mobile.repository.IRepository;
import com.eyetracker.mobile.ui.camera.CameraPresenter;
import com.eyetracker.mobile.ui.framedetail.FrameDetailPresenter;
import com.eyetracker.mobile.ui.framelist.FrameListPresenter;
import com.eyetracker.mobile.ui.upload.UploadPresenter;
import com.eyetracker.mobile.utils.UiExecutor;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fabia on 5/20/2016.
 */
@Module
public class TestModule {

    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public FrameListPresenter provideFrameListPresenter() {
        return new FrameListPresenter();
    }

    @Provides
    @Singleton
    public FrameDetailPresenter provideFrameDetailPresenter() {
        return new FrameDetailPresenter();
    }

    @Provides
    @Singleton
    public CameraPresenter provideCameraPresenter() {
        return new CameraPresenter();
    }

    @Provides
    @Singleton
    public UploadPresenter provideUploadPresenter() {
        return new UploadPresenter();
    }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }

}
