package com.escodro.saatila.network.model;

import lombok.Getter;

/**
 * Created by IgorEscodro on 18/12/16.
 */
public class Weather {

    @Getter
    private String id;

    @Getter
    private String icon;

    @Getter
    private String description;

    @Getter
    private String main;
}
