package com.eyetracker.mobile.network;

import com.eyetracker.mobile.network.frame.FrameApi;
import com.eyetracker.mobile.network.frame.IFrameApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fabia on 5/21/2016.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(NetworkConfig.ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public IFrameApi provideFrameApi(Retrofit retrofit) {
        return retrofit.create(FrameApi.class);
    }

}
