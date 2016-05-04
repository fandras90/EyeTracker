package com.eyetracker.mobile.repository;

import com.eyetracker.mobile.model.Frame;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fabia on 4/25/2016.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public IRepository<Frame> provideFrameRepository() { return new FrameRepository(); }

}
