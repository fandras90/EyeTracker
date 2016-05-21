package com.eyetracker.mobile.interactor.frame;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.interactor.frame.event.GetFrameEvent;
import com.eyetracker.mobile.interactor.frame.event.GetFramesEvent;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;
import com.eyetracker.mobile.network.frame.FrameApi;
import com.eyetracker.mobile.repository.IRepository;

import org.greenrobot.eventbus.EventBus;
import org.opencv.core.Mat;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fabia on 4/20/2016.
 */
public class FrameInteractor {

    @Inject
    FrameApi frameApi;

    public FrameInteractor() {
        EyeTrackerApplication.injector.inject(this);
    }

    public void getFrames() {
        Call<Frames> frameResultCall = frameApi.getFrames(0, 5);
        GetFramesEvent event = new GetFramesEvent();
        try {
            Response<Frames> response = frameResultCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setResult(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void getFrame(final Long id) {
        Call<Frame> frameResultCall = frameApi.getFrameById(id);
        GetFrameEvent event = new GetFrameEvent();
        try {
            Response<Frame> response = frameResultCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setResult(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void uploadFrame(Mat f) {

    }

}
