package com.karrel.bluetoothsample.presenter;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Rell on 2017. 8. 24..
 */

public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View view;

    public MainPresenterImpl(MainPresenter.View view) {
        this.view = view;
    }

    @Override
    public void connectBt(BluetoothDevice device) {
    }
}
