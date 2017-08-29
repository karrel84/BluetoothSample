package com.karrel.bluetoothsample.event;

import com.karrel.bluetoothsample.model.ProtocolItem;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class RxWriteButtonEvent {
    private static RxWriteButtonEvent mInstance;
    private PublishSubject<ProtocolItem> mSubject;

    private RxWriteButtonEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxWriteButtonEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxWriteButtonEvent();
        }
        return mInstance;
    }

    public void sendEvent(ProtocolItem device) {
        mSubject.onNext(device);
    }

    public Observable<ProtocolItem> getObservable() {
        return mSubject;
    }
}
