package com.eyetracker.mobile;

import com.eyetracker.mobile.interactor.InteractorModule;
import com.eyetracker.mobile.interactor.frame.FrameInteractor;
import com.eyetracker.mobile.repository.RepositoryModule;
import com.eyetracker.mobile.network.NetworkModule;
import com.eyetracker.mobile.ui.UIModule;
import com.eyetracker.mobile.ui.camera.CameraActivity;
import com.eyetracker.mobile.ui.camera.CameraPresenter;
import com.eyetracker.mobile.ui.framedetail.FrameDetailActivity;
import com.eyetracker.mobile.ui.framedetail.FrameDetailPresenter;
import com.eyetracker.mobile.ui.framelist.FrameAdapter;
import com.eyetracker.mobile.ui.framelist.FrameListActivity;
import com.eyetracker.mobile.ui.framelist.FrameListPresenter;
import com.eyetracker.mobile.ui.upload.UploadActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fabia on 4/20/2016.
 */
@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, NetworkModule.class, InteractorModule.class})
public interface EyeTrackerApplicationComponent {

    void inject(FrameListActivity frameListActivity);
    void inject(FrameListPresenter frameListPresenter);
    void inject(FrameAdapter frameAdapter);

    void inject(FrameDetailActivity frameDetailActivity);
    void inject(FrameDetailPresenter frameDetailPresenter);

    void inject(CameraActivity cameraActivity);
    void inject(CameraPresenter cameraPresenter);

    void inject(UploadActivity uploadActivity);

    void inject(FrameInteractor frameInteractor);

}