package com.escodro.saatila.database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by IgorEscodro on 18/12/16.
 */

public class WeatherRealm extends RealmObject {

    @PrimaryKey
    private String name;

    private String temp;

    private long refreshDate;

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

    public void setRefreshDate(long refreshDate) {
        this.refreshDate = refreshDate;
    }

    public String getFormattedRefreshDate() {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);
        calendar.setTimeInMillis(refreshDate);
        return sdf.format(calendar.getTime());
    }
}
