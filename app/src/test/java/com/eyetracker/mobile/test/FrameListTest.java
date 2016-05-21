package com.eyetracker.mobile.test;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.interactor.frame.FrameInteractor;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;
import com.eyetracker.mobile.ui.framelist.FrameListPresenter;
import com.eyetracker.mobile.ui.framelist.FrameListScreen;
import com.eyetracker.mobile.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import static com.eyetracker.mobile.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by fabia on 5/21/2016.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FrameListTest {

    private FrameListPresenter frameListPresenter;
    private FrameListScreen frameListScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        frameListScreen = mock(FrameListScreen.class);
        frameListPresenter = new FrameListPresenter();
        frameListPresenter.attachScreen(frameListScreen);
    }

    @Test
    public void testList() {
        frameListPresenter.refreshFrames();
        ArgumentCaptor<Frames> framesCaptor = ArgumentCaptor.forClass(
                Frames.class);
        verify(frameListScreen).showFrames(framesCaptor.capture());
        assertTrue(framesCaptor.getValue().getFrames().size() > 0);
    }

    @Test
    public void testShowDetails() {
        Frame f = new Frame();
        frameListPresenter.showDetails(f);
        verify(frameListScreen).showDetails(f);
    }

    @Test
    public void testStartCamera() {
        frameListPresenter.startCamera();
        verify(frameListScreen).startCamera();
    }

    @After
    public void tearDown() {
        frameListPresenter.detachScreen();
    }

}
