package com.karrel.bluetoothsample.etc;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class RxEvent {
    private static RxEvent mInstance;
    private PublishSubject<Object> mSubject;

    private RxEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxEvent();
        }
        return mInstance;
    }

    public void sendEvent(Object object) {
        mSubject.onNext(object);
    }

    public Observable<Object> getObservable() {
        return mSubject;
    }
}
