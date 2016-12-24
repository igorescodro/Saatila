package com.escodro.saatila.module;

import android.content.Context;

import com.escodro.saatila.SaatilaApplication;
import com.escodro.saatila.annotation.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IgorEscodro on 15/12/16.
 */
@Module
public class ApplicationModule {

    private final SaatilaApplication mApplication;

    public ApplicationModule(SaatilaApplication app) {
        mApplication = app;
    }

    @ForApplication
    @Provides
    @Singleton
    Context provideContext() {
        return mApplication.getApplicationContext();
    }
}
