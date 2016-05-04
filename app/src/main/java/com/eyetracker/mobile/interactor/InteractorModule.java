package com.eyetracker.mobile.interactor;

import com.eyetracker.mobile.interactor.camera.ImageInteractor;
import com.eyetracker.mobile.interactor.frame.FrameInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fabia on 4/20/2016.
 */
@Module
public class InteractorModule {

    @Provides
    public FrameInteractor provideFrameInteractor() {
        return new FrameInteractor();
    }

    @Provides
    public ImageInteractor provideImageInteractor() { return new ImageInteractor(); }

}
