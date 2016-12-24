package com.escodro.saatila.component;

import com.escodro.saatila.SaatilaApplication;
import com.escodro.saatila.fragment.WeatherFragment;
import com.escodro.saatila.module.ApplicationModule;
import com.escodro.saatila.module.WeatherServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by IgorEscodro on 17/12/16.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        WeatherServiceModule.class
})

public interface ApplicationComponent {

    void inject(SaatilaApplication app);

    void inject(WeatherFragment fragment);

    final class Initializer {
        public static ApplicationComponent init(SaatilaApplication app) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(app))
                    .weatherServiceModule(new WeatherServiceModule())
                    .build();
        }
    }

}
