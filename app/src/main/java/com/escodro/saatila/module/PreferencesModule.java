package com.escodro.saatila.module;

import android.content.SharedPreferences;

import com.escodro.saatila.common.Constants;
import com.escodro.saatila.preference.StringPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IgorEscodro on 18/03/17.
 */

@Module
public class PreferencesModule {

    @Provides
    @Singleton
    public StringPreference provideTemperatureScale(SharedPreferences preferences) {
        return new StringPreference(preferences, Constants.KEY_TEMPERATURE_SCALE,
                Constants.VALUE_CELSIUS);
    }

}
