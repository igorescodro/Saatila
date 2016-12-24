package com.escodro.saatila.network.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IgorEscodro on 15/12/16.
 */
public class WeatherResponse {

    @Getter
    private String id;

    @Getter
    private String dt;

    @Getter
    private Clouds clouds;

    @Getter
    private Coord coord;

    @Getter
    private Wind wind;

    @Getter
    private String cod;

    @Setter
    @Getter
    private String visibility;

    @Getter
    private Sys sys;

    @Setter
    @Getter
    private String name;

    @Getter
    private String base;

    @Getter
    private List<Weather> weather;

    @Getter
    private Main main;
}
