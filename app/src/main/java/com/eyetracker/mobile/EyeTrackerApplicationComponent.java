package com.eyetracker.mobile;

import com.eyetracker.mobile.interactor.InteractorModule;
import com.eyetracker.mobile.interactor.framelist.FrameInteractor;
import com.eyetracker.mobile.network.NetworkModule;
import com.eyetracker.mobile.ui.UIModule;
import com.eyetracker.mobile.ui.camera.CameraActivity;
import com.eyetracker.mobile.ui.camera.CameraPresenter;
import com.eyetracker.mobile.ui.framelist.FrameListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fabia on 4/20/2016.
 */
@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, InteractorModule.class})
public interface EyeTrackerApplicationComponent {
    void inject(FrameListActivity frameListActivity);

    void inject(CameraActivity cameraActivity);
    void inject(CameraPresenter cameraPresenter);

//    void inject(ArtistsFragment artistsFragment);
//
    void inject(FrameInteractor frameInteractor);
//
//    void inject(ArtistsPresenter artistsPresenter);
}