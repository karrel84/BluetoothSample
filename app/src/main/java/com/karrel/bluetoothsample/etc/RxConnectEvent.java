package com.karrel.bluetoothsample.etc;

import android.bluetooth.BluetoothDevice;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class RxConnectEvent {
    private static RxConnectEvent mInstance;
    private PublishSubject<BluetoothDevice> mSubject;

    private RxConnectEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxConnectEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxConnectEvent();
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
