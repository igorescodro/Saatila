package com.escodro.saatila.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by IgorEscodro on 18/12/16.
 */

public class WeatherRealm extends RealmObject {

    @PrimaryKey
    private String name;

    private String temp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
