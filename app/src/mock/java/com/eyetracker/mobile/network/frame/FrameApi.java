package com.eyetracker.mobile.network.frame;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;
import com.eyetracker.mobile.network.frame.FrameApi;
import com.eyetracker.mobile.repository.IRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fabia on 5/21/2016.
 */
public class FrameApi implements IFrameApi {

    @Inject
    IRepository<Frame> frameRepository;

    public FrameApi() {
        EyeTrackerApplication.injector.inject(this);
    }

    @Override
    public Call<Frames> getFrames(@Query("offset") Integer offset, @Query("limit") Integer limit) {

        final Frames frames = new Frames();
        List<Frame> frameList = frameRepository.listAll();
        frames.setFrames(frameList);

        Call<Frames> call = new Call<Frames>() {

            @Override
            public Response<Frames> execute() throws IOException {
                return Response.success(frames);
            }

            @Override
            public void enqueue(Callback<Frames> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Frames> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<Void> uploadFrame(@Body Frame _postBody) {

        final Frame frame = _postBody;
        frameRepository.insert(frame);

        Call<Void> call = new Call<Void>() {

            @Override
            public Response<Void> execute() throws IOException {
                return Response.success(null);
            }

            @Override
            public void enqueue(Callback<Void> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<Frame> getFrameById(@Path("id") Long id) {

        final Frame frame = frameRepository.getById(id);

        Call<Frame> call = new Call<Frame>() {

            @Override
            public Response<Frame> execute() throws IOException {
                return Response.success(frame);
            }

            @Override
            public void enqueue(Callback<Frame> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Frame> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<Void> deleteFrame(@Path("id") Long id) {

        final Frame frame = frameRepository.getById(id);
        frameRepository.delete(frame);

        Call<Void> call = new Call<Void>() {
            @Override
            public Response<Void> execute() throws IOException {
                return Response.success(null);
            }

            @Override
            public void enqueue(Callback<Void> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

}