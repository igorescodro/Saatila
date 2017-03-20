package com.escodro.saatila.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by IgorEscodro on 18/03/17.
 *
 * @see
 * <a href="https://medium.com/google-developer-experts/persist-your-data-elegantly-u2020-way-c50be19acf9#.p53f7kt4u">Persist
 * your data elegantly: U2020 way - Medium</a>
 * @see <a href="https://github.com/pomopomo/WearPomodoro/">WearPomodoro - GitHub</a>
 */

public class StringPreference {

    private final SharedPreferences mPreferences;

    private final String mKey;

    private final String mDefaultValue;

    public StringPreference(@NonNull SharedPreferences preferences, @NonNull String key) {
        this(preferences, key, null);
    }

    public StringPreference(@NonNull SharedPreferences preferences, @NonNull String key,
                            @Nullable String defaultValue) {
        mPreferences = preferences;
        mKey = key;
        mDefaultValue = defaultValue;
    }

    @Nullable
    public String get() {
        return mPreferences.getString(mKey, mDefaultValue);
    }

    public boolean isSet() {
        return mPreferences.contains(mKey);
    }

    public void set(@Nullable String value) {
        mPreferences.edit().putString(mKey, value).apply();
    }

    public void delete() {
        mPreferences.edit().remove(mKey).apply();
    }
}
