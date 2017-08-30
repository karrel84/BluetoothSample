package com.karrel.bluetoothsample.event;

import com.karrel.bluetoothsample.model.Protocol;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class RxProtocolEvent {
    private static RxProtocolEvent mInstance;
    private PublishSubject<Protocol> mSubject;

    private RxProtocolEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxProtocolEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxProtocolEvent();
        }
        return mInstance;
    }

    public void sendEvent(Protocol device) {
        mSubject.onNext(device);
    }

    public Observable<Protocol> getObservable() {
        return mSubject;
    }
}
