package com.eyetracker.mobile.model;

import com.eyetracker.mobile.model.frame.Frame;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fabia on 4/25/2016.
 */
@Module
public class MockModelModule extends ModelModule {

    @Provides
    Frame provideFrameModel() { return new Frame(); }

}
