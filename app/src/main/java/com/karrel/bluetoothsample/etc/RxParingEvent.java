package com.karrel.bluetoothsample.etc;

import android.bluetooth.BluetoothDevice;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rell on 2017. 8. 25..
 */

public class RxParingEvent {
    private static RxParingEvent mInstance;
    private PublishSubject<BluetoothDevice> mSubject;

    private RxParingEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxParingEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxParingEvent();
        }
        return mInstance;
    }

    public void sendEvent(BluetoothDevice device) {
        mSubject.onNext(device);
    }

    public Observable<BluetoothDevice> getObservable() {
        return mSubject;
    }
}
