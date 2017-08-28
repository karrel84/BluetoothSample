package com.karrel.bluetoothsample.model;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Rell on 2017. 8. 28..
 */

public class PairedItem extends BluetoothItem {
    public PairedItem(BluetoothDevice device) {
        this.device = device;
    }
}
