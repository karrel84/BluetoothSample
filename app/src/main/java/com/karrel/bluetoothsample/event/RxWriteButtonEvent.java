package com.karrel.bluetoothsample.event;

import android.bluetooth.BluetoothDevice;

import com.karrel.bluetoothsample.model.RxAddWriteItem;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by jylee on 2017. 8. 29..
 */

public class RxWriteButtonEvent {
    private static RxWriteButtonEvent mInstance;
    private PublishSubject<RxAddWriteItem> mSubject;

    private RxWriteButtonEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxWriteButtonEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxWriteButtonEvent();
        }
        return mInstance;
    }

    public void sendEvent(RxAddWriteItem device) {
        mSubject.onNext(device);
    }

    public Observable<RxAddWriteItem> getObservable() {
        return mSubject;
    }
}
