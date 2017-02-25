package com.escodro.saatila.module;

import com.escodro.saatila.database.DatabaseRealm;
import com.escodro.saatila.database.WeatherDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IgorEscodro on 24/02/17.
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public DatabaseRealm provideDatabaseRealm() {
        return new DatabaseRealm();
    }

    @Provides
    @Singleton
    public WeatherDatabase provideWeatherDatabase(DatabaseRealm databaseRealm) {
        return new WeatherDatabase(databaseRealm);
    }
}
