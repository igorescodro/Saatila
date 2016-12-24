package com.escodro.saatila.network.model;

import lombok.Getter;

/**
 * Created by IgorEscodro on 18/12/16.
 */
public class Sys {

    @Getter
    private String message;

    @Getter
    private String id;

    @Getter
    private String sunset;

    @Getter
    private String sunrise;

    @Getter
    private String type;

    @Getter
    private String country;
}
