package com.eyetracker.mobile.test;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.utils.RobolectricDaggerTestRunner;

import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

/**
 * Created by fabia on 5/21/2016.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UploadTest {
}
