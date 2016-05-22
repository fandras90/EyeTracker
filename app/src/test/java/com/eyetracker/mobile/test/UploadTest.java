package com.eyetracker.mobile.test;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.ui.upload.UploadPresenter;
import com.eyetracker.mobile.ui.upload.UploadScreen;
import com.eyetracker.mobile.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static com.eyetracker.mobile.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by fabia on 5/21/2016.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UploadTest {

    private UploadPresenter uploadPresenter;
    private UploadScreen uploadScreen;

    private byte[] image;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        uploadScreen = mock(UploadScreen.class);
        uploadPresenter = new UploadPresenter();
        uploadPresenter.attachScreen(uploadScreen);

        image = new byte[]{};

        uploadPresenter.setUploadable(image);
    }

    @Test
    public void testUpload() {

    }

    @After
    public void tearDown() {
        uploadPresenter.detachScreen();
    }

}
