package com.escodro.saatila.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by IgorEscodro on 17/12/16.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ForActivity {
}
