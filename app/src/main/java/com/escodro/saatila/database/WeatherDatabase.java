package com.escodro.saatila.database;

import com.escodro.saatila.network.model.WeatherResponse;

import io.realm.Realm;

/**
 * Created by IgorEscodro on 24/02/17.
 */
public class WeatherDatabase extends Database {

    private DatabaseRealm mDbRealm;

    public WeatherDatabase(DatabaseRealm dbRealm) {
        super(dbRealm);
        mDbRealm = dbRealm;
    }

    public WeatherRealm readFromRealm(String name) {
        final Realm realm = mDbRealm.getRealmInstance();
        return findFirst(realm, name);
    }

    public String writeToRealm(WeatherResponse weatherResponse) {
        Realm realm = mDbRealm.getRealmInstance();
        realm.executeTransaction(transactionRealm -> {
            WeatherRealm weatherRealm = findFirst(transactionRealm, weatherResponse
                    .getName());
            if (weatherRealm == null) {
                weatherRealm = transactionRealm.createObject(WeatherRealm.class, weatherResponse
                        .getName());
            }
            weatherRealm.setTemp(weatherResponse.getMain().getTemp());
        });
        realm.close();
        return weatherResponse.getName();
    }

    private WeatherRealm findFirst(Realm realm, String name) {
        return realm.where(WeatherRealm.class).equalTo("name", name).findFirst();
    }

}
