package com.eyetracker.mobile;

import com.eyetracker.mobile.interactor.InteractorModule;
import com.eyetracker.mobile.model.MockModelModule;
import com.eyetracker.mobile.model.ModelModule;
import com.eyetracker.mobile.network.NetworkModule;
import com.eyetracker.mobile.ui.UIModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fabia on 4/25/2016.
 */
@Singleton
@Component(modules = {UIModule.class, MockModelModule.class, NetworkModule.class, InteractorModule.class})
public interface EyeTrackerMockApplicationComponent extends EyeTrackerApplicationComponent {
}
