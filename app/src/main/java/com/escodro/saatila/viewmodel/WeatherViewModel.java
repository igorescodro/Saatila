package com.escodro.saatila.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;

import com.escodro.saatila.database.WeatherRealm;
import com.escodro.saatila.network.WeatherService;
import com.escodro.saatila.network.model.WeatherResponse;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by IgorEscodro on 18/12/16.
 */

public class WeatherViewModel extends BaseObservable {

    private final static String TAG = WeatherViewModel.class.getName();

    private final ObservableField<WeatherRealm> mWeather;

    private final WeatherService mService;

    private Realm mRealmUI;

    @Inject
    public WeatherViewModel(WeatherService service) {
        mRealmUI = Realm.getDefaultInstance();
        mService = service;
        mWeather = new ObservableField<>();
        sendRequest();
    }

    public void onDestroy() {
        mRealmUI.close();
    }

    private void sendRequest() {
        final String name = "San Francisco";

        Observable<WeatherRealm> observable =
                mService.getWeather(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .map(this::writeToRealm)
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(this::readFromRealm);

        final WeatherRealm cachedWeather = readFromRealm(name);
        if (cachedWeather != null) {
            observable = observable.mergeWith(Observable.just(cachedWeather));
        }

        observable.subscribe(this::setWeather, this::processError);
    }

    private void setWeather(WeatherRealm weatherRealm) {
        mWeather.set(weatherRealm);
    }

    private void processError(Throwable throwable) {
        Log.e(TAG, throwable.getLocalizedMessage());
    }

    public ObservableField<WeatherRealm> getWeather() {
        return mWeather;
    }

    private WeatherRealm readFromRealm(String name) {
        return findInRealm(mRealmUI, name);
    }

    private String writeToRealm(WeatherResponse weatherResponse) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm -> {
            WeatherRealm weatherRealm = findInRealm(transactionRealm, weatherResponse
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

    private WeatherRealm findInRealm(Realm realm, String name) {
        return realm.where(WeatherRealm.class).equalTo("name", name).findFirst();
    }

}
