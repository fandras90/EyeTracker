package com.eyetracker.mobile.network;

import com.eyetracker.mobile.model.FramesResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fabia on 4/20/2016.
 */
public interface FramesApi {
    @GET("search")
    Call<List<FramesResult>> getFrames();
}
