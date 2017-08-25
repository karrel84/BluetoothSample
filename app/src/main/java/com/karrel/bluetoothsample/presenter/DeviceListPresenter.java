package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.karrel.bluetoothsample.view.DeviceListActivity;

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

    void startScan();

    void stopScan();

    void pairingDevice(Context context, BluetoothDevice device);

    interface View {

        void showToast(String s);

        void finish();

        void checkPermission(String rationaleMessage, String... permissions);

        void startActionRequestEnable();

        void setPairedList(Set<BluetoothDevice> pairedDevices);

        void addScanedDevice(BluetoothDevice device);
    }
}
