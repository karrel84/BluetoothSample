package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Rell on 2017. 8. 24..
 */

public interface DeviceListPresenter {

    void startBluetooth();

    void onPermissionGranted();

    void onPermissionDenied(ArrayList<String> deniedPermissions);

    void onResultEnableBt(int resultCode);

    interface View {

        void showToast(String s);

        void finish();

        void checkPermission(String rationaleMessage, String... permissions);

        void startActionRequestEnable();

        void setPairedList(Set<BluetoothDevice> pairedDevices);

        void addScanedDevice(BluetoothDevice device);
    }
}
