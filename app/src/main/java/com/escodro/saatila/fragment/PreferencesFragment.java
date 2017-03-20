package com.escodro.saatila.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import com.escodro.saatila.R;
import com.escodro.saatila.controller.PreferencesController;
import com.escodro.saatila.injector.Injector;

import javax.inject.Inject;

/**
 * Created by IgorEscodro on 25/02/17.
 */
public class PreferencesFragment extends PreferenceFragmentCompat {

    @Inject
    PreferencesController mController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getApplicationComponent().inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListPreference listPreference = (ListPreference) findPreference(
                PreferencesController.KEY_SCALE);
        mController.setListPreference(listPreference);
    }
}
