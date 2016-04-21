package com.eyetracker.mobile.interactor.frames;

import org.greenrobot.eventbus.EventBus;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.interactor.frames.event.GetFramesEvent;
import com.eyetracker.mobile.model.FramesResult;
import com.eyetracker.mobile.network.FramesApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fabia on 4/20/2016.
 */
public class FramesInteractor {

    @Inject
    FramesApi framesApi;

    public FramesInteractor() {
        EyeTrackerApplication.injector.inject(this);
    }

    public void getMeasurements() {
        Call<FramesResult> framesResultCall = framesApi.getFrames();
        GetFramesEvent event = new GetFramesEvent();
        try {
            Response<FramesResult> response = framesResultCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            //event.setCode(response.code());
            //event.setArtists(response.body().getArtists().getItems());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            //event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
