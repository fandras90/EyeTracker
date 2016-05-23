package com.eyetracker.mobile.network.frame;

import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fabia on 5/23/2016.
 */
public interface IFrameApi {

    /**
     * Measured frames
     * The Frames endpoint returns items from all previous uploads of the user. The history array in the response will have a maximum length based on the limit parameter. The response value count may exceed limit, therefore subsequent API requests may be necessary.
     * @param offset Offset the list of returned results by this amount. Default is zero.
     * @param limit Number of items to retrieve. Default is 5, maximum is 100.
     * @return Call<Frames>
     */
    @GET("frames")
    Call<Frames> getFrames(
            @Query("offset") Integer offset, @Query("limit") Integer limit
    );


    /**
     * Upload Frame
     * The Specific Frame endpoint creates a new frame.
     * @param _postBody
     * @return Call<Frame>
     */
    @POST("frames")
    Call<Void> uploadFrame(
            @Body Frame _postBody
    );


    /**
     * Specific Frame
     * The Specific Frame endpoint returns the frame with the given id.
     * @param id Id of the frame
     * @return Call<Frame>
     */
    @GET("frames/{id}")
    Call<Frame> getFrameById(
            @Path("id") Long id
    );


    /**
     * Delete Frame
     * The Delete Frame endpoint deletes the frame with the given id.
     * @param id Id of the frame
     * @return Call<Void>
     */
    @DELETE("frames/{id}")
    Call<Void> deleteFrame(
            @Path("id") Long id
    );

}
