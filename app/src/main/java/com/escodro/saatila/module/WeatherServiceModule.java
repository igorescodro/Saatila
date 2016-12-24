package com.escodro.saatila.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.escodro.saatila.R;
import com.escodro.saatila.annotation.ForApplication;
import com.escodro.saatila.network.WeatherService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IgorEscodro on 17/12/16.
 */
@Module
public class WeatherServiceModule {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static final String GET_WEATHER_URL = "weather?units=metric";

    @ForApplication
    @Inject
    Context mContext;

    @Provides
    @Singleton
    Gson provideGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            final Request original = chain.request();
            final HttpUrl originalHttpUrl = original.url();

            final HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiid", mContext.getString(R.string.OPEN_WEATHER_API_KEY))
                    .build();

            Request.Builder builder = original.newBuilder().url(url);
            final Request request = builder.build();
            return chain.proceed(request);
        });
        return httpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull Gson gson, @NonNull OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(@NonNull Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }
}
