package com.eyetracker.mobile.interactor.frame;

import org.greenrobot.eventbus.EventBus;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.interactor.frame.event.GetFramesEvent;
import com.eyetracker.mobile.model.frame.Frame;
import com.eyetracker.mobile.network.frame.FramesApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fabia on 4/20/2016.
 */
public class FrameInteractor {

    @Inject
    FramesApi framesApi;

    public FrameInteractor() {
        EyeTrackerApplication.injector.inject(this);
    }

    public void getFrames() {
        Call<List<Frame>> frameResultCall = framesApi.getFrames();
        GetFramesEvent event = new GetFramesEvent();
        try {
            Response<List<Frame>> response = frameResultCall.execute();
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
