package com.escodro.saatila.network;

import com.escodro.saatila.module.WeatherServiceModule;
import com.escodro.saatila.network.model.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by IgorEscodro on 15/12/16.
 */
public interface WeatherService {

    @GET(WeatherServiceModule.GET_WEATHER_URL)
    Observable<WeatherResponse> getWeather(@Query("q") String city);

}
