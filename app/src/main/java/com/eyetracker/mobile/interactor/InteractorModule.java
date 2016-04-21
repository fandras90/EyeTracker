package com.eyetracker.mobile.interactor;

import com.eyetracker.mobile.interactor.frames.FramesInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fabia on 4/20/2016.
 */
@Module
public class InteractorModule {
    @Provides
    public FramesInteractor provideMeasurementsInteractor() {
        return new FramesInteractor();
    }
}
