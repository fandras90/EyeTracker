package com.eyetracker.mobile.interactor.framelist;

import org.greenrobot.eventbus.EventBus;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.interactor.framelist.event.GetFramesEvent;
import com.eyetracker.mobile.model.FrameResult;
import com.eyetracker.mobile.network.FramesApi;

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
        Call<List<FrameResult>> frameResultCall = framesApi.getFrames();
        GetFramesEvent event = new GetFramesEvent();
        try {
            Response<List<FrameResult>> response = frameResultCall.execute();
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
