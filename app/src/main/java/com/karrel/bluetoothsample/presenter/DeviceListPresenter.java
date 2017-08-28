package com.karrel.bluetoothsample.presenter;

import com.karrel.bluetoothsample.model.BluetoothItem;

import java.util.ArrayList;

/**
 * Created by Rell on 2017. 8. 24..
 */

public interface DeviceListPresenter {

    void startBluetooth();

    void onPermissionGranted();

    void onPermissionDenied(ArrayList<String> deniedPermissions);

    void onResultEnableBt(int resultCode);

    void startScan();

    void stopScan();

    interface View {

        void showToast(String s);

        void finish();

        void checkPermission(String rationaleMessage, String... permissions);

        void startActionRequestEnable();

        void addBluetoothDevice(BluetoothItem item);
    }
}
