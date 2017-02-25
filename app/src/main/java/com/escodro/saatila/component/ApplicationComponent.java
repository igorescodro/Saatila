package com.escodro.saatila.component;

import com.escodro.saatila.SaatilaApplication;
import com.escodro.saatila.database.DatabaseRealm;
import com.escodro.saatila.fragment.WeatherFragment;
import com.escodro.saatila.module.ApplicationModule;
import com.escodro.saatila.module.DatabaseModule;
import com.escodro.saatila.module.WeatherServiceModule;
import com.escodro.saatila.viewmodel.WeatherViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by IgorEscodro on 17/12/16.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        WeatherServiceModule.class,
        DatabaseModule.class
})

public interface ApplicationComponent {

    void inject(SaatilaApplication app);

    void inject(WeatherFragment fragment);

    void inject(DatabaseRealm dbRealm);

    void inject(WeatherViewModel weatherViewModel);
}
