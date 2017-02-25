package com.escodro.saatila.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.escodro.saatila.database.WeatherDatabase;
import com.escodro.saatila.database.WeatherRealm;
import com.escodro.saatila.injector.Injector;
import com.escodro.saatila.network.WeatherService;
import com.escodro.saatila.network.model.WeatherResponse;
import com.escodro.saatila.observable.ObservableUtil;

import javax.inject.Inject;

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

    private int mLoadingVisibility;

    @Inject
    WeatherDatabase mWeatherDb;

    @Inject
    public WeatherViewModel(WeatherService service) {
        Injector.getApplicationComponent().inject(this);
        mService = service;
        mWeather = new ObservableField<>();
        sendRequest();
    }

    public void onDestroy() {
        mWeatherDb.close();
    }

    private void sendRequest() {
        final String name = "San Francisco";

        setVisibility(View.VISIBLE);

        final Observable<WeatherResponse> objectObs = ObservableUtil
                .zipWithTimer(mService.getWeather(name));

        Observable<WeatherRealm> observable = objectObs
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(mWeatherDb::writeToRealm)
                .observeOn(AndroidSchedulers.mainThread())
                .map(mWeatherDb::readFromRealm);

        final WeatherRealm cachedWeather = mWeatherDb.readFromRealm(name);
        if (cachedWeather != null) {
            observable = observable.mergeWith(Observable.just(cachedWeather));
        }

        observable.subscribe(
                weatherRealm -> {
                    mWeather.set(weatherRealm);
                    setVisibility(View.INVISIBLE);
                },
                throwable -> {
                    Log.e(TAG, throwable.getLocalizedMessage());
                    setVisibility(View.INVISIBLE);
                });
    }

    private void setVisibility(int visibility) {
        mLoadingVisibility = visibility;
        notifyPropertyChanged(BR.loadingVisibility);
    }

    public ObservableField<WeatherRealm> getWeather() {
        return mWeather;
    }

    @Bindable
    public int getLoadingVisibility() {
        return mLoadingVisibility;
    }
}
