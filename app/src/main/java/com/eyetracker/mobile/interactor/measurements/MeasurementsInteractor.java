package com.eyetracker.mobile.interactor.measurements;

import org.greenrobot.eventbus.EventBus;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.interactor.measurements.event.GetMeasurementsEvent;
import com.eyetracker.mobile.model.MeasurementsResult;
import com.eyetracker.mobile.network.MeasurementsApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fabia on 4/20/2016.
 */
public class MeasurementsInteractor {

    @Inject
    MeasurementsApi measurementsApi;

    public MeasurementsInteractor() {
        EyeTrackerApplication.injector.inject(this);
    }

    public void getMeasurements() {
        Call<MeasurementsResult> measurementsResultCall = measurementsApi.getMeasurements();
        GetMeasurementsEvent event = new GetMeasurementsEvent();
        try {
            Response<MeasurementsResult> response = measurementsResultCall.execute();
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
