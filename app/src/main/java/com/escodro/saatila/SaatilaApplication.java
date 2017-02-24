package com.escodro.saatila;

import android.app.Application;

import com.escodro.saatila.component.ApplicationComponent;
import com.escodro.saatila.injector.Injector;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by IgorEscodro on 15/12/16.
 */

public class SaatilaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        createComponent();
    }

    private void initRealm() {
        final RealmConfiguration realmConfig = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    private void createComponent() {
        Injector.initializeApplicationComponent(this);
        Injector.getApplicationComponent().inject(this);
    }
}
