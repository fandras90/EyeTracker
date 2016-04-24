package com.eyetracker.mobile;

import dagger.Component;
import javax.inject.Singleton;

import com.eyetracker.mobile.interactor.InteractorModule;
import com.eyetracker.mobile.interactor.frames.FrameInteractor;
import com.eyetracker.mobile.network.NetworkModule;
import com.eyetracker.mobile.ui.UIModule;
import com.eyetracker.mobile.ui.camera.CameraActivity;
import com.eyetracker.mobile.ui.camera.LiveActivity;
import com.eyetracker.mobile.ui.main.FrameListActivity;

/**
 * Created by fabia on 4/20/2016.
 */
@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, InteractorModule.class})
public interface EyeTrackerApplicationComponent {
    void inject(FrameListActivity mainActivity);

    void inject(CameraActivity cameraActivity);
    void inject(LiveActivity cameraActivity);

//    void inject(ArtistsFragment artistsFragment);
//
    void inject(FrameInteractor frameInteractor);
//
//    void inject(ArtistsPresenter artistsPresenter);
}