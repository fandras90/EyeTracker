package com.eyetracker.mobile.network;

import com.eyetracker.mobile.model.MeasurementsResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fabia on 4/20/2016.
 */
public interface MeasurementsApi {
    @GET("search")
    Call<MeasurementsResult> getMeasurements();
}
