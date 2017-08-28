package com.karrel.bluetoothsample.etc;

import android.bluetooth.BluetoothDevice;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class RxBluetoothConnectEvent {
    private static RxBluetoothConnectEvent mInstance;
    private PublishSubject<BluetoothDevice> mSubject;

    private RxBluetoothConnectEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxBluetoothConnectEvent getInstance() {
        if (mInstance == null) {
            mInstance = new RxBluetoothConnectEvent();
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
