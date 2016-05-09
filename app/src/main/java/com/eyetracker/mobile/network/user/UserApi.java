package com.eyetracker.mobile.network.user;

import com.eyetracker.mobile.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fabia on 5/9/2016.
 */
public interface UserApi {

    /**
     * User Profile
     * The User Profile endpoint returns information about the EyeTracker user that has authorized with the application.
     * @return Call<Profile>
     */
    @GET("me")
    Call<Profile> getUser();

}
