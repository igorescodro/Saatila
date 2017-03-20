package com.escodro.saatila.controller;

import android.support.annotation.NonNull;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;

import com.escodro.saatila.injector.Injector;
import com.escodro.saatila.preference.StringPreference;

import javax.inject.Inject;

/**
 * Created by IgorEscodro on 19/03/17.
 */

public class PreferencesController implements Preference.OnPreferenceChangeListener {

    public static final String KEY_SCALE = "scale";

    private ListPreference mScalePreference;

    @Inject
    StringPreference mScale;

    @Inject
    public PreferencesController() {
        Injector.getApplicationComponent().inject(this);
    }

    public void setListPreference(@NonNull ListPreference listPreference) {
        mScalePreference = listPreference;
        mScalePreference.setOnPreferenceChangeListener(this);
        updateValues();
    }

    private void updateValues() {
        if (mScalePreference == null) {
            return;
        }

        final String scaleValue = mScale.get();
        mScalePreference.setValue(scaleValue);
        mScalePreference.setSummary(scaleValue);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case KEY_SCALE:
                mScale.set(newValue.toString());
                updateValues();
                break;
        }
        return true;
    }
}
