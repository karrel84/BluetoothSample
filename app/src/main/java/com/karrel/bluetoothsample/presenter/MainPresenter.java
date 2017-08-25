package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Rell on 2017. 8. 24..
 */

public interface MainPresenter {
    void connectBt(BluetoothDevice device);

    void sendMessage(String message);

    void startBt();

    void stopBt();

    interface View {

        void connectedDevice(String mConnectedDeviceName);

        void setSatus(String s);

        void readMessage(String readMessage);
    }
}
