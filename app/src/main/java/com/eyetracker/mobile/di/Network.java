package com.eyetracker.mobile.di;

import javax.inject.Qualifier;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by fabia on 4/20/2016.
 */
@Qualifier
@Retention(RUNTIME)
public @interface Network {
}