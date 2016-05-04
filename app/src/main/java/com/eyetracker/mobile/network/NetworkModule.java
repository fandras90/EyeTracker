package com.eyetracker.mobile.network;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.network.frame.FrameApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fabia on 4/20/2016.
 */
@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        if (BuildConfig.FLAVOR == "production")
            return new Retrofit.Builder()
                    .baseUrl(NetworkConfig.ENDPOINT_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        else if (BuildConfig.FLAVOR == "mock")
            return new Retrofit.Builder()
                    .baseUrl(NetworkConfig.MOCK_ENDPOINT_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        else return null;
    }

    @Provides
    @Singleton
    public FrameApi provideFrameApi(Retrofit retrofit) {
        return retrofit.create(FrameApi.class);
    }
}
