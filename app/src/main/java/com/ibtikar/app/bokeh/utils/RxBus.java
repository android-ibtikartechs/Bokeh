package com.ibtikar.app.bokeh.utils;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus  {

    private static final BehaviorSubject<Object> behaviorSubject
            = BehaviorSubject.create();


    public static Disposable subscribe(@NonNull Consumer<Object> action) {
        return behaviorSubject.subscribe(action);
    }
    //use this method to send data
    public static void publish(@NonNull Object message) {
        behaviorSubject.onNext(message);
    }



    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }


}
