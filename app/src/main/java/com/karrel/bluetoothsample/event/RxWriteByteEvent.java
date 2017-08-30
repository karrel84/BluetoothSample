package com.karrel.bluetoothsample.event;

import com.karrel.bluetoothsample.model.Protocol;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by jylee on 2017. 8. 29..
 * <p>
 * 블루투스 기기에 api를 송신하기위한 이벤트 버스이다.
 */

public class RxWriteByteEvent {
    private static RxWriteByteEvent mInstance;
    private PublishSubject<Protocol> mSubject;

    private RxWriteByteEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxWriteByteEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxWriteByteEvent();
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
