package com.escodro.saatila;

import android.app.Application;

import com.escodro.saatila.database.DatabaseRealm;
import com.escodro.saatila.injector.Injector;

import javax.inject.Inject;

/**
 * Created by IgorEscodro on 15/12/16.
 */

public class SaatilaApplication extends Application {

    @Inject
    DatabaseRealm mDatabaseRealm;

    @Override
    public void onCreate() {
        super.onCreate();
        createComponent();
        initRealm();
    }

    private void initRealm() {
        mDatabaseRealm.setup();
    }

    private void createComponent() {
        Injector.initializeApplicationComponent(this);
        Injector.getApplicationComponent().inject(this);
    }
}
