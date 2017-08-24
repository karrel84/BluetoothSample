package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Rell on 2017. 8. 24..
 */

public interface MainPresenter {
    void connectBt(BluetoothDevice device);

    interface View {

    }
}
