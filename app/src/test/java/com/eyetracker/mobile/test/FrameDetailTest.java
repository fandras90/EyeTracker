package com.eyetracker.mobile.test;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;
import com.eyetracker.mobile.ui.framedetail.FrameDetailPresenter;
import com.eyetracker.mobile.ui.framedetail.FrameDetailScreen;
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
public class FrameDetailTest {

    private FrameDetailPresenter frameDetailPresenter;
    private FrameDetailScreen frameDetailScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        frameDetailScreen = mock(FrameDetailScreen.class);
        frameDetailPresenter = new FrameDetailPresenter();
        frameDetailPresenter.attachScreen(frameDetailScreen);
    }

    @Test
    public void testShowFrame() {
        final Long id = 0L;
        frameDetailPresenter.getFrame(id);
        ArgumentCaptor<Frame> frameCaptor = ArgumentCaptor.forClass(
                Frame.class);
        verify(frameDetailScreen).showFrame(frameCaptor.capture());
        assertTrue(frameCaptor.getValue().getId() == id);
    }

    @After
    public void tearDown() {
        frameDetailPresenter.detachScreen();
    }


}
