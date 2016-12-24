package com.escodro.saatila.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by IgorEscodro on 17/12/16.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
