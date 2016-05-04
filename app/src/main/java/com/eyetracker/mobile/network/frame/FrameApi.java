package com.eyetracker.mobile.network.frame;

import com.eyetracker.mobile.model.Frame;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fabia on 4/20/2016.
 */
public interface FrameApi {

    @GET("search")
    Call<List<Frame>> getFrames();

}