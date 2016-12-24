package com.escodro.saatila;

import android.app.Application;
import android.content.Context;

import com.escodro.saatila.component.ApplicationComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by IgorEscodro on 15/12/16.
 */

public class SaatilaApplication extends Application {

    private ApplicationComponent mApplicationComponent;

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
        mApplicationComponent = ApplicationComponent.Initializer.init(this);
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            createComponent();
        }
        return mApplicationComponent;
    }

    public static SaatilaApplication get(Context context) {
        return (SaatilaApplication) context.getApplicationContext();
    }
}
