package com.eyetracker.mobile;

import com.eyetracker.mobile.interactor.InteractorModule;
import com.eyetracker.mobile.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fabia on 5/20/2016.
 */

@Singleton
@Component(modules = { NetworkModule.class, TestModule.class, InteractorModule.class })
public interface TestComponent extends EyeTrackerApplicationComponent {
}
