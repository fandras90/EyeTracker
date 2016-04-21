package com.eyetracker.mobile;

import dagger.Component;
import javax.inject.Singleton;

import com.eyetracker.mobile.interactor.InteractorModule;
import com.eyetracker.mobile.interactor.frames.FramesInteractor;
import com.eyetracker.mobile.network.NetworkModule;
import com.eyetracker.mobile.ui.UIModule;
import com.eyetracker.mobile.ui.main.MainActivity;

/**
 * Created by fabia on 4/20/2016.
 */
@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, InteractorModule.class})
public interface EyeTrackerApplicationComponent {
    void inject(MainActivity mainActivity);

//    void inject(ArtistsFragment artistsFragment);
//
    void inject(FramesInteractor measurementsInteractor);
//
//    void inject(ArtistsPresenter artistsPresenter);
}