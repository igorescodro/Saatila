package com.escodro.saatila.observable;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by IgorEscodro on 25/02/17.
 */

public class ObservableUtil {

    public static <T> Observable<T> zipWithTimer(Observable<T> observable) {
        return Observable.zip(observable,
                Observable.timer(1000, TimeUnit.MILLISECONDS), (t, timerValue) -> t);
    }
}
