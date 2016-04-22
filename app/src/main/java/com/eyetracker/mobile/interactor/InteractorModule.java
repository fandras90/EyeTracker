package com.eyetracker.mobile.interactor;

import com.eyetracker.mobile.interactor.frames.FrameInteractor;

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
}
