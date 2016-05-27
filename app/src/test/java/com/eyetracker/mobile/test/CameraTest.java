package com.eyetracker.mobile.test;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.ui.camera.CameraPresenter;
import com.eyetracker.mobile.ui.camera.CameraScreen;
import com.eyetracker.mobile.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by fabia on 5/21/2016.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CameraTest {

    private CameraPresenter cameraPresenter;
    private CameraScreen cameraScreen;

    @Before
    public void setup() throws Exception {
        cameraScreen = mock(CameraScreen.class);
        cameraPresenter = new CameraPresenter();
        cameraPresenter.attachScreen(cameraScreen);
    }

    @Test
    public void testProcessImage() {
        byte[] data = new byte[]{};
        cameraPresenter.processRawImage(data);
        ArgumentCaptor<byte[]> imageCaptor = ArgumentCaptor.forClass(
                byte[].class);
        verify(cameraScreen).showProcessedImage(imageCaptor.capture());
    }

    @Test
    public void testDiscardImage() {
        cameraPresenter.discard();
        verify(cameraScreen).discardResults();
    }

    @Test
    public void testUploadImage() {
        cameraPresenter.upload();
        ArgumentCaptor<Frame> imageCaptor = ArgumentCaptor.forClass(
                Frame.class);
        verify(cameraScreen).uploadFrame(imageCaptor.capture());
    }

    @After
    public void tearDown() {
        cameraPresenter.detachScreen();
    }

}
