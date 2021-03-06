package com.karrel.bluetoothsample.event;

import com.karrel.bluetoothsample.model.Protocol;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by jylee on 2017. 8. 29..
 * <p>
 * 프로토콜을 수정하기위한 이벤트 버스이다.
 * 인자로 전달되는값이 null이라면 프로토콜을 추가한다.
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
