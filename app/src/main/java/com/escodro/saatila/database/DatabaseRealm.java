package com.escodro.saatila.database;

import android.content.Context;

import com.escodro.saatila.injector.Injector;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by IgorEscodro on 24/02/17.
 */

public class DatabaseRealm {

    @Inject
    Context mContext;

    private RealmConfiguration mRealmConfiguration;

    public DatabaseRealm() {
        Injector.getApplicationComponent().inject(this);
    }

    public void setup() {
        Realm.init(mContext);
        if (mRealmConfiguration == null) {
            mRealmConfiguration = new RealmConfiguration.Builder()
                    .build();
            Realm.setDefaultConfiguration(mRealmConfiguration);
        } else {
            throw new IllegalStateException("Database already configured");
        }
    }

    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    public void close() {
        getRealmInstance().close();
    }

}
