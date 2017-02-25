package com.escodro.saatila.injector;

import com.escodro.saatila.SaatilaApplication;
import com.escodro.saatila.component.ApplicationComponent;
import com.escodro.saatila.component.DaggerApplicationComponent;
import com.escodro.saatila.module.ApplicationModule;
import com.escodro.saatila.module.DatabaseModule;
import com.escodro.saatila.module.WeatherServiceModule;

import java.util.Objects;

/**
 * Created by IgorEscodro on 24/02/17.
 */

public class Injector {

    private static ApplicationComponent mApplicationComponent;

    private Injector() {
    }

    public static void initializeApplicationComponent(SaatilaApplication application) {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .weatherServiceModule(new WeatherServiceModule())
                .databaseModule(new DatabaseModule())
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        Objects.requireNonNull(mApplicationComponent, "ApplicationComponent is null");
        return mApplicationComponent;
    }
}
