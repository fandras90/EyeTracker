package com.eyetracker.mobile.network;

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
        return new Retrofit.Builder()
                .baseUrl(NetworkConfig.ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public FramesApi provideMeasurementsApi(Retrofit retrofit) {
        return retrofit.create(FramesApi.class);
    }
}
